package com.api_gateway.gateway.JwtUtil;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String 	SECRET="mySecretKey123456789012345678901234567890";
	private int EXPIRATION=24 * 60 * 60 * 1000;
	
	
	@SuppressWarnings("deprecation")
	public String generateToken(String email,Long userId) {
		return Jwts.builder()
				.setSubject(email)
				.claim("userId", userId)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
				.signWith(SignatureAlgorithm.HS256,SECRET)
				.compact();
	}
	
	@SuppressWarnings("deprecation")
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	//Get email from token
	public String getEmail(String token) {
		Claims claims=Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public Long getUserId(String token) {
		Claims claims=Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody();
		return Long.valueOf(claims.get("userId").toString());
	}
}
