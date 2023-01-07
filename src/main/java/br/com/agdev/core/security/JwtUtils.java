package br.com.agdev.core.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
// TODO Turn methods statics
public class JwtUtils {

	private static final String JWT_REGEX = "^([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)";
	
	// TODO Extract key to properties
	private static final String PRIVATE_KEY = "?4]Xza2TC^#7!?\\\\x6G{G)/F@E}fhe'A?;EX[3U%Dfe.df}jG;#~=+?J@*bm/AL_K";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Claims extractAllClaims(String token) {
		JwtParser parser = Jwts.parserBuilder()
				.setSigningKey(PRIVATE_KEY.getBytes())
				.build();

		return parser.parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public boolean hasClaim(String token, String claimName) {
		Claims claims = extractAllClaims(token);
		return claims.get(claimName) != null;
	}

	public boolean hasCorrectFormat(String token) {
		if (token == null)
			return false;

		return token.matches(JWT_REGEX);
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);

		if (!hasCorrectFormat(token))
			return false;

		if (isTokenExpired(token))
			return false;

		if (!username.equals(userDetails.getUsername()))
			return false;

		return true;
	}

	public String generateToken(UserDetails userDetails) {
//		var base64PrivateKey = Base64.getEncoder().encodeToString(PRIVATE_KEY.getBytes());
		SecretKey key = Keys.hmacShaKeyFor(PRIVATE_KEY.getBytes());
		
		String token = Jwts.builder()
				.setSubject(userDetails.getUsername())
				.claim("authorities", userDetails.getAuthorities())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
//				.signWith(SignatureAlgorithm.HS256, base64PrivateKey)
				.signWith(key)
				.compact();

		return token;
	}
}
