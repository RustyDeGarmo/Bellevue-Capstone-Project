//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
package com.provisio.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.provisio.daos.ReservationDAO;
import com.provisio.models.Reservation;
import com.provisio.models.User;

/**
 * Servlet implementation class ReservationsFilterController
 */
@WebServlet("/filter-reservations")
public class ReservationsFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String revCode = request.getParameter("rev_code");
		
		HttpSession session = request.getSession();

		// Fetch user reservations
		User loggedUser = (User) session.getAttribute("LOGGED_USER");
		ReservationDAO rdao = new ReservationDAO();
		List<Reservation> userReservations = rdao.fetchUserReservations(loggedUser.getId(), (revCode == null || revCode.isEmpty()) ? null : revCode);
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setStatus(200);
		
		if (userReservations == null || userReservations.isEmpty()) {
			JsonArray data = new JsonArray();
			out.print(data.toString());
			
		} else {
			Gson gson = new Gson();

			Type type = new TypeToken<List<Reservation>>() {
			}.getType();

			String reservationData = gson.toJson(userReservations, type);

			out.print(reservationData);

		}

		out.flush();
	}

}
