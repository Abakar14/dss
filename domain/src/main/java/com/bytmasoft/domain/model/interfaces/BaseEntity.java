package com.bytmasoft.domain.model.interfaces;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Value("${spring.application.name}")
//	private String appName;

	@ApiModelProperty(notes = "The api will generate the createdOn", hidden = true)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	LocalDateTime createdOn;

	@ApiModelProperty(notes = "The api will generate the updatedOn", hidden = true)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	LocalDateTime updatedOn;

	@ApiModelProperty(notes = "The api will generate the status")
	@JsonProperty(value = "status")
	@Size(max = 1)
	String status;

	@Column(nullable = true, columnDefinition = "boolean default false")
	Boolean deletestatus = false;

	@ApiModelProperty(notes = "The api will generate the insertedBy", hidden = true)
	String insertedBy;

	@ApiModelProperty(notes = "The api will generate the insertedProg", hidden = true)
	String insertedProg;

	@ApiModelProperty(notes = "The api will generate the updatedBy", hidden = true)
	String updatedBy;

	@ApiModelProperty(notes = "The api will generate the updatedProg", hidden = true)
	String updatedProg;

//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dssSeq")
//	@SequenceGenerator(name = "dssSeq", sequenceName = "DSS_SEQ", allocationSize = 1)
//    this will not work in this place muss be in the Entity class
	@ApiModelProperty(notes = "The database will generate the id", hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@PrePersist
	public void prePersist() {
		this.createdOn = LocalDateTime.now();
//		this.insertedBy = appName;

	}

	@PreUpdate
	public void preUpdate() {
		this.updatedOn = LocalDateTime.now();
//		this.updatedProg = appName;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof BaseEntity))
			return false;

		BaseEntity that = (BaseEntity) obj;

		return this.id != null && Objects.equals(this.id, that.id);

	}

	@Override
	public int hashCode() {

		return 31;
	}

}
