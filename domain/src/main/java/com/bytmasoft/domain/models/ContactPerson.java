package com.bytmasoft.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.enums.ContactPersonType;
import com.bytmasoft.domain.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class ContactPerson extends BaseUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -787584079024966039L;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "student_contact_persons", joinColumns = {
			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "student_id", referencedColumnName = "id") })
	List<Student> students = new ArrayList<Student>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "teacher_contact_persons", joinColumns = {
			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "teacher_id", referencedColumnName = "id") })
	List<Teacher> teachers = new ArrayList<Teacher>();
		
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "manager_contact_persons", joinColumns = {
			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "manager_id", referencedColumnName = "id") })
	List<Manager> managers = new ArrayList<Manager>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "contact_persion_address", joinColumns = {
			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private List<Address> addresses = new ArrayList<>();
	
	@JsonProperty(value = "type")
	@Enumerated(EnumType.STRING)
	private ContactPersonType contact_person_type;
	
	@Override
	public void setType(UserType type) {
		
		super.setType(null);
		this.setType(null);
	}

	
	

}
