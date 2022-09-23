//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Data access layer: Rooms
package com.provisio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.provisio.models.Room;
import com.provisio.utils.ConnectionUtil;

// DAO for rooms
public class RoomDAO extends BaseDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// Get all rooms from DB
	public List<Room> getAllRooms() {
		
		List<Room> rooms = new ArrayList<>();
		
		try {
			
			String sql = "SELECT * FROM rooms";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
			
				Room rm = new Room();
				rm.setType(rs.getString("type"));
				rm.setId(rs.getInt("id"));
				
				rooms.add(rm);
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, con);
		}
		
		return rooms;
	}
}
