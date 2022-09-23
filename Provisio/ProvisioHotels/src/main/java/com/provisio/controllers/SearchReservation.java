//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for MyBookings /reservations
package com.provisio.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.provisio.daos.LoyaltyDAO;
import com.provisio.daos.ReservationDAO;
import com.provisio.models.Loyalty;
import com.provisio.models.Reservation;
import com.provisio.models.User;

/**
 * Servlet implementation class MyBookingsController
 */
@WebServlet("/search")

// Controller to search for existing reservations
public class SearchReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Fetch user search input			
		String searchString = request.getParameter("reservationId");
		
		// Check if reservation exists
		ReservationDAO rdao = new ReservationDAO();
		Reservation reservation = rdao.fetchReservationByCode(searchString);
		
		Gson gson = new Gson();
		String json = gson.toJson(reservation);
	    JsonElement jsonElement = new JsonParser().parse(json);
	    JsonObject jsonObject = jsonElement.getAsJsonObject();
	    
	    if (jsonObject.has("checkin")) { // if found
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view-reservation/" + searchString);
			dispatcher.forward(request, response);
	    	} else { // if not found
				RequestDispatcher dispatcher = request.getRequestDispatcher("/my-bookings/?exists=false");
				dispatcher.forward(request, response);
	    	};


	}
}
