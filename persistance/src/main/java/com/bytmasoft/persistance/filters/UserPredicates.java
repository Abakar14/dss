/**
 * @author Mahamat
 * Date 13.04.2020 : 23:05:44
 */
package com.bytmasoft.persistance.filters;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.bytmasoft.domain.models.BaseUser;

/**
 * @author Mahamat Date 13.04.2020 : 23:05:44
 */
public class UserPredicates {

	public static <T> Predicate<T> isRemerkedForDelete() {
		return u -> ((BaseEntity) u).getDeletestatus().booleanValue() == true;
	}

	public static <T> Predicate<T> isNotRemerkedForDelete() {
		return u -> ((BaseEntity) u).getDeletestatus().booleanValue() == false;
	}

	public static Predicate<BaseUser> isMachine() {
		return u -> u.getType().equals(UserType.MACHINE);
	}
	
	public static Predicate<BaseUser> isHuman() {
		return u -> u.getType().equals(UserType.HUMAN);
	}

	public static <T> List<T> filterUsers(List<T> users, Predicate<T> predicate) {

		return users.stream().filter(predicate).collect(Collectors.toList());
	}


	public static <T> List<T> filterPages(Page<T> users, Predicate<T> predicate) {

		return users.stream().filter(predicate).collect(Collectors.toList());
	}
}
