package com.bytmasoft.persistance.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.domain.models.Student;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UserUtils <T extends BaseUser> {
	
	public  String generateLoginname(T t) {
		
		String toconcat = "";
		int day = LocalDateTime.now().getDayOfMonth();
		if (day < 10) {
			toconcat = "0" + day;
		} else {
			toconcat = "" + day;
		}
		return t.getLastName().substring(0, t.getLastName().length() - 1)
				.concat(t.getFirstName().substring(0, 1)).concat(toconcat).toUpperCase();

	}
	
	public T setUpdateParams(T t, String appName) {
		t.setUpdatedProg(appName);
		t.setUpdatedOn(LocalDateTime.now());
		t.setUpdatedBy(t.getUsername());
		return t;
	}

}
