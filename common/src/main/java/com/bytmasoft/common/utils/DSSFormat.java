package com.bytmasoft.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DSSFormat {
	

	public static LocalDateTime formateToLocalDateTime(String date, String format) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		LocalDateTime formatDateTime = LocalDateTime.parse(date, formatter);

		
		return formatDateTime;
	}
	
	
	public static String formateToString(LocalDateTime datetime, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

	     String formatDateTime = datetime.format(formatter);
		 
		 return formatDateTime;
	}
	
	public static String formateToString(LocalDate date, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

	     String formatDateTime = date.format(formatter);
		 
		 return formatDateTime;
	}
	
	
	public static LocalDate formatToLocalDate(String stringDate, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		LocalDate localdate = LocalDate.parse(stringDate, formatter);
		
		return localdate;
		
	}
	
	public static LocalDateTime formatToLocalDateTime(String stringDate, String format) {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		LocalDateTime localdatetime = LocalDateTime.parse(stringDate, formatter);
		
		return localdatetime;		
		
	}
	


}
