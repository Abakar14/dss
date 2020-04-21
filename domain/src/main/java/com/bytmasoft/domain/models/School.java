/**
 * @author Mahamat
 * Date 09.04.2020 : 14:16:33
 */
package com.bytmasoft.domain.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.enums.SchoolType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mahamat Date 09.04.2020 : 14:16:33
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class School extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1494506503868049157L;

	private String name;

	@ApiModelProperty(notes = "The api will generate the status")
	@JsonProperty(value = "status")
	@Size(max = 1)
	private String status;

	@JsonProperty(value = "description")
	@Size(max = 250)
	private String description;

	/**
	 * string size 5 chars
	 */
	@JsonProperty(value = "description")
	@Size(max = 5)
	private String prefix;

	@ApiModelProperty(allowEmptyValue = false)
	@JsonProperty(value = "SchoolType")
	@Column(name = "schooltype", nullable = false)
	@Enumerated(EnumType.STRING)
	private SchoolType type;

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToMany(mappedBy = "school")
	private List<User> users = new ArrayList<>();

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	public void addUser(User user) {
		this.users.add(user);
		user.setSchool(this);
	}

	@Override
	public int hashCode() {

		final int primeNumber = 31;
		int resutl = 1;
		resutl = (primeNumber * resutl) + ((name == null) ? 0 : name.hashCode());
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

		final Role role = (Role) obj;
		if (!name.equals(role.getName())) {
			return false;
		}

		return true;

	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Role [id=").append(this.getId()).append(", name=").append(this.getName()).append(", status=")
				.append(this.getStatus()).append(", type=").append(this.getType()).append(", privileges=").append("]");

		return builder.toString();
	}

}
