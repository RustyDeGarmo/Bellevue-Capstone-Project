//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for Index 
package com.provisio.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.provisio.daos.HotelsDAO;
import com.provisio.models.Hotel;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/home")

// Controller to manage /home
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Fetch hotel lactions and featured hotels
		HotelsDAO hdao = new HotelsDAO();
		List<String> locations = hdao.getHotelLocations();
		List<Hotel> featuredHotels = hdao.fetchFeaturedHotels();
		request.setAttribute("HOTEL_LOCATIONS", locations);
		request.setAttribute("FEATURED_HOTELS", featuredHotels);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		
		dispatcher.forward(request, response);

	}

}
