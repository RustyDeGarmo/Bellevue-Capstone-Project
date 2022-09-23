//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo

package com.provisio.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Define MySQL connection
public class ConnectionUtil {

	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/provisio_hotels", "root", "rBillie-0928t");
			} catch (SQLException ex) {

				System.out.println(ex);
			}
		} catch (ClassNotFoundException ex) {
			// log an exception. for example:
			System.out.println("Driver not found.");
		}
		return con;
	}

}
