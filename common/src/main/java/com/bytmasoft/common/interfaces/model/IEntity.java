package com.bytmasoft.common.interfaces.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IEntity extends Serializable{

	Long getId();
	
	void setId(final Long id);
	
	public String getStatus();
	
	void setStatus(String status);
	
	LocalDateTime getCreatedOn();
	
	void setCreatedOn(LocalDateTime createdOn);
	
	LocalDateTime getUpdatedOn();
	
	void setUpdatedOn(LocalDateTime updatedOn);
}
