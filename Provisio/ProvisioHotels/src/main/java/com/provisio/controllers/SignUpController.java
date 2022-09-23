//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Controllers for new user sign up
package com.provisio.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import com.provisio.daos.UserDAO;
import com.provisio.models.User;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/signup")

// Controller to sign up for an account
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String APP_URL = getServletContext().getInitParameter("appUrl");

		// Forward to /home if already logged in
		if (session.getAttribute("LOGGED_USER") != null) {
			response.sendRedirect(APP_URL + "/home");
		} else { // Forward to sign-up page
			request.getRequestDispatcher("/signup.jsp").forward(request,
					response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Create new user
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);

		// Validate password
		try {
			ValidatorFactory validatorFactory = Validation
					.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			Set<ConstraintViolation<User>> constraintViolations = validator
					.validate(user);

			// if password not valid
			if (!constraintViolations.isEmpty()) {
				Map<String, String> errors = new HashMap<>();

				for (ConstraintViolation<User> constraintViolation : constraintViolations) {
					errors.put(constraintViolation.getPropertyPath().toString(),
							constraintViolation.getMessage());
				}

				request.setAttribute("user", user);
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/signup.jsp").forward(request,
						response);

			} else { // if password is valid, create user
				UserDAO udao = new UserDAO();
				udao.signup(user);

				request.setAttribute("signupSuccess",
						"You have successfully signed up.");
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
