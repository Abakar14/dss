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
import com.bytmasoft.domain.models.User;

/**
 * @author Mahamat Date 13.04.2020 : 23:05:44
 */
public class UserPredicates {

	public static Predicate<User> isRemerkedForDelete() {
		return u -> u.getDeletestatus().booleanValue() == true;
	}

	public static Predicate<User> isNotRemerkedForDelete() {
		return u -> u.getDeletestatus().booleanValue() == false;
	}

	public static Predicate<User> isTeacher() {
		return u -> u.getType().equals(UserType.TEACHER);
	}

	public static Predicate<User> isManager() {
		return u -> u.getType().equals(UserType.MANAGER);
	}

	public static Predicate<User> isStudent() {
		return u -> u.getType().equals(UserType.STUDENT);
	}

	public static Predicate<User> isParent() {
		return u -> u.getType().equals(UserType.PARENT);
	}

	public static List<User> filterUsers(List<User> users, Predicate<User> predicate) {

		return users.stream().filter(predicate).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public static Page<User> filterUsers(Page<User> users, Predicate<User> predicate) {

		return (Page<User>) users.stream().filter(predicate).collect(Collectors.toList());
	}
}
