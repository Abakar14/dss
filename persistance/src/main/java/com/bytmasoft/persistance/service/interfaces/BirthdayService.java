/**
 * @author Mahamat
 * Date 19.03.2020 : 21:25:58
 */
package com.bytmasoft.persistance.service.interfaces;

import java.time.LocalDate;

/**
 * @author Mahamat Date 19.03.2020 : 21:25:58
 */
public interface BirthdayService {

	LocalDate getValidBirthday(String birthdayString);

	String getBirthDOW(LocalDate birthday);

	String getStarSign(LocalDate birthday);
}
