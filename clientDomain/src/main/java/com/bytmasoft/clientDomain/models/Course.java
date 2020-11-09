package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.clientDomain.model.interfaces.BaseEntity;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Course extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1201363279901994614L;
	
	private String description;
	private String designation;
	
	
	private Set<Teacher> teachers = new HashSet<>();
	

	private Set<Student> students = new HashSet<>();
	
	
	private Set<Classe> classes = new HashSet<>();
	
	
	
	

}
