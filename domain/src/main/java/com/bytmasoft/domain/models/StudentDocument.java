package com.bytmasoft.domain.models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mahamat Abakar
 * created on 13.11.2020 at 06:20:13
 * How to mapp Lob to String or byte[]?
 * see https://www.youtube.com/watch?v=uZXfZZ59cjU&t=456s
 * I have remarked that with Blob (image profile) in Base user
 *         the programm is slowly work for that reason as performance use this
 *         (Thorben Janssen) to manage Performance.
 * 
 * lob is used for very large object byte[] or String also you can use
 *         Lob for (java.sql.Blob & java.sql.clob)
 * Blob used image and binary and Clob used for large char or string
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class StudentDocument extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private String type;

	@OneToOne
	@MapsId
	private Student student;
	
		
	@Lob
	private String largeString;
	
	@Lob
	private byte [] content;

	@JsonProperty(value = "profile_picture")
	@Lob
	private byte[] profile_picture;
}
