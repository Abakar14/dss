/**
 * 
 */
package com.bytmasoft.domain.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Privilege extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1737704428174004440L;

	@Column(unique = true)
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "privileges")
	List<Role> roles = new ArrayList<>();

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Privilege [id=").append(this.getId()).append(", name=").append(this.getName())
				.append(", status=").append(this.getStatus()).append(", roles=").append(this.getRoles()).append("]");

		return builder.toString();
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

		final Privilege privilege = (Privilege) obj;
		if (!name.equals(privilege.getName())) {
			return false;
		}

		return true;

	}

}
