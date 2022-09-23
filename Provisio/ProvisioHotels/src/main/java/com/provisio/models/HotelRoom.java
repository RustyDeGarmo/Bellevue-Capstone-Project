//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo

package com.provisio.models;

import java.math.BigDecimal;

// Object model and getter/setter methods
public class HotelRoom {

	private int id;
	private Hotel hotel;
	private Room room;
	private int capacity;
	private BigDecimal price;

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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
