/**
 * 
 */
package com.bytmasoft.common.utils;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mahamat Abakar created on 19.11.2020 at 16:00:50
 */
@XmlRootElement
@AllArgsConstructor
@Data
public class ApiError {

	private LocalDateTime now;
	private String message;
	private String description;
	private int status;

}
