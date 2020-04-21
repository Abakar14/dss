/**
 * @author Mahamat
 * Date 19.03.2020 : 10:23:20
 */
package com.bytmasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Mahamat Date 19.03.2020 : 10:23:20
 */
@SpringBootApplication
@EntityScan(basePackages = { "com.bytmasoft" })
@ComponentScan(basePackages = { "com.bytmasoft" })
public class DSSApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DSSApplication.class, args);

	}

}
