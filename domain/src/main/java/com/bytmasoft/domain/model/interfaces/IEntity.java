package com.bytmasoft.domain.model.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IEntity extends Serializable {

	Long getId();

	void setId(final Long id);

	LocalDateTime getCreatedOn();

	void setCreatedOn(LocalDateTime createdOn);

	LocalDateTime getUpdatedOn();

	void setUpdatedOn(LocalDateTime updatedOn);
}
