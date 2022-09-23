//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for Viewing reservation
package com.provisio.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.provisio.daos.ReservationDAO;
import com.provisio.models.Reservation;
import com.provisio.models.User;

/**
 * Servlet implementation class ViewReservationController
 */
@WebServlet("/view-reservation/*")

// Controller to view single existing reservation
public class ViewReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String info = request.getRequestURL().toString();
		String code = info.replaceAll(".*/", "");
		
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("LOGGED_USER");

		// Fetch reservation and send to reservation-details.jsp
		ReservationDAO rdao = new ReservationDAO();
		Reservation reservation = rdao.fetchReservationByCode(code);

		
		// If the reservation code is not valid or the reservation trying to be fetched does not belong to the logged user, then redirect back to my bookings page 
		if (reservation == null || reservation.getUser().getId() != loggedUser.getId()) {

			String APP_URL = getServletContext().getInitParameter("appUrl");

			response.sendRedirect(APP_URL + "/my-bookings");

			return;
		}
		
		request.setAttribute("reservation", reservation);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation-details.jsp");

		dispatcher.forward(request, response);
	}

}
