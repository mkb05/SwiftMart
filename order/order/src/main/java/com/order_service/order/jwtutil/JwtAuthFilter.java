package com.order_service.order.jwtutil;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter  {

	private final String secret = "mySecretKey123456789012345678901234567890";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) {
		
		try {
			String authHeader=request.getHeader("Authorization");
			if(authHeader !=null && authHeader.startsWith("Bearer ")) {
				String token=authHeader.substring(7);
				
				Claims claims=Jwts.parser()
						.setSigningKey(secret.getBytes())
						.parseClaimsJws(token)
						.getBody();
				
				Long userId=claims.get("userId",Long.class);	
				
				request.setAttribute("userId", userId);
			}
		}
			catch(Exception e) {
				throw new RuntimeException("Invalid JWT Token");
			}
			
			try {
				filterChain.doFilter(request, response);
			}catch(Exception ignored) {}
		}
	}

