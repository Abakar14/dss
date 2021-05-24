package com.bytmasoft.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DSSFormat {
	

	public static LocalDateTime formateStringToLocalDateTime(String date, String format) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		LocalDateTime localdatetime = LocalDateTime.parse(date, formatter);

		
		return localdatetime;
	}

	
	public static String formateLocalDateTimeToString(LocalDateTime datetime, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

	     String formatDateTime = datetime.format(formatter);
		 
		 return formatDateTime;
	}
	
	public static String formateLocalDateToString(LocalDate date, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

	     String formatDateTime = date.format(formatter);
		 
		 return formatDateTime;
	}
	
	
	public static LocalDate formatStringToLocalDate(String stringDate, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		LocalDate localdate = LocalDate.parse(stringDate, formatter);
		
		return localdate;
		
	}
	

	


}
