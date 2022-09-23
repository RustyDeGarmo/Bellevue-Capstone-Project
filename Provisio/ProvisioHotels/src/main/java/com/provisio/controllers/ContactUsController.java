//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//functional contact us
package com.provisio.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Servlet implementation class ContactUsController
 */
@WebServlet("/contact-us")
public class ContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		 // Check google docs for Sign in with App Passwords
		
		// https://support.google.com/accounts/answer/185833?p=InvalidSecondFactor
		
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String sender = request.getParameter("email");
		String msg = request.getParameter("message");
		String subject = request.getParameter("subject");

		String fullName = fname + " " + lname;

		String GMAIL_USERNAME = getServletContext().getInitParameter("gmailUsername"); // web.xml context params
		String GMAIL_PASSWORD = getServletContext().getInitParameter("gmailPassword"); // web.xml context params

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(GMAIL_USERNAME, GMAIL_PASSWORD);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("info@provisio.com"));
			message.setSubject(subject);
			message.setContent(
					"<!DOCTYPE html><html><head><style type='text/css'>table{width: 750px; border-collapse: collapse; margin:50px auto;}tr:nth-of-type(odd){background: #eee;}th{background: #3498db; color: white; font-weight: bold;}td, th{padding: 10px; border: 1px solid #ccc; text-align: left; font-size: 18px;}h2{background: #5B9600; border-radius: 6px 6px 6px 6px; box-shadow: 0 0 0 1px #5F5A4B, 1px 1px 6px 1px rgba(10, 10, 0, 0.5); color: #FFFFFF; font-size: 24px; font-weight: bold; height: 24px; line-height: 20px; margin: 12px 0 !important; padding: 20px; text-align: center; text-shadow: 2px 2px 3px #222222;}@media only screen and (max-width: 760px),(min-device-width: 768px) and (max-device-width: 1024px){table{width: 100%;}table, thead, tbody, th, td, tr{display: block;}tr{border: 1px solid #ccc;}td{border: none;border-bottom: 1px solid #eee; position: relative;padding-left: 50%;}td:before{position: absolute;top: 6px;left: 6px;width: 45%; padding-right: 10px; white-space: nowrap;/* Label the data */content: attr(data-column);color: #000;font-weight: bold;}}</style></head><body><h2>Contact Us Title</h2><table> <tbody> <tr> <td data-column='First Name'>Name &nbsp;&nbsp; : </td><td data-column='Last Name'>"
							+ fullName
							+ "</td></tr><tr> <td data-column='First Name'>Email &nbsp; &nbsp;: </td><td data-column='Last Name'>"
							+ sender
							+ "</td></tr><tr> <td data-column='First Name'>Subject &nbsp;: </td><td data-column='Last Name'>"
							+ subject
							+ "</td></tr><tr> <td data-column='First Name'>Message :</td><td data-column='Last Name'>"
							+ msg + "</td></tr></tbody></table></body></html>",
					"text/html");

			Transport.send(message);

			request.setAttribute("emailDelivered", "Thank you for getting in touch. We will get back to you soon !.");
			request.getRequestDispatcher("/about.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
