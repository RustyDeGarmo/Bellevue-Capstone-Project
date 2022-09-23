//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Data access layer: Users/accounts
package com.provisio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.provisio.models.User;
import com.provisio.utils.ConnectionUtil;
import com.provisio.utils.PasswordUtil;

public class UserDAO extends BaseDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// Save user in DB
	public boolean signup(User user) {

		try {

			String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, PasswordUtil.encryptPassword(user.getPassword()));
			ps.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(rs, ps, con);
		}
	}

	// Fetch login password for user
	public User login(String email, String password) {

		User user = null;

		try {

			String sql = "SELECT * FROM users WHERE email = ?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String dbPass = rs.getString("password");
				if (PasswordUtil.isValidPass(password, dbPass)) {

					user = new User();
					user.setId(rs.getInt("id"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setEmail(rs.getString("email"));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}

		return user;
	}
}
