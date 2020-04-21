/**
 * @author Mahamat
 * Date 14.04.2020 : 18:16:13
 */
package com.bytmasoft.persistance.services;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bytmasoft.persistance.interfaces.DSSMailService;

import lombok.RequiredArgsConstructor;

/**
 * @author Mahamat Date 14.04.2020 : 18:16:13
 */
@RequiredArgsConstructor
@Service
public class DSSMailServiceImpl implements DSSMailService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4083129286543950151L;

	// TODO Mahamat 14.04.2020 18:18:25
	// Implemenet sendEmail
	@Override
	public boolean sendEmail(String reciepient) {
		if (reciepient != null && !reciepient.isEmpty()) {
			return true;

		} else {
			return false;
		}
	}

}
