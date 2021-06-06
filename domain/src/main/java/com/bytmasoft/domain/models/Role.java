/**
 * 
 */
package com.bytmasoft.domain.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.bytmasoft.domain.enums.RoleType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
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
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Role extends BaseEntity {

	private static final long serialVersionUID = -8409570516606378132L;

	@Column(unique = true)
	private String name;

	@Enumerated(EnumType.STRING)
	private RoleType type;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "role_privilege", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "privilege_id", referencedColumnName = "id") })
	Set<Privilege> privileges = new HashSet<>();

//	@JsonIgnore
//	@Fetch(FetchMode.JOIN)
	@ManyToMany(mappedBy = "roles")
	private Set<Student> students = new HashSet<>();

//	@JsonIgnore
//	@Fetch(FetchMode.JOIN)
	@ManyToMany(mappedBy = "roles")
	private Set<Manager> managers = new HashSet<>();

//	@JsonIgnore
//	@Fetch(FetchMode.JOIN)
	@ManyToMany(mappedBy = "roles")
	private Set<Teacher> teachers = new HashSet<>();

//	@JsonIgnore
//	@Fetch(FetchMode.JOIN)
	@ManyToMany(mappedBy = "roles")
	private Set<Employee> employees = new HashSet<>();

//	@ManyToMany(mappedBy = "roles")
//	private Set<User> users = new HashSet<>();

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
		builder.append("Role [id=").append(this.getId()).append(", name=").append(this.getName()).append(", active=")
				.append(this.getActive()).append(", type=").append(this.getType()).append(", privileges=")
				.append(this.getPrivileges()).append("]");

		return builder.toString();
	}

}
