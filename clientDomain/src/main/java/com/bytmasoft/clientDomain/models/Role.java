/**
 * 
 */
package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.clientDomain.enums.RoleType;
import com.bytmasoft.clientDomain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mahamat Abakar Date 23.12.2019
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Role extends BaseEntity {

	private static final long serialVersionUID = -8409570516606378132L;

	private String name;

	private RoleType type;

	Set<Privilege> privileges = new HashSet<>();

//	@JsonIgnore
	private Set<Student> students = new HashSet<>();

//	@JsonIgnore
	private Set<Manager> managers = new HashSet<>();

//	@JsonIgnore
	private Set<Teacher> teachers = new HashSet<>();

//	@JsonIgnore
	private Set<Employee> employees = new HashSet<>();

	public void addPrivilege(Privilege privilege) {

		this.privileges.add(privilege);
		privilege.getRoles().add(this);

	}

	public void removePrivilege(Privilege privilege) {

		this.privileges.remove(privilege);
		privilege.getRoles().remove(this);

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
				.append(this.getStatus()).append(", type=").append(this.getType()).append(", privileges=")
				.append(this.getPrivileges()).append("]");

		return builder.toString();
	}

}
