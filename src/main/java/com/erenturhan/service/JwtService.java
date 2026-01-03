package com.erenturhan.service;

import java.awt.RenderingHints.Key;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String SECRET_KEY = "Fg7aacpB1gJmfw97uZ+LesCY7Jm2cY0mCeqDUOXnk1Y=";
	
	public String generateToken(String username, String role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
				}
	
		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
		}
		
		
		public boolean isTokenValid(String token, String username) {
			final String extractedUsername = extractUsername(token);
			return (extractedUsername.equals(username) && !isTokenExpired(token));
			
		}
		
		
		private boolean isTokenExpired(String token) {
			return extractExpiration(token).before(new Date());
		}
		
		
		private Date extractExpiration(String token) {
			return extractClaim(token, Claims::getExpiration);
		}
		
		
		private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		}
		
		
		private Claims extractAllClaims(String token) {
			return Jwts.parserBuilder()
					.setSigningKey(getSignKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		}
		
		
		private java.security.Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
		
		}
		public String extractRole(String token) {
		    return extractClaim(token, claims -> claims.get("role", String.class));
		}

		
		
		
		
		
		
		
		

}
