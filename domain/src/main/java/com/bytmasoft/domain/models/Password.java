package com.bytmasoft.domain.models;

import java.util.Date;

import com.bytmasoft.domain.model.interfaces.BaseEntity;

public class Password extends BaseEntity {
	
	
	private String password;
	
	//lifetime muss bee configurable (3 month)
	private int lifetime;
	private String firstOldPassword;
	private String secondOldPassword;
	
	
	private boolean isValid() {
		boolean isValid = false;
		
		
		return isValid;
		
	}

}
