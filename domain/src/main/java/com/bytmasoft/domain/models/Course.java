package com.bytmasoft.domain.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * like Mathe, physic 1, arabic a.s.on
 * @author Mahamat
 *
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Course extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1201363279901994614L;
	
	private String description;
	private String designation;
	
	@ManyToMany(mappedBy = "courses")
	private Set<Teacher> teachers = new HashSet<>();
	
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students = new HashSet<>();
	
	@ManyToMany(mappedBy = "courses")
	private Set<Classe> classes = new HashSet<>();
	
	
	
	

}
