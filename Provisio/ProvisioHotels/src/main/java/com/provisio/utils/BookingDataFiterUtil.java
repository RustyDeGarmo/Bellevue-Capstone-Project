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

// Filter for booking_data
public class BookingDataFiterUtil implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		String APP_URL = req.getServletContext().getInitParameter("appUrl");

		HttpSession session = req.getSession();
		Object data = session.getAttribute("BOOKING_DATA");

		if (data == null) {
			resp.sendRedirect(APP_URL + "/home");
		} else {
			chain.doFilter(request, response);
		}
	}

}
