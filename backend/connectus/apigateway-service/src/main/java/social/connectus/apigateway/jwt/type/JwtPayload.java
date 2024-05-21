package social.connectus.apigateway.jwt.type;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JwtPayload {
	/**
	 * "payload": {
	 *     "email": "joonamin44@gmail.com",
	 *     "issuedAt": "May 13, 2024, 2:32:02 PM",
	 *     "issuer": "user-service"
	 *   },
	 */
	private String email;
	private Date issuedAt;
	private String issuer;
}
