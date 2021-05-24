package com.bytmasoft.clientDomain.model.interfaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public abstract class BaseEntity implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	LocalDateTime createdOn;

	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	LocalDateTime updatedOn;


	@JsonProperty(value = "status")
	@Size(max = 1)
	String status;

	
	Boolean deletestatus = false;

	
	String insertedBy;

	String insertedProg;


	String updatedBy;

	
	String updatedProg;



	private Long id;
	
	
	public void init() {
		this.createdOn = LocalDateTime.now();

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof BaseEntity))
			return false;

		BaseEntity that = (BaseEntity) obj;

		return this.id != null && Objects.equals(this.id, that.id);

	}

	@Override
	public int hashCode() {

		return 31;
	}

}
