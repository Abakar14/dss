package com.bytmasoft.clientDomain.models;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bytmasoft.clientDomain.enums.GenderType;
import com.bytmasoft.clientDomain.enums.UserType;
import com.bytmasoft.clientDomain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */

@Getter
@Setter
@NoArgsConstructor

public abstract class BaseUser extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7764311919115841229L;

	

	@JsonProperty(value = "status")
	@Size(max = 1)
	private String status;
	

	@JsonProperty(value = "gender")

	private GenderType gender;

	
	@NotNull
	@Size(min = 3, max = 50, message = "firstname must be between {min} and {max} characters long")
	
	private String firstName;


	@JsonProperty(value = "middelname")
	@Size(min = 1, message = "is required")
	private String middelName;


	@NotNull(message = "is required")
	@Size(min = 3, message = "is required")

	private String lastName;

	private EmailAddress emailAddress;


	@NotNull(message = "is required")
	@Size(min = 8, message = "is required")
	private String password;
	

	private String salt;

	@JsonProperty(value = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private LocalDate birthday;


	@JsonProperty(value = "lastlogin")
	private LocalDateTime lastLogin;

	
	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "type")	
	private UserType type;

	
	@JsonProperty(value = "age")
	private int age;

	@JsonProperty(value = "foto")
	private Blob foto;

	
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	
	public int getAge() {

		if (birthday != null) {
			LocalDate today = LocalDate.now();
			Period period = Period.between(birthday, today);
			return period.getYears();

		}
		return age;

	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(this.getId()).append(", firstname=").append(this.getFirstName())
				.append(", lastname=").append(this.getLastName()).append(", status=").append(this.getStatus())
				.append(", email=").append(this.emailAddress.toString()).append(", password=").append(this.getPassword())
				.append("]"
						);

		return builder.toString();
	}

	@Override
	public int hashCode() {

		final int primeNumber = 31;
		int resutl = 1;
		resutl = (primeNumber * resutl) + ((this.emailAddress.toString() == null) ? 0 : this.emailAddress.toString().hashCode());
		return resutl;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final BaseUser user = (BaseUser) obj;
		if (!this.emailAddress.toString().equals(user.emailAddress.toString())) {
			return false;
		}

		return true;

	}

}
