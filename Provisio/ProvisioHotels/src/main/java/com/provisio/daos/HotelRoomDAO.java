//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Data access layer: Hotel rooms
package com.provisio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.provisio.models.HotelRoom;
import com.provisio.models.Room;
import com.provisio.utils.ConnectionUtil;

// DAO for hotel room
public class HotelRoomDAO extends BaseDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// Fetch all rooms by hotel_id
	public List<HotelRoom> fetchHotelRoomsFor(int hotelId) {

		List<HotelRoom> hotelRooms = new ArrayList<>();

		try {
			String sql = "SELECT\r\n"
					+ "    hrm.id,\r\n"
					+ "    rm.type,\r\n"
					+ "    hrm.capacity,\r\n"
					+ "    hrm.price\r\n"
					+ "FROM\r\n"
					+ "    rooms rm\r\n"
					+ "INNER JOIN\r\n"
					+ "    hotel_room hrm\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        rm.id = hrm.room_id)\r\n"
					+ "WHERE\r\n"
					+ "    hrm.hotel_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, hotelId);
			rs = ps.executeQuery();

			while (rs.next()) {

				HotelRoom hr = new HotelRoom();

				Room room = new Room();
				room.setType(rs.getString("rm.type"));

				hr.setId(rs.getInt("hrm.id"));
				hr.setRoom(room);
				hr.setCapacity(rs.getInt("hrm.capacity"));
				hr.setPrice(rs.getBigDecimal("hrm.price"));

				hotelRooms.add(hr);
			}

			return hotelRooms;

		} catch (Exception e) {
			e.printStackTrace();
			return hotelRooms;
		} finally {
			close(rs, ps, con);
		}

	}
	
	// Fetch guests per room constraint
	public boolean validGuestsForRoom(int guests, int hotelRoomId) {

		try {
			
			String sql = "SELECT\r\n"
					+ "    hrm.capacity\r\n"
					+ "FROM\r\n"
					+ "    hotel_room hrm\r\n"
					+ "WHERE\r\n"
					+ "    hrm.id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, hotelRoomId);
			rs = ps.executeQuery();

			int capacity = 0;

			if (rs.next()) {
				capacity = rs.getInt("hrm.capacity");
			}

			return capacity >= guests;

		} catch (Exception e) {
			return false;
		} finally {
			close(rs, ps, con);
		}
		
	}
	
	// Fetch room data by room_id
	public HotelRoom fetchHotelRoomById(int id) {
		
		try {
			
			String sql = "SELECT\r\n"
					+ "    hrm.capacity,\r\n"
					+ "    hrm.price\r\n"
					+ "FROM\r\n"
					+ "    hotel_room hrm\r\n"
					+ "WHERE\r\n"
					+ "    hrm.id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			HotelRoom hrm = new HotelRoom();

			if (rs.next()) {

				hrm.setCapacity(rs.getInt("hrm.capacity"));
				hrm.setPrice(rs.getBigDecimal("hrm.price"));
			}

			return hrm;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, ps, con);
		}
	}

}
