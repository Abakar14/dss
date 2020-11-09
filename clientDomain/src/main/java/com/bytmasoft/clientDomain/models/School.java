/**
 * @author Mahamat
 * Date 09.04.2020 : 14:16:33
 */
package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.clientDomain.enums.SchoolType;
import com.bytmasoft.clientDomain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mahamat Date 09.04.2020 : 14:16:33
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class School extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1494506503868049157L;

	private String name;

	@JsonProperty(value = "status")
	@Size(max = 1)
	private String status;

	@JsonProperty(value = "description")
	@Size(max = 250)
	private String description;

	/**
	 * string size 5 chars
	 */
	@JsonProperty(value = "prefix")
	@Size(max = 5)
	private String prefix;

	@JsonProperty(value = "SchoolType")
	private SchoolType type;

	private Set<Teacher> teachers = new HashSet<>();

	@JsonIgnore
	@XmlElement
	private Address address;

	@JsonProperty(value = "manager")
	private Manager manager;

	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
		teacher.getSchools().add(this);
	}

	public void removeTeacher(Teacher teacher) {
		this.teachers.remove(teacher);
		teacher.getSchools().remove(this);
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
