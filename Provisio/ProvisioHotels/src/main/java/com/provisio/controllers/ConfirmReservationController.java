//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for Confirm Reservation
package com.provisio.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.provisio.daos.HotelAmenityDAO;
import com.provisio.daos.ReservationDAO;
import com.provisio.models.Hotel;
import com.provisio.models.HotelAmentity;
import com.provisio.models.User;
import com.provisio.utils.CurrencyFormatUtil;

/**
 * Servlet implementation class ConfirmReservationController
 */
@WebServlet("/confirm-reservation")

// Controller to confirm reservation
public class ConfirmReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// POST request
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String APP_URL = getServletContext().getInitParameter("appUrl");

		// Get reservation data from session
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, String> data = (Map<String, String>) session
				.getAttribute("BOOKING_DATA");

		Hotel hotel = (Hotel) session.getAttribute("SELECTED_HOTEL");

		User loggedUser = (User) session.getAttribute("LOGGED_USER");

		ReservationDAO rdao = new ReservationDAO();
		String code = rdao.saveReservation(data, loggedUser.getId());

		// Set booking attributes to request and remove from session
		if (code != null) {
			request.setAttribute("hotel_name", hotel.getName());
			request.setAttribute("location", hotel.getLocation());
			request.setAttribute("imgURL", hotel.getImages().get(0));
			request.setAttribute("guests", data.get("guests"));

			String[] bookingDates = data.get("bookingDate").split("to");

			request.setAttribute("checkin", bookingDates[0]);
			request.setAttribute("checkout", bookingDates[1]);
			request.setAttribute("nights", data.get("nights"));
			request.setAttribute("per_night", data.get("room_price"));

			BigDecimal price = new BigDecimal(data.get("room_price"));
			request.setAttribute("roomPrice",
					CurrencyFormatUtil.format(price.doubleValue()));
			price = price.multiply(new BigDecimal(data.get("nights")));
			request.setAttribute("roomTot",
					CurrencyFormatUtil.format(price.doubleValue()));
			List<Integer> selectedAmts = new ArrayList<>();

			for (Map.Entry<String, String> entry : data.entrySet()) {
				if (entry.getKey().contains("ham_")) {
					selectedAmts.add(
							Integer.parseInt(entry.getKey().split("_")[1]));
				}
			}
        // Added an empty check for the list to fix HotelAmenity bug
			HotelAmenityDAO hadao = new HotelAmenityDAO();
			List<HotelAmentity> amts = selectedAmts.isEmpty() ? new ArrayList<HotelAmentity>() : hadao.fetchAmenityData(selectedAmts);

			request.setAttribute("amentities", amts);
			request.setAttribute("total", CurrencyFormatUtil.format(Double.parseDouble(data.get("total"))));
			request.setAttribute("code", code);

			session.removeAttribute("SELECTED_HOTEL");
			session.removeAttribute("HOTEL_AMENTITIES");
			session.removeAttribute("BOOKING_DATA");

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/reservation-confirmed.jsp");

			dispatcher.forward(request, response);
		}

	}
}
