package com.bytmasoft.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.StringJoiner;
import java.util.regex.Pattern;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.util.Assert;

import com.bytmasoft.domain.enums.GenderType;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7764311919115841229L;

	private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

//	@ApiModelProperty(notes = "The api will generate the status")
//	@JsonProperty(value = "status")
//	@Size(max = 1)
//	private String status;

	@ApiModelProperty(allowEmptyValue = false)
	@JsonProperty(value = "gender")
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderType gender;

	@ApiModelProperty(allowEmptyValue = false)
//	@JsonProperty(value = "firstname")
	@NotNull
	@Size(min = 3, max = 50, message = "firstname must be between {min} and {max} characters long")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@ApiModelProperty(allowEmptyValue = true)
	@JsonProperty(value = "middelname")
	@Size(min = 1, message = "is required")
	private String middelName;

//	@JsonProperty(value = "lastname")
	@NotNull(message = "is required")
	@Size(min = 3, message = "is required")
	@Column(name = "last_name", nullable = false)
	private String lastName;

//	@JsonProperty(value = "email")
//	@Email
//	@Column(nullable = false, unique = true)
//	private String email;
//	@Embedded
	@Column(unique = true, nullable = false)
	private String email;

//	@JsonIgnore
//	@NotNull(message = "password is required")
	@Nullable
	@Size(min = 8, message = "password musst be min 8 characters")
	private String password;

//	@NotNull(message = "is required")
//	@Size(min = 9, max = 9, message = "is required")
	private String salt;

	@JsonProperty(value = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private LocalDate birthday;

	@ApiModelProperty(notes = "The api will generate the last login")
	@JsonProperty(value = "lastlogin")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@ApiModelProperty(notes = "The api will generate the username")
	@JsonProperty(value = "username")
	@Column(unique = true, nullable = false)
	private String username;

	@JsonProperty(value = "type")
	@Enumerated(EnumType.STRING)
	private UserType type;

	@ApiModelProperty(notes = "The api calcalte the age from the birthday", hidden = true)
	@JsonProperty(value = "age")
	@Transient
	private int age = 0;

	private String phoneNr;

	private String mobilePhoneNr;

	@JsonProperty(value = "profile_picture")
	@Lob
	private byte[] profile_picture;

	public void setPassword(String password) {

		this.password = password;
	}

	public void setEmail(String email) {
		Assert.isTrue(isValid(email), "Invalid email address!");
		this.email = email;

	}

	@PrePersist
	public abstract void generateUsername();

	public static boolean isValid(String candidate) {

		return candidate == null ? false : PATTERN.matcher(candidate).matches();
	}

	/**
	 * with @PrePersit and PreUpdate change the salt before insert into database
	 */
	public void generateSalt() {
		this.salt = RandomString.make(9);
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

		return new StringJoiner(";", this.getClass().getSimpleName() + "[", "]").add("id = " + this.getId())
				.add("firstname =" + this.getFirstName()).add("lastname = " + this.getLastName())
				.add("e-mail = " + this.getEmail()).add("status" + this.getStatus())
				.add("password" + this.getPassword()).add("type" + this.getType()).toString();

	}

	@Override
	public int hashCode() {

		return Objects.hashCode(this.getId(), this.getEmail(), this.getUsername());

	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof BaseUser) {
			BaseUser user = (BaseUser) obj;
			return Objects.equal(this.getId(), user.getId()) && Objects.equal(this.getEmail(), user.getEmail());
		}
		return false;
	}

}
