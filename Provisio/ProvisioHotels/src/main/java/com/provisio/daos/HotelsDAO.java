//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Data access layer: Hotels
package com.provisio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.provisio.models.Amentity;
import com.provisio.models.Attraction;
import com.provisio.models.Hotel;
import com.provisio.models.HotelAmentity;
import com.provisio.utils.ConnectionUtil;

// Hotel DAO
public class HotelsDAO extends BaseDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// Get hotel locations from DB
	public List<String> getHotelLocations() {

		List<String> locations = new ArrayList<>();

		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement("SELECT DISTINCT location FROM hotels");
			rs = ps.executeQuery();
			while (rs.next()) {
				locations.add(rs.getString("location"));
			}

			return locations;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, ps, con);
		}
	}
	
	// Fetch hotels by name
	public Hotel fetchHotelByName(String name) {

		try {
			
			String sql = "SELECT\r\n"
					+ "    hot.id,\r\n"
					+ "    hot.name,\r\n"
					+ "    hot.location,\r\n"
					+ "    hot.per_night,\r\n"
					+ "    hot.description,\r\n"
					+ "    CONCAT('[', GROUP_CONCAT(DISTINCT CONCAT('\"', img.img_url, '\"')), ']') AS images,\r\n"
					+ "    CONCAT('[', GROUP_CONCAT(DISTINCT JSON_OBJECT('name', attr.name, 'icon', attr.icon)), ']') AS hotel_attractions\r\n"
					+ "FROM\r\n"
					+ "    attractions attr\r\n"
					+ "LEFT JOIN\r\n"
					+ "    hotels hot\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        attr.hotel_id = hot.id)\r\n"
					+ "LEFT JOIN\r\n"
					+ "    images img\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        hot.id = img.hotel_id)        \r\n"
					+ "\r\n"
					+ "WHERE\r\n"
					+ "    hot.name LIKE ?\r\n"
					+ "GROUP BY hot.id ";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			Hotel hotel = null;
			
			if (rs.next()) {
				
				hotel = new Hotel();

				hotel.setId(rs.getInt("hot.id"));
				hotel.setName(rs.getString("hot.name"));
				hotel.setLocation(rs.getString("hot.location"));
				hotel.setPerNight(rs.getBigDecimal("hot.per_night"));
				hotel.setDescription(rs.getString("hot.description"));

				List<String> imageList = new Gson().fromJson(rs.getString("images"), new TypeToken<List<String>>() {
				}.getType());

				List<Attraction> attractions = new Gson().fromJson(rs.getString("hotel_attractions"), new TypeToken<List<Attraction>>() {
				}.getType());
								
				hotel.setImages(imageList);
				hotel.setAttractions(attractions);
				
			}
			
			return hotel;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, ps, con);
		}
	}

	// Fetch hotel amenities by hotel
	public List<HotelAmentity> fetchHotelAmentities(int hotelId) {

		List<HotelAmentity> hotelAmentities = new ArrayList<>();	
		
		try {
			
			String sql = "SELECT\r\n"
					+ "    amt.name,\r\n"
					+ "    amt.icon,\r\n"
					+ "    ham.id,\r\n"
					+ "    ham.price,\r\n"
					+ "    ham.charge_type\r\n"
					+ "FROM\r\n"
					+ "    hotel_amentity ham\r\n"
					+ "INNER JOIN\r\n"
					+ "    amentities amt\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        ham.amentity_id = amt.id)\r\n"
					+ "WHERE\r\n"
					+ "    ham.hotel_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, hotelId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				HotelAmentity hamt = new HotelAmentity();
				
				Amentity amt = new Amentity();
				amt.setName(rs.getString("amt.name"));
				amt.setIcon(rs.getString("amt.icon"));
				
				hamt.setId(rs.getInt("ham.id"));
				hamt.setPrice(rs.getBigDecimal("ham.price"));
				hamt.setChargeType(rs.getString("ham.charge_type"));
				hamt.setAmentity(amt);
				
				hotelAmentities.add(hamt);
			}
			
			return hotelAmentities;
			
		} catch (Exception e) {
			e.printStackTrace();
			return hotelAmentities;
		} finally {
			close(rs, ps, con);
		}
	}
	
	// Fetch featured hotels
	public List<Hotel> fetchFeaturedHotels() {

		List<Hotel> featuredHotels = new ArrayList<>();

		try {
			
			String sql = "SELECT\r\n"
					+ "    hot.name,\r\n"
					+ "    hot.location,\r\n"
					+ "    hot.per_night,\r\n"
					+ "    hot.rate,\r\n"
					+ "    hot.description,\r\n"
					+ "      CONCAT('[',GROUP_CONCAT(CONCAT('\"', img.img_url, '\"')),']') AS images\r\n"
					+ "FROM\r\n"
					+ "    images img\r\n"
					+ "INNER JOIN\r\n"
					+ "    hotels hot\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        img.hotel_id = hot.id)\r\n"
					+ "WHERE hot.featured = 1        \r\n"
					+ "GROUP BY hot.id";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				Hotel hotel = new Hotel();

				hotel.setName(rs.getString("hot.name"));
				hotel.setLocation(rs.getString("hot.location"));
				hotel.setPerNight(rs.getBigDecimal("hot.per_night"));
				hotel.setDescription(rs.getString("hot.description"));
				hotel.setRate(rs.getInt("hot.rate"));

				List<String> imageList = new Gson().fromJson(rs.getString("images"), new TypeToken<List<String>>() {
				}.getType());

				hotel.setImages(imageList);

				featuredHotels.add(hotel);
			}

			return featuredHotels;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, ps, con);
		}
	}
	
	// Fetch all hotels
	public List<Hotel> fetchAllHotels(Map<String, String> filters) {

		List<Hotel> allHotels = new ArrayList<>();

		try {
			
			String sql = "SELECT\r\n"
					+ "    hot.name,\r\n"
					+ "    hot.location,\r\n"
					+ "    hot.per_night,\r\n"
					+ "    hot.rate,\r\n"
					+ "    CONCAT('[', GROUP_CONCAT(CONCAT('\\\"', img.img_url, '\\\"')), ']') AS images\r\n"
					+ "FROM\r\n"
					+ "    images img\r\n"
					+ "INNER JOIN\r\n"
					+ "    hotels hot\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        img.hotel_id = hot.id)\r\n"
					+ "INNER JOIN\r\n"
					+ "    hotel_room hr\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        hot.id = hr.hotel_id)\r\n"
					+ "INNER JOIN\r\n"
					+ "    rooms rm\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        hr.room_id = rm.id)\r\n"
					+ "WHERE\r\n"
					+ "    COALESCE(?, rm.type) = rm.type\r\n"
					+ " AND (IF(? IS NOT NULL AND ? IS NOT NULL, hot.per_night BETWEEN ? AND ?, 1=1))\r\n"
					+ " AND COALESCE(?, hr.capacity) <= hr.capacity\r\n"
					+ " AND COALESCE(?, hot.location) = hot.location\r\n"
					+ "GROUP BY hot.id";
			
			con = ConnectionUtil.getConnection();
			
			ps = con.prepareStatement(sql);

			ps.setString(1, filters.get("roomType"));
			ps.setString(2, filters.get("pricefrom"));
			ps.setString(3, filters.get("priceto"));
			ps.setString(4, filters.get("pricefrom"));
			ps.setString(5, filters.get("priceto"));
			ps.setString(6, filters.get("guests"));
			ps.setString(7, filters.get("location"));
			
			rs = ps.executeQuery();
			
			while (rs.next()) {

				Hotel hotel = new Hotel();

				hotel.setName(rs.getString("hot.name"));
				hotel.setLocation(rs.getString("hot.location"));
				hotel.setPerNight(rs.getBigDecimal("hot.per_night"));
				hotel.setRate(rs.getInt("hot.rate"));

				List<String> imageList = new Gson().fromJson(rs.getString("images"), new TypeToken<List<String>>() {
				}.getType());

				hotel.setImages(imageList);

				allHotels.add(hotel);
			}

			return allHotels;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, ps, con);
		}
	}
}
