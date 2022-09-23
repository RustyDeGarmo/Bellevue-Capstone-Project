//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for Hotel Filters
package com.provisio.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.provisio.daos.HotelsDAO;
import com.provisio.daos.RoomDAO;
import com.provisio.models.Hotel;
import com.provisio.models.Room;

/**
 * Servlet implementation class FilterHotelsController
 */
@WebServlet("/search-results")

// Controller to handle search requests
public class FilterHotelsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/search-results.jsp");

		String location = request.getParameter("location");

		// Filter locations
		Map<String, String> filters = new HashMap<>();
		filters.put("location", (location == null || location.isEmpty()) ? null : location);

		// Create room and hotel objects
		RoomDAO rdao = new RoomDAO();
		List<Room> allRooms = rdao.getAllRooms();

		HotelsDAO hdao = new HotelsDAO();
		List<Hotel> allHotels = hdao.fetchAllHotels(filters);
		List<String> locations = hdao.getHotelLocations();

		// Set attributes
		request.setAttribute("rooms", allRooms);
		request.setAttribute("hotels", allHotels);
		request.setAttribute("locations", locations);

		dispatcher.forward(request, response);
	}

}
