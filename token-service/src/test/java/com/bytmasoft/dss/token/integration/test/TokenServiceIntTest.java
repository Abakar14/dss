package com.bytmasoft.dss.token.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.domain.models.EmailAddress;
import com.bytmasoft.dss.token.helper.models.UserPrincipal;
import com.bytmasoft.dss.token.helper.utils.TokenUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;

//@ConditionalOnBean({})
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserPrincipalDetailsService.class)
class TokenServiceIntTest {
	
	private static BaseUser teacher;
	private static String salt = "Aba14mah";
	private static UserPrincipal userPrincipal;
	private static String username = "ABAKAT06";
	private static String password = "Aba14";
	private static EmailAddress email;
	
	private static TokenUtil tokenUtil;
	
	private static String token = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..w6bUxOx-RDZ911-kItTc1g.76YkBWXU1YeNZ8vcgDoXoBNrgYfEjO_IAZrVp5bGKibpAypCfsE9OCNFxOnRBzTCikmlfhCHNDw3VCY7T87odFE-ifZf8wHFaoTDkd0y8AfGqpGfKu7QuX8ux6LD_HMmhQc25_i-pQUpupNdAx8PJxVB9VGcSgwXsJoSpg40oj8.aoEhjgQbP_k4KAJBOiqpAA";
	private static String status = "A";
	
	@BeforeAll
	private static void setup() {
		
		
		
		
	}

	@Test
	void testGetUsernameFormToken() {
		try {
			assertNotNull(tokenUtil);
			assertNotNull(tokenUtil.getUsernameFormToken(token));
			assertEquals(username, tokenUtil.getUsernameFormToken(token));

		} catch (ParseException | BadJOSEException | JOSEException e) {
			
			e.printStackTrace();
		}
		
	}

//	@Test
//	void testGetSubjectToken() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetIssuedAtDateFromToken() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetExpirationDateFormToken() throws ParseException, BadJOSEException, JOSEException {
//		assertNotNull(tokenUtil.getExpirationDateFormToken(token));
//	}
//
//	@Test
//	void testValidateToken() {
//		tokenUtil.validateToken(token, userDetail)
//	}
//
//	@Test
//	void testCanTokenBeRefreshed() {
//	}
//
//	@Test
//	void testGetClaimFromToken() {
////		tokenUtil.getClaimFromToken(token, claimsResolver)
//	}

}
