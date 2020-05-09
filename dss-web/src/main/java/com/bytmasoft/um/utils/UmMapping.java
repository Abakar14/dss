package com.bytmasoft.um.utils;

/**
 * 
 * @author Mahamat Abakar Date 08.11.2019 20:51:46
 */
public final class UmMapping {

	public static final String USERS = "users";
	public static final String TEACHERS = "teachers";
	public static final String MANAGERS = "managers";
	public static final String STUDENTS = "students";
	public static final String countries = "countries";
	public static final String ADDRESSES = "addresses";
	public static final String LOGIN = "login";
	public static final String ROLES = "roles";
	public static final String PRIVILEGES = "privileges";
	public static final String SCHOOLS = "schools";

	public static final class Singular {
		public static final String USER = "user";
		public static final String ROLE = "role";
		public static final String PRIVILEGE = "privilege";
		public static final String country = "country";
		public static final String ADDRESS = "address";
		public static final String SCHOOL = "school";
		public static final String TEACHER = "teacher";
		public static final String MANAGER = "manager";
		public static final String STUDENT = "student";

	}

	private UmMapping() {

		throw new AssertionError();

	}

}
