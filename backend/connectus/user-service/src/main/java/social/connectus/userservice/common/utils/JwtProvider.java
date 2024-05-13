package social.connectus.userservice.common.utils;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import social.connectus.userservice.common.type.JwtPayload;
import social.connectus.userservice.common.type.JwtPropertiesProvider;

@Component
public class JwtProvider {

	@Value("${spring.application.name}")
	private String issuer;

	@Value("${jwt.access-expiration}")
	private Long accessExpiration;

	private final SecretKey secretKey;

	public JwtProvider(@Value("${jwt.secret-key}") String secretKey) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
	}

	public String createAccessToken(JwtPayload jwtPayload) {

		String jwtIssuer = Optional.ofNullable(jwtPayload.getIssuer()).orElseGet(() -> issuer);
		Date jwtIssuedAt = Optional.ofNullable(jwtPayload.getIssuedAt()).orElseGet(Date::new);

		return Jwts.builder()
			.claim(JwtPropertiesProvider.PAYLOAD.getValue(), jwtPayload) // custom claim
			.issuer(jwtIssuer)
			.issuedAt(jwtIssuedAt)
			.expiration(new Date(jwtPayload.getIssuedAt().getTime() + accessExpiration))
			.signWith(secretKey, Jwts.SIG.HS512)
			.compact();
	}

	public JwtPayload verifyToken(String jwtToken) {
		Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
			.parseSignedClaims(jwtToken);

		String email = JwtPropertiesProvider.EMAIL.getValue();
		return new JwtPayload(claimsJws.getPayload().get(email, String.class), claimsJws.getPayload().getIssuedAt(), claimsJws.getPayload().getIssuer());
	}

}
