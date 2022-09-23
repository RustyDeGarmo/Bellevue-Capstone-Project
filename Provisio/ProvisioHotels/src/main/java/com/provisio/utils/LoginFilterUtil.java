//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo

package com.provisio.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Forward to /home if not logged in
public class LoginFilterUtil implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		String APP_URL = req.getServletContext().getInitParameter("appUrl");

		HttpSession session = req.getSession();
		Object user = session.getAttribute("LOGGED_USER");

		if (user == null) {
			resp.sendRedirect(APP_URL + "/home");
		} else {
			chain.doFilter(request, response);
		}

	}

}
