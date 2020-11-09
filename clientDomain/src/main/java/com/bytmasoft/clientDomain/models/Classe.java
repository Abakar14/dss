package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.clientDomain.enums.ClasseType;
import com.bytmasoft.clientDomain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Classe extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1871735935804856233L;
	
	private String description;
	private String designation;
	
	
	private ClasseType type;
	
	@JsonIgnore
	private Set<Course> courses = new HashSet<>();
	
	public void addCourse(Course course){
		this.courses.add(course);
		course.getClasses().add(this);
	}
	
	public void removeCourse(Course course){
		this.courses.remove(course);
		course.getClasses().remove(this);
	}
	
	


}
