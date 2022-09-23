//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Data access layer: Reservations
package com.provisio.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.provisio.models.Hotel;
import com.provisio.models.HotelAmentity;
import com.provisio.models.HotelRoom;
import com.provisio.models.Reservation;
import com.provisio.models.Room;
import com.provisio.models.User;
import com.provisio.utils.ConnectionUtil;
import com.provisio.utils.HolidayRatesUtil;

// Reservation DAO
public class ReservationDAO extends BaseDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// Calculate reservation price including amenities, user cannot make reservation for 0 days
	public String fetchCalculations(String bookingDates, int guests, int hotelRoomId, List<Integer> hotelAmts) {

		BigDecimal total = new BigDecimal(0);

		String[] dates = bookingDates.split("to");

		LocalDate checkIn = LocalDate.parse(dates[0].trim());
		LocalDate checkOut = LocalDate.parse(dates[1].trim());

		long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
		
		if(nights == 0) {
			nights = 1;
		}

		HotelRoomDAO hrdao = new HotelRoomDAO();
		HotelRoom hRoom = hrdao.fetchHotelRoomById(hotelRoomId);

		total = total.add(hRoom.getPrice()).multiply(BigDecimal.valueOf(nights));

		HotelAmenityDAO hadao = new HotelAmenityDAO();

		if (!hotelAmts.isEmpty()) {

			List<HotelAmentity> amenities = hadao.fetchAmenityData(hotelAmts);

			for (HotelAmentity amenity : amenities) {

				total = amenity.getChargeType().equalsIgnoreCase("per_night")
						? total.add(amenity.getPrice().multiply(BigDecimal.valueOf(nights)))
						: total.add(amenity.getPrice());
			}
		}
		
		// add holiday rates
		@SuppressWarnings("deprecation")
		BigDecimal roomHolidayAddition = HolidayRatesUtil.addHolidayRates(bookingDates, hRoom.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
		total = total.add(roomHolidayAddition.multiply(BigDecimal.valueOf(nights)));
		
	
		
		JsonObject json = new JsonObject();
		json.addProperty("nights", nights);
		json.addProperty("total", total);
		json.addProperty("roomPrice", hRoom.getPrice().add(roomHolidayAddition));

		return json.toString();
	}

	// Save reservation to DB
	public String saveReservation(Map<String, String> data, int userId) {

		try {

			String sqlh = "INSERT INTO reservations (guests, check_in, check_out, nights, total, booked_date, user_id, hotel_room_id, per_night) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String sqld = "INSERT INTO reservation_amentity (reservation_id, hotel_amentity_id) VALUES (?, ?)";

			String[] bookingDates = data.get("bookingDate").split("to");

			con = ConnectionUtil.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sqlh, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, Integer.parseInt(data.get("guests")));
			ps.setDate(2, Date.valueOf(bookingDates[0].trim()));
			ps.setDate(3, Date.valueOf(bookingDates[1].trim()));
			ps.setInt(4, Integer.parseInt(data.get("nights")));
			ps.setBigDecimal(5, new BigDecimal(data.get("total")));
			ps.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
			ps.setInt(7, userId); // user_id
			ps.setInt(8, Integer.parseInt(data.get("room_size"))); // room_id
			ps.setString(9, data.get("room_price")); // room_id

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			int pk = -1;
			String code = null;

			if (rs != null && rs.next()) {
				pk = rs.getInt(1);

				ps = con.prepareStatement(sqld); // Inserting Reservation Amenities

				for (Map.Entry<String, String> entry : data.entrySet()) {

					if (entry.getKey().contains("ham_")) {
						ps.setInt(1, pk);
						ps.setInt(2, Integer.parseInt(entry.getKey().split("_")[1]));
						ps.addBatch();
					}
				}

				ps.executeBatch();

			}

			con.commit();

			ps = con.prepareStatement("SELECT code FROM reservations WHERE id = ?"); // Fetch reservation code
			ps.setInt(1, pk);
			rs = ps.executeQuery();
			if (rs.next()) {
				code = rs.getString(1);
			}

			return code;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, ps, con);
		}

	}

	// Fetch reservation data by booking code
	public Reservation fetchReservationByCode(String code) {

		try {

			String sql = "SELECT\r\n" 
					+ "    rev.code,\r\n"
					+ "    rev.guests,\r\n"
					+ "    rev.check_in,\r\n"
					+ "    rev.check_out,\r\n" 
					+ "    rev.nights,\r\n" 
					+ "    rev.total,\r\n" 
					+ "    rev.status,\r\n"
					+ "    rev.user_id,\r\n"
					+ "    rev.per_night,\r\n" //
		            + "    hot.name,\r\n" 
					+ "    hot.location,\r\n" 
		            + "    hr.capacity,\r\n" 
					+ "    hr.price,\r\n"
					+ "    rm.type,\r\n"
					+ "    CONCAT('[', GROUP_CONCAT(JSON_OBJECT('amentity', JSON_OBJECT('name', amt.name, 'icon', amt.icon), 'price', ham.price,\r\n"
					+ "    'chargeType', ham.charge_type)), ']') AS amenities\r\n"
					+ "FROM\r\n"
					+ "    reservation_amentity hrm\r\n"
					+ "INNER JOIN\r\n"
					+ "    reservations rev\r\n"
					+ "ON\r\n"
					+ "    (\r\n" 
					+ "        hrm.reservation_id = rev.id)\r\n" 
					+ "INNER JOIN\r\n"
					+ "    hotel_amentity ham\r\n" 
					+ "ON\r\n" 
					+ "    (\r\n"
					+ "        hrm.hotel_amentity_id = ham.id)\r\n" 
					+ "INNER JOIN\r\n" 
					+ "    amentities amt\r\n"
					+ "ON\r\n" 
					+ "    (\r\n" 
					+ "        ham.amentity_id = amt.id)\r\n" 
					+ "INNER JOIN\r\n"
					+ "    hotel_room hr\r\n" 
					+ "ON\r\n" 
					+ "    (\r\n" 
					+ "        rev.hotel_room_id = hr.id)\r\n"
					+ "INNER JOIN\r\n" 
					+ "    hotels hot\r\n" 
					+ "ON\r\n" 
					+ "    (\r\n"
					+ "        hr.hotel_id = hot.id)\r\n" 
					+ "INNER JOIN\r\n" 
					+ "    rooms rm\r\n" 
					+ "ON\r\n"
					+ "    (\r\n" 
					+ "        hr.room_id = rm.id)\r\n" 
					+ "WHERE\r\n" 
					+ "    rev.code = ?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();

			Reservation reservation = null;

			if (rs.next()) {

				reservation = new Reservation();
				reservation.setGuests(rs.getInt("rev.guests"));
				reservation.setCheckin(rs.getDate("rev.check_in"));
				reservation.setCheckout(rs.getDate("rev.check_out"));
				reservation.setNights(rs.getInt("rev.nights"));
				reservation.setTotal(rs.getBigDecimal("rev.total"));
				reservation.setStatus(rs.getString("rev.status"));
				reservation.setCode(code);
				reservation.setPerNight(rs.getBigDecimal("rev.per_night"));
				
				HotelRoom hr = new HotelRoom();
				hr.setPrice(rs.getBigDecimal("hr.price"));
				hr.setCapacity(rs.getInt("hr.capacity"));
				hr.setPrice(rs.getBigDecimal("hr.price"));

				Room rm = new Room();
				rm.setType(rs.getString("rm.type"));
				hr.setRoom(rm);

				Hotel hot = new Hotel();
				hot.setName(rs.getString("hot.name"));
				hot.setLocation(rs.getString("hot.location"));
				hr.setHotel(hot);
				
				User resUser = new User();
				resUser.setId(rs.getInt("rev.user_id"));

				reservation.setUser(resUser);
				
				reservation.setHotelRoom(hr);

				Type amtType = new TypeToken<ArrayList<HotelAmentity>>() {
				}.getType();
				List<HotelAmentity> amenities = new Gson().fromJson(rs.getString("amenities"), amtType);

				reservation.setAmenities(amenities);

			}

			return reservation;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, ps, con);
		}

	}

	// Fetch all reservations by user_id and reservation code if exists
	public List<Reservation> fetchUserReservations(int id, String revCode) {

		List<Reservation> reservations = new ArrayList<>();
		try {
			String sql = "SELECT\r\n"
					+ "    rev.code,\r\n"
					+ "    rev.guests,\r\n"
					+ "    rev.check_in,\r\n"
					+ "    rev.check_out,\r\n"
					+ "    rev.nights,\r\n"
					+ "    rev.status,\r\n"
					+ "    rev.booked_date,\r\n"
					+ "    rev.total,\r\n"
					+ "    hot.name,\r\n"
					+ "    hot.location,\r\n"
					+ "    hr.capacity,\r\n"
					+ "    hr.price,\r\n"
					+ "    rm.type\r\n"
					+ "FROM\r\n"
					+ "    provisio_hotels.reservations rev\r\n"
					+ "INNER JOIN\r\n"
					+ "    provisio_hotels.hotel_room hr\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        rev.hotel_room_id = hr.id)\r\n"
					+ "INNER JOIN\r\n"
					+ "    provisio_hotels.hotels hot\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        hr.hotel_id = hot.id)\r\n"
					+ "INNER JOIN\r\n"
					+ "    provisio_hotels.rooms rm\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        hr.room_id = rm.id)\r\n"
					+ "WHERE\r\n"
					+ "    rev.user_id = ? ";
			
			if (revCode != null) {
				sql += " AND rev.code LIKE ?";
			}
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			if (revCode != null) {
				ps.setString(2, revCode);
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				Reservation res = new Reservation();
				res.setGuests(rs.getInt("rev.guests"));
				res.setCheckin(rs.getDate("rev.check_in"));
				res.setCheckout(rs.getDate("rev.check_out"));
				res.setNights(rs.getInt("rev.nights"));
				res.setTotal(rs.getBigDecimal("rev.total"));
				res.setStatus(rs.getString("rev.status"));
				res.setCode(rs.getString("rev.code"));
				res.setBookedDate(rs.getDate("rev.booked_date"));
				
				HotelRoom hrm = new HotelRoom();
				hrm.setCapacity(rs.getInt("hr.capacity"));
				hrm.setPrice(rs.getBigDecimal("hr.price"));
				
				Room room = new Room();
				room.setType(rs.getString("rm.type"));
				hrm.setRoom(room);
				
				Hotel hotel = new Hotel();
				hotel.setName(rs.getString("hot.name"));
				hotel.setLocation(rs.getString("hot.location"));
				
				hrm.setHotel(hotel);
				res.setHotelRoom(hrm);
				
				reservations.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close(rs, ps, con);
		}

		return reservations;
	}

}
