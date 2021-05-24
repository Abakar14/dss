package com.bytmasoft.clientDomain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
public abstract class BaseUser extends BaseEntity {

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

	private String email;

//	@NotNull(message = "is required")
	@Size(min = 8, message = "password musst be min 8 characters")
	private String password;

	private String salt;

	@JsonProperty(value = "birthday")
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd.MM.yyyy")
	private LocalDate birthday;

	@JsonProperty(value = "lastlogin")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private LocalDateTime lastLogin;

	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "type")
	private UserType type;

	@JsonProperty(value = "age")
	private int age;
	private String phoneNr;
	private String mobilePhoneNr;

	@JsonProperty(value = "profile_picture")
	private byte[] profile_picture;

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
				.append(", email=").append(this.email).append(", password=").append(this.getPassword()).append("]");

		return builder.toString();
	}

	@Override
	public int hashCode() {

		final int primeNumber = 31;
		int resutl = 1;
		resutl = (primeNumber * resutl) + ((this.email == null) ? 0 : this.email.hashCode());
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
		if (!this.email.equals(user.email)) {
			return false;
		}

		return true;

	}

}
