//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for Calculating cost of reservations
package com.provisio.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.provisio.daos.HotelRoomDAO;
import com.provisio.daos.ReservationDAO;

/**
 * Servlet implementation class ReservationCalculateController
 */


@WebServlet("/reservation-calculator")

// Controller to calculate reservation costs
public class ReservationCalculateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get booking data
		String bookingDates = request.getParameter("bookingDate");
		int guests = Integer.parseInt(request.getParameter("guests"));
		int hotelRoomId = Integer.parseInt(request.getParameter("room_size"));

		List<Integer> hamts = new ArrayList<>();
		Map parms = request.getParameterMap();
		Iterator i = parms.keySet().iterator();
		
		while (i.hasNext()) {
			String key = (String) i.next();
			if (key.contains("ham_")) {
				hamts.add(Integer.parseInt(key.split("_")[1]));
			}
		}

		ReservationDAO rdao = new ReservationDAO();

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		// Create JSON objects for errors and set status code
		PrintWriter out = response.getWriter();

		if (!validDates(bookingDates)) {
			
			response.setStatus(500);
			
			JsonObject json = new JsonObject();
			json.addProperty("error", "invalid-dates");
			json.addProperty("message", "Invalid booking date provided !");
			
			out.print(json.toString());
			out.flush();
			
			return;
		}

		if (!validGuestsForRoom(guests, hotelRoomId)) {
			
			response.setStatus(500);
			
			JsonObject json = new JsonObject();
			json.addProperty("error", "invalid-guests");
			json.addProperty("message", "Invalid room capacity for selected guests. !");
			
			out.print(json.toString());
			out.flush();
			
			return;
		}
		
		// Fetch calculation
		String reservationData = rdao.fetchCalculations(bookingDates, guests, hotelRoomId, hamts);
		response.setStatus(200);
		out.print(reservationData);
		out.flush();

	}

	// Check if guest count is valid
	private boolean validGuestsForRoom(int guests, int hotelRoomId) {

		HotelRoomDAO hrmdao = new HotelRoomDAO();
		return hrmdao.validGuestsForRoom(guests, hotelRoomId);
	}

	// Check if dates are valid
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
