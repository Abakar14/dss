package com.bytmasoft.domain.models;

public class EmailTest {

	public static void main(String[] args) {
//		EmailAddress email = new EmailAddress();
		
		Student s = new Student();
		s.setEmailAddress(new EmailAddress("abakar@gmail.de"));
		
		System.out.println(s.getEmailAddress().toString());

	}

}
