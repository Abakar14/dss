/**
 * 
 */
package com.bytmasoft.configration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.PermissionClient;

/**
 * @author Mahamat Abakar created on 18.11.2020 at 00:02:10
 */
@Configuration
public class PermissionClientConfig {

	private String url = "http://localhost:8084/um/api/v1/";
//	private String url = "student-service/um/api/v1/";

	@Bean
	public PermissionClient permissionClient(WebClient webClient) {
		return new PermissionClient(webClient);
	}

	@Bean
	@ConditionalOnMissingBean
	public WebClient webClient() {
		return WebClient.builder()
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build())
				.baseUrl(url).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
	}

}
