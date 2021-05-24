package com.bytmasoft.domain.utils;

public final class DSSMapping {

	public static final String STUDENTS = "students";
	public static final String TEACHERS = "Teachers";
	public static final String MANAGERS = "Managers";
	public static final String EMPLOYEES = "Employees";

	public static final String ADDRESSES = "addresses";
	public static final String CLASSES = "classes";
	public static final String COUNTRIES = "countries";
	public static final String ROLES = "roles";
	public static final String PRIVILEGES = "privileges";
	public static final String FILES = "files";
	public static final String DOCUMENTS = "documents";

	public static final class Singular {

		public static final String TEACHER = "Teacher";
		public static final String MANAGER = "Manager";
		public static final String STUDENT = "student";
		public static final String EMPLOYEE = "Employee";

		public static final String ADDRESSE = "address";
		public static final String COUNTRY = "country";

		public static final String ROLE = "role";
		public static final String PRIVILEGE = "privilege";
		public static final String FILE = "file";
		public static final String DOCUMENT = "document";
		public static final String CLASSE = "classe";

	}

	private DSSMapping() {

		throw new AssertionError();

	}

}
