//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for MyBookings /reservations
package com.provisio.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.provisio.daos.LoyaltyDAO;
import com.provisio.daos.ReservationDAO;
import com.provisio.models.Loyalty;
import com.provisio.models.Reservation;
import com.provisio.models.User;

/**
 * Servlet implementation class MyBookingsController
 */
@WebServlet("/my-bookings")

// Controller to show booking history and show loyalty points
public class MyBookingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Fetch user reservations
		User loggedUser = (User) session.getAttribute("LOGGED_USER");
		ReservationDAO rdao = new ReservationDAO();
		List<Reservation> userReservations = rdao.fetchUserReservations(loggedUser.getId(), null);
		request.setAttribute("userReservations", userReservations);
		
		// Set Loyalty points
		LoyaltyDAO ldao = new LoyaltyDAO();
		Loyalty loyaltyPoints = ldao.loyaltyPoints(loggedUser.getId());
		request.setAttribute("loyaltyPoints", loyaltyPoints);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/my-bookings.jsp");
		dispatcher.forward(request, response);

	}

}
