package com.bytmasoft.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DSSFormat {
	

	public static LocalDateTime formateToLocalDateTime(String date, String format) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		LocalDateTime formatDateTime = LocalDateTime.parse(date, formatter);

		
		return formatDateTime;
	}
	
	
	public static String formateToString(LocalDateTime date, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

	     String formatDateTime = date.format(formatter);
		 
		 return formatDateTime;
	}
	


}
