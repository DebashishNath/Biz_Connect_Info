package auth_info.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import auth_info.service.UserDetailsImpl;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Value("${app.refreshExpirationDateInMs}")
	private int jwtRefreshExpirationDateInMs;

	@Value("${app.jwtTempExpirationMs}")
	private int jwtTempExpirationMs;

	private Key getSigningKey() {
		try {
			byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
			return Keys.hmacShaKeyFor(keyBytes);
		} catch (IllegalArgumentException | io.jsonwebtoken.io.DecodingException ex) {
			logger.error("Failed to decode JWT secret. Ensure it's a valid Base64 string. Exception: {}", ex.getMessage());
			throw new IllegalStateException("Invalid JWT secret key", ex);
		}
	}

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return generateTokenFromUsername(userPrincipal.getUsername());
	}

	public String doGenerateRefreshToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		try {
			return Jwts.builder()
					.setSubject(userPrincipal.getUsername())
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtRefreshExpirationDateInMs))
					.signWith(getSigningKey(), SignatureAlgorithm.HS512)
					.compact();
		} catch (Exception ex) {
			logger.error("Exception during refresh token generation: {}", ex.getMessage(), ex);
			return "Refresh Token Generation Failed";
		}
	}

	public String generateTokenFromUsername(String username) {
		try {
			return Jwts.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					.signWith(getSigningKey(), SignatureAlgorithm.HS256)
					.compact();
		} catch (Exception ex) {
			logger.error("Exception during access token generation: {}", ex.getMessage(), ex);
			return null;
		}
	}

	public String getUserNameFromJwtToken(String token) {
		try {
			return Jwts
					.parserBuilder()
					.setSigningKey(getSigningKey())
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (Exception ex) {
			logger.error("Error extracting username from token: {}", ex.getMessage(), ex);
			return null;
		}
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

	public String generateTempToken(String username) {
		try {
			return Jwts.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + jwtTempExpirationMs))
					.claim("type", "TEMP")
					.signWith(getSigningKey(), SignatureAlgorithm.HS256)
					.compact();
		} catch (Exception ex) {
			logger.error("Error generating temp token: {}", ex.getMessage(), ex);
			return null;
		}
	}

	public String getUserNameFromTempToken(String tempToken) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(getSigningKey())
					.build()
					.parseClaimsJws(tempToken)
					.getBody();

			// Optional: ensure this is a TEMP token
			String type = claims.get("type", String.class);
			if (!"TEMP".equals(type)) {
				logger.error("Invalid token type: {}", type);
				return null;
			}

			return claims.getSubject();

		} catch (ExpiredJwtException ex) {
			logger.error("Temp token expired: {}", ex.getMessage());
		} catch (JwtException | IllegalArgumentException ex) {
			logger.error("Invalid temp token: {}", ex.getMessage());
		}

		return null;
	}

}