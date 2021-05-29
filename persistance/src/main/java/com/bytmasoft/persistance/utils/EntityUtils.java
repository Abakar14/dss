package com.bytmasoft.persistance.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bytmasoft.domain.model.interfaces.BaseEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EntityUtils<T extends BaseEntity> {

	public T setUpdateParams(T t, String appName) {
		t.setUpdatedProg(appName);
		t.setUpdatedOn(LocalDateTime.now());

		return t;
	}

}
