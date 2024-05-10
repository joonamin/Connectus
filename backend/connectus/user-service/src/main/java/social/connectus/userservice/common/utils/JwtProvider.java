package social.connectus.userservice.common.utils;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	@Value("${spring.application.name}")
	private String issuer;

	@Value("${service.jwt.access-expiration}")
	private Long accessExpiration;

	private final SecretKey secretKey;

	public JwtProvider(@Value("${service.jwt.secret-key}") String secretKey) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
	}

	public String createAccessToken(JwtPayload jwtPayload) {

		return Jwts.builder()
			.claim("email", jwtPayload)
			.issuer(issuer)
			.issuedAt(jwtPayload.issuedAt())
			.expiration(new Date(jwtPayload.issuedAt().getTime() + accessExpiration))
			.signWith(secretKey, Jwts.SIG.HS512)
			.compact();
	}

	public JwtPayload verifyToken(String jwtToken) {

		Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
			.parseSignedClaims(jwtToken);

		return new JwtPayload(claimsJws.getPayload().get(jwtPropertiesProvider.getEmailKey(), String.class), claimsJws.getPayload().getIssuedAt());
	}

}
