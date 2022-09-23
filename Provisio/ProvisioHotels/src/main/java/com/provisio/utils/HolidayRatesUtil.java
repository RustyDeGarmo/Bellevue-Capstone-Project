package com.provisio.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.provisio.models.Holiday;

public class HolidayRatesUtil {

	public static BigDecimal addHolidayRates(String bookingDates, BigDecimal roomPrice) {

		BigDecimal rates = new BigDecimal(0);

		String[] dates = bookingDates.split("to");

		try {
			LocalDate checkin = LocalDate.parse(dates[0].trim());
			LocalDate checkout = LocalDate.parse(dates[1].trim());

			List<LocalDate> totalDates = new ArrayList<>();

			while (!checkin.isAfter(checkout)) {

				totalDates.add(checkin);
				checkin = checkin.plusDays(1);
			}

			DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd");

			for (LocalDate date : totalDates) {

				for (Holiday holiday : Holiday.values()) {

					if (holiday.getDate().equals(date.format(df))) { 
						rates = rates.add(roomPrice.multiply(BigDecimal.valueOf(holiday.getIncrementRate()))); 
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rates;
	}

}
