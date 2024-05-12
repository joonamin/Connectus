package social.connectus.apigateway.jwt;


import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtVerifierImpl implements JwtVerifier {

	private final SecretKey key;

	public JwtVerifierImpl(@Value("${jwt.secret-key") String keyString) {
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyString));
	}

	@Override
	public boolean verify(String token) {
		Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

		// todo: interface 변경, 추후에 sanitize가 필요한 api를 고려하여, 필요한 claim을 뽑아내서 리턴하는 과정이 필요

		return true;
	}
}
