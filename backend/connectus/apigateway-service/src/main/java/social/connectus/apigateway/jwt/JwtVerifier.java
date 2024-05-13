package social.connectus.apigateway.jwt;


public interface JwtVerifier {
	boolean verify(String token);
}
