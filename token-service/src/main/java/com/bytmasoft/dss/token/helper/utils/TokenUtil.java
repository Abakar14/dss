/**
 * 
 */
package com.bytmasoft.dss.token.helper.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */
@Component
public class TokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4474761911904944925L;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.token.lifetime}")
	private String AccessTokenValiditySecondsStr;

	private byte[] secretKey;

	@PostConstruct
	private void init() throws UnsupportedEncodingException, KeyLengthException {

		secretKey = secret.getBytes("UTF-8");

	}

	public String getUsernameFormToken(String token) throws ParseException, BadJOSEException, JOSEException {

		JWTClaimsSet claimsSet = getAllClaimsFromToken(token);
		if(claimsSet != null) {
			String username = (String) claimsSet.getClaim("username");
			
			if(username != null) {
				return username;
			}else {
				username = "";
			}
			
			return username;
		}else return "";

	}

	public String getSubjectToken(String token) throws ParseException, BadJOSEException, JOSEException {

		return getClaimFromToken(token, JWTClaimsSet::getSubject);

	}

	public Date getIssuedAtDateFromToken(String token) throws ParseException, BadJOSEException, JOSEException {
		return getClaimFromToken(token, JWTClaimsSet::getIssueTime);
	}


	public Date getExpirationDateFormToken(String token) throws ParseException, BadJOSEException, JOSEException {
		return getClaimFromToken(token, JWTClaimsSet::getExpirationTime);

	}

	private Boolean isTokenExpired(String token) throws ParseException, BadJOSEException, JOSEException {

		final Date expiration = getExpirationDateFormToken(token);
		return expiration.before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetail)
			throws ParseException, BadJOSEException, JOSEException {

		final String username = getUsernameFormToken(token);

		return (username.equals(userDetail.getUsername()) && !isTokenExpired(token));

	}


	public Boolean canTokenBeRefreshed(String token) throws ParseException, BadJOSEException, JOSEException {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	private Boolean ignoreTokenExpiration(String token) throws ParseException, BadJOSEException, JOSEException {
		// here you specify tokens, for that the expiration is ignored
//	 JWTClaimsSet claims = getAllClaimsFromToken(token);

		return false;
	}

	public <T> T getClaimFromToken(String token, Function<JWTClaimsSet, T> claimsResolver)
			throws ParseException, BadJOSEException, JOSEException {
		final JWTClaimsSet claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private JWTClaimsSet getAllClaimsFromToken(String token) throws ParseException, BadJOSEException, JOSEException {

		ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
		JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<SimpleSecurityContext>(secretKey);
		JWEKeySelector<SimpleSecurityContext> jweKeySelector = new JWEDecryptionKeySelector<SimpleSecurityContext>(
				JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
		jwtProcessor.setJWEKeySelector(jweKeySelector);

		return jwtProcessor.process(token, null);

	}

}
