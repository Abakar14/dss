package com.bytmasoft.domain.models;

import com.bytmasoft.domain.model.interfaces.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Password extends BaseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8187952419153752529L;

	private String password;
	
	//lifetime muss bee configurable (3 month)
	private int lifetime;
	private String firstOldPassword;
	private String secondOldPassword;
	
	


}
