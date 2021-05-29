package com.bytmasoft.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.bytmasoft.common.utils.DSSFormat;
import com.bytmasoft.domain.models.BaseUser;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UserUtils<T extends BaseUser> {

	public String generateLoginname(T t) {

		String toconcat = "";
		int day = LocalDateTime.now().getDayOfMonth();
		if (day < 10) {
			toconcat = "0" + day;
		} else {
			toconcat = "" + day;
		}
		return t.getLastName().substring(0, t.getLastName().length() - 1).concat(t.getFirstName().substring(0, 1))
				.concat(toconcat).toUpperCase();

	}

	public T setUpdateParams(T t, String appName, String dateformat) {
		t.setUpdatedProg(appName);

		ZoneId zid = ZoneId.of("Europe/Paris");
		LocalDateTime time = LocalDateTime.now(zid);

		String dateStr = DSSFormat.formateLocalDateTimeToString(time, dateformat);

		t.setUpdatedOn(DSSFormat.formateStringToLocalDateTime(dateStr, dateformat));
		t.setUpdatedBy(t.getUsername());

		return t;
	}

	public T setUpdateParams(T t, String appName) {
		t.setUpdatedProg(appName);
		t.setUpdatedOn(LocalDateTime.now());
		t.setUpdatedBy(t.getUsername());
		return t;
	}

	public T setCreatedParams(T t, String appName) {
		t.setInsertedProg(appName);

		return t;
	}

}
