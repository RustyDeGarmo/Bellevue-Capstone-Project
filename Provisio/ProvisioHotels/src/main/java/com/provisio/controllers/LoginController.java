//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for log in
package com.provisio.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.provisio.daos.UserDAO;
import com.provisio.models.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")

// Controller to manage login
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String APP_URL = null;

	@Override
	public void init() throws ServletException {
		APP_URL = getServletContext().getInitParameter("appUrl");
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Remove session when logout request is not null
		if (request.getParameter("logout") != null) {
			session.removeAttribute("LOGGED_USER");
			response.sendRedirect(APP_URL + "/home");

			return;
		}

		// Forward to /home when logged in
		if (session.getAttribute("LOGGED_USER") != null) {
			response.sendRedirect(APP_URL + "/home");

			// Forward to login if not logged in our logging out
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Set login credentials
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDAO udao = new UserDAO();
		User loggedUser = udao.login(email, password);

		// If logged user is not null, get session
		if (loggedUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_USER", loggedUser);

			if (session.getAttribute("HOTEL_URL") != null) {
				String hotelURL = session.getAttribute("HOTEL_URL").toString();

				session.removeAttribute("LOGIN_MESSAGE");
				session.removeAttribute("HOTEL_URL");

				response.sendRedirect(hotelURL);
			} else {
				response.sendRedirect(APP_URL + "/home");
			}
		
		} else { // If login credentials are invalid
			request.setAttribute("error", "Invalid login credentials !");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}

	}

}
