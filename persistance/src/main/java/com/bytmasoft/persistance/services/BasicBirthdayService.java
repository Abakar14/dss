/**
 * @author Mahamat
 * Date 19.03.2020 : 21:29:47
 */
package com.bytmasoft.persistance.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.bytmasoft.persistance.interfaces.BirthdayService;

/**
 * @author Mahamat Date 19.03.2020 : 21:29:47
 */
@Service
public class BasicBirthdayService implements BirthdayService {

	private final String dateFormat = "";

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

	@Override
	public LocalDate getValidBirthday(String birthdayString) {
		if (birthdayString == null) {
			throw new RuntimeException("Must include birthday");
		}
		try {
			LocalDate birthdate = LocalDate.parse(birthdayString, formatter);
			return birthdate;
		} catch (Exception e) {
			throw new RuntimeException("Must include valid birthday in yyyy-MM-dd format");
		}
	}

	@Override
	public String getBirthDOW(LocalDate birthday) {

		return birthday.getDayOfWeek().toString();
	}

	@Override
	public String getStarSign(LocalDate birthday) {
		int year = birthday.getYear();
		switch (year % 12) {
		case 0:
			return "Monkey";
		case 1:
			return "Rooster";
		case 2:
			return "Dog";
		case 3:
			return "Pig";
		case 4:
			return "Rat";
		case 5:
			return "Ox";
		case 6:
			return "Tiger";
		case 7:
			return "Rabbit";
		case 8:
			return "Dragon";
		case 9:
			return "Snake";
		case 10:
			return "Horse";
		case 11:
			return "Sheep";
		}
		return "";
	}

	/**
	 * @param formatter the formatter to set
	 */
	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

}
