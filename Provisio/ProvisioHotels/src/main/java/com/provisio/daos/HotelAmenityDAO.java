//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo
//Data access layer: Amentities 
package com.provisio.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.provisio.models.Amentity;
import com.provisio.models.HotelAmentity;
import com.provisio.utils.ConnectionUtil;

// DAO for hotel amenities
public class HotelAmenityDAO extends BaseDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	// Fetch data for a given amentiy
	public List<HotelAmentity> fetchAmenityData(List<Integer> amtIds) {

		List<HotelAmentity> hotelRooms = new ArrayList<>();

		if (amtIds.isEmpty()) {	return hotelRooms; }
		
		try {
			String sql = "SELECT DISTINCT\r\n"
					+ "    amt.name,\r\n"
					+ "    amt.icon,\r\n"
					+ "    hmt.price,\r\n"
					+ "    hmt.charge_type\r\n"
					+ "FROM\r\n"
					+ "    amentities amt\r\n"
					+ "INNER JOIN\r\n"
					+ "    hotel_amentity hmt\r\n"
					+ "ON\r\n"
					+ "    (\r\n"
					+ "        amt.id = hmt.amentity_id) WHERE hmt.id IN (%s) \r\n";
					
			sql = String.format(sql, amtIds.stream().map(v -> String.valueOf(v.byteValue())).collect(Collectors.joining(", ")));
						
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				
				HotelAmentity hamt = new HotelAmentity();
				hamt.setPrice(rs.getBigDecimal("hmt.price"));
				hamt.setChargeType(rs.getString("hmt.charge_type"));
				
				Amentity amt = new Amentity();
				amt.setName(rs.getString("amt.name"));
				amt.setIcon(rs.getString("amt.icon"));
				
				hamt.setAmentity(amt);
				
				hotelRooms.add(hamt);
				
			}
			
			return hotelRooms;
			
		} catch (Exception e) {
			e.printStackTrace();

			return hotelRooms;
		} finally {
			close(rs, ps, con);
		}
	}
}
