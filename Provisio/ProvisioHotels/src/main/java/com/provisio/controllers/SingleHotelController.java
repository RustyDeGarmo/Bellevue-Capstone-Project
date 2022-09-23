//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for viewing single hotel location
package com.provisio.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.provisio.daos.HotelRoomDAO;
import com.provisio.daos.HotelsDAO;
import com.provisio.models.Hotel;
import com.provisio.models.HotelAmentity;
import com.provisio.models.HotelRoom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Servlet implementation class SingleHotelController
 */
@WebServlet("/hotels/*")

// Controller to handle single hotel view
public class SingleHotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String info = request.getRequestURL().toString();
		String hotelName = info.replaceAll(".*/", "").replace("-", " ");

		// Fetch hotel by name
		HotelsDAO hdao = new HotelsDAO();
		HotelRoomDAO hrdao = new HotelRoomDAO();
		Hotel hotel = hdao.fetchHotelByName(hotelName);
		
		HttpSession session = request.getSession();
		
		// check if session BOOKING_DATA attribute exists
		if(session.getAttribute("BOOKING_DATA") != null) {
			
			Map<String, String> data = (Map<String, String>) session.getAttribute("BOOKING_DATA");
			
			// remove session BOOKING_DATA attribute if the selected hotel is not the one in the session data
			if(Integer.parseInt(data.get("hotelId")) != hotel.getId()) {
				session.removeAttribute("BOOKING_DATA");
			}
		}
		
		// Fetch hotel amenities
		List<HotelAmentity> hamts = hdao.fetchHotelAmentities(hotel.getId());
		Collection<List<HotelAmentity>> groupedHamts = listChunk(hamts, 6);
		session.setAttribute("SELECTED_HOTEL", hotel);

		// Fetch hotel rooms 
		List<HotelRoom> hotelRooms = hrdao.fetchHotelRoomsFor(hotel.getId());
		session.setAttribute("HOTEL_AMENTITIES", groupedHamts);
		request.setAttribute("hotel_rooms", hotelRooms);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/single-hotel.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String APP_URL = getServletContext().getInitParameter("appUrl");
		String info = request.getRequestURL().toString();
		String hotelName = info.replaceAll(".*/", "");
		
		// If booking data is empty, forward to hotel view
		if (request.getParameter("bookingDate").isEmpty() || !validDates(request.getParameter("bookingDate"))) {
			response.sendRedirect(APP_URL + "/hotels/" + hotelName);
			return;
		}

		// Save booking_data in session
		Map parms = request.getParameterMap();
		Iterator i = parms.keySet().iterator();
		Map<String, String> attributes = new HashMap<>();
		
		HotelsDAO hdao = new HotelsDAO();
		Hotel hotel = hdao.fetchHotelByName(hotelName.replace("-", " "));
		
		attributes.put("hotelId", hotel.getId() + "");

		while (i.hasNext()) {
			String key = (String) i.next();
			String value = ((String[]) parms.get(key))[0];
			
			attributes.put(key, value);
		}

		session.setAttribute("BOOKING_DATA", attributes);
		
		// Forward to login.jsp if not logged in
		
		if (session.getAttribute("LOGGED_USER") == null) {
			
			session.setAttribute("LOGIN_MESSAGE", "Please log in to make a reservation");
			session.setAttribute("HOTEL_URL", APP_URL + "/hotels/" + hotelName);
			response.sendRedirect(APP_URL + "/login.jsp");
			
		} else {
		
			response.sendRedirect(APP_URL + "/review-reservation.jsp");
		}

	}

	private <T> Collection<List<T>> listChunk(List<T> inputList, int size) {
		final AtomicInteger counter = new AtomicInteger(0);
		return inputList.stream().collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size)).values();
	}

	// Date validation
	private boolean validDates(String bookingDates) {

		boolean flag = false;

		String[] dates = bookingDates.split("to");

		try {
			LocalDate.parse(dates[0].trim());
			LocalDate.parse(dates[1].trim());
			flag = true;
		} catch (Exception e) {
		}

		return flag;
	}

}
