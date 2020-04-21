package com.bytmasoft.um.configuration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
public class UmProperties {
	
	/**
	 * my host name
	 */
	@NotBlank
	private String hostname;
	
	/**
	 * my port name
	 */
	@Min(1000)
	@Max(65000)
	private int port;	

}
