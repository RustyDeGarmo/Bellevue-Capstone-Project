//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo

package com.provisio.models;

// Object model and getter/setter methods
public class ReservationAmentity {

	private Reservation reservation;
	private HotelAmentity hotelAmentity;

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public HotelAmentity getHotelAmentity() {
		return hotelAmentity;
	}

	public void setHotelAmentity(HotelAmentity hotelAmentity) {
		this.hotelAmentity = hotelAmentity;
	}

}
