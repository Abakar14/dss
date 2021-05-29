package com.bytmasoft.domain.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.bytmasoft.domain.enums.FileType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
@XmlRootElement
@Entity(name = "dssfiles")
public class FileDB extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String filename;
	private String description;
	/**
	 * mime type
	 */
	@Enumerated(EnumType.STRING)
	private FileType type;

	/**
	 * array of bytes, map to a BLOB
	 */
	@Lob
	private byte[] data;

	public FileDB(String name, FileType type, byte[] data) {
		this.filename = name;
		this.type = type;
		this.data = data;
	}

	/**
	 * @param filename2
	 * @param contentType
	 * @param bytes
	 */
	public FileDB(String filename2, String contentType, byte[] bytes) {
		this.filename = filename2;
		this.type = FileType.valueOf(contentType);
		this.data = bytes;
	}

	@JsonProperty(value = "student")
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;

	@JsonProperty(value = "teacher")
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@JsonProperty(value = "manager")
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "manager_id")
	private Manager manager;

	@JsonProperty(value = "employee")
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@JsonProperty(value = "contactPerson")
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "contactPerson_id")
	private ContactPerson contactPerson;

	/**
	 * @param contentType
	 */
	public void setType(String contentType) {
		// TODO Auto-generated method stub

	}

}
