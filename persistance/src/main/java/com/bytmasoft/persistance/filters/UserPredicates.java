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
import com.bytmasoft.domain.models.User;

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

	public static Predicate<User> isMachine() {
		return u -> u.getUserType().equals(UserType.MACHINE);
	}

	public static Predicate<User> isHuman() {
		return u -> u.getUserType().equals(UserType.HUMAN);
	}

	public static <T> List<T> filterUsers(List<T> users, Predicate<T> predicate) {

		return users.stream().filter(predicate).collect(Collectors.toList());
	}

	public static <T> List<T> filterPages(Page<T> users, Predicate<T> predicate) {

		return users.stream().filter(predicate).collect(Collectors.toList());
	}
}
