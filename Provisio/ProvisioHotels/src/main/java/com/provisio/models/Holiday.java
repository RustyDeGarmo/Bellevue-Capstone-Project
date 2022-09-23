package com.provisio.models;

public enum Holiday {

	CHRIMAS("12-25", 0.05), FOURTH_OF_JULY("07-04", 0.05), NEW_YEAR("01-01", 0.05);

	private String date;
	private double incrementRate;

	Holiday(String date, double incrementRate) {
		this.date = date;
		this.incrementRate = incrementRate;
	}

	public double getIncrementRate() {
		return this.incrementRate;
	}

	public String getDate() {
		return this.date;
	}
}
