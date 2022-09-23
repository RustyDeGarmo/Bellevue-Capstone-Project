//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo

package com.provisio.models;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

// Object model and getter/setter methods
public class HotelAmentity {

	private int id;
	@Expose(serialize = false, deserialize = false)
	private Hotel hotel;
	private Amentity amentity;
	private BigDecimal price;
	private String chargeType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Amentity getAmentity() {
		return amentity;
	}

	public void setAmentity(Amentity amentity) {
		this.amentity = amentity;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public Double getPriceDouble() {
		return price.doubleValue();
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

}
