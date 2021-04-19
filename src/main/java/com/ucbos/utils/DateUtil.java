package com.ucbos.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	/**
	 * "distributionOrderReleaseDate":"2017-03-14T21:07:04.823Z",
	 * 2017-08-04T20:29:38.251Z
	 * 
	 * @return
	 */
	public static String getTommrowDate() {

		Instant now = Instant.now();

		Instant futureDate = now.plus(1, ChronoUnit.DAYS);
		System.out.println("tommrow" + futureDate);

		return futureDate.toString();
	}

	public static String getFutureDate(int offsetDays) {

		Instant now = Instant.now();
		Instant futureDate = now.plus(offsetDays, ChronoUnit.DAYS);
		return futureDate.toString();
	}
	
	public static String getTodaysDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ssssss");
		sdf.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
		
		return sdf.format(new Date());
	}
	public static void main(String a[]){
		Instant now = Instant.now();
		
		System.out.println("."+getTodaysDate());
	}
}
