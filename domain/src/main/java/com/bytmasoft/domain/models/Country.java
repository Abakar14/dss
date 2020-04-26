package com.bytmasoft.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Country extends BaseEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8386019607653261069L;

	@XmlElement
	@Column(nullable = false, unique = true)
	private String name;

	@XmlElement
	private String status;

	@XmlElement
	@Column(unique = true)
	private String abbreviations;

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
	private List<Address> addresses = new ArrayList<>();

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Country [id=").append(this.getId()).append(", name=").append(this.getName())
				.append(", abbreviations=").append(this.getAbbreviations()).append(", status=").append(this.getStatus())
				.append(", addresses=").append(this.getAddresses()).append("]");

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

		final Country country = (Country) obj;
		if (!name.equals(country.getName())) {
			return false;
		}

		return true;

	}

}
