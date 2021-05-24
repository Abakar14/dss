package com.bytmasoft.domain.models;

public class EmailTest {

	public static void main(String[] args) {
//		EmailAddress email = new EmailAddress();
		
		Student s = new Student();
		s.setEmail("abakar@gmail.de");
		
		System.out.println(s.getEmail().toString());

	}

}
