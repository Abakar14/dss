/**
 * 
 */
package com.bytmasoft.um.utils;

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
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
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

	private Date now;

	private DirectEncrypter encrypter;

	private long TOKEN_EXPIRATIONTIME_SECONDS;

	private byte[] secretKey;

	@PostConstruct
	private void init() throws UnsupportedEncodingException, KeyLengthException {

		secretKey = secret.getBytes("UTF-8");

		encrypter = new DirectEncrypter(secretKey);

		TOKEN_EXPIRATIONTIME_SECONDS = Long.valueOf(AccessTokenValiditySecondsStr);

	}

//	public String generateToken(DSSUserDetails userDetail) throws JOSEException, UnsupportedEncodingException {
//		now = new Date();
//		JWTClaimsSet claims = new JWTClaimsSet.Builder().claim("email", userDetail.getEmail())
//				.claim("username", userDetail.getUsername()).jwtID(UUID.randomUUID().toString())
//				.expirationTime(new Date(now.getTime() + TOKEN_EXPIRATIONTIME_SECONDS)).issueTime(now).build();
//
//		return doGenerateToken(claims);
//	}

	/**
	 * @param claims
	 * @param loginname
	 * @return
	 * @throws JOSEException
	 * @throws UnsupportedEncodingException
	 */
//	private String doGenerateToken(JWTClaimsSet claims) throws JOSEException, UnsupportedEncodingException {
//
//		Payload payload = new Payload(claims.toJSONObject());
//
//		JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);
//
//		JWEObject jweObject = new JWEObject(header, payload);
//
//		jweObject.encrypt(encrypter);
//
//		return jweObject.serialize();
//
//	}

	public String getUsernameFormToken(String token) throws ParseException, BadJOSEException, JOSEException {

		JWTClaimsSet claimsSet = getAllClaimsFromToken(token);
		return (String) claimsSet.getClaim("username");

	}

	public String getSubjectToken(String token) throws ParseException, BadJOSEException, JOSEException {

		return getClaimFromToken(token, JWTClaimsSet::getSubject);

	}

	public Date getIssuedAtDateFromToken(String token) throws ParseException, BadJOSEException, JOSEException {
		return getClaimFromToken(token, JWTClaimsSet::getIssueTime);
	}

//	public Date getIssuedAtDateFromToken(String token) {
//		return getClaimFromToken(token, Claims::getIssuedAt);
//	}

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

//	private Date toDate(LocalTime localTime) {
//		Instant instant = localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
//		return toDate(instant);
//
//	}

//	private Date toDate(Instant instant) {
//		BigInteger milis = BigInteger.valueOf(instant.getEpochSecond()).multiply(BigInteger.valueOf(1000));
//		milis = milis.add(BigInteger.valueOf(instant.getNano()).divide(BigInteger.valueOf(1_000_000)));
//
//		return new Date(milis.longValue());
//	}

//	public String refreshToken(String token, DSSUserDetails userDetail)
//			throws ParseException, BadJOSEException, JOSEException, UnsupportedEncodingException {
//
//		if (validateToken(token, userDetail)) {
//
//			return generateToken(userDetail);
//
//		}
//
//		return "token not valid ...";
//
//	}

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
