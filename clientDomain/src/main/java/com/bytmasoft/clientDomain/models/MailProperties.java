package com.bytmasoft.clientDomain.models;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
public class MailProperties {
	
	/**
	 * my host name
	 */
	@NotBlank
	private String hostname;
	
	/**
	 * my port name
	 */
	@Min(500)
	@Max(65000)
	private int port;
	
	@Email
	private String from;
	
	private List<String> defualtRecipients;
	
	private Map<String, String> additionalHeaders;
								
	
	private Credentials crediential;

}
