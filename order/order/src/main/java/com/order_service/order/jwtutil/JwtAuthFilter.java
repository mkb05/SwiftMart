package com.order_service.order.jwtutil;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Collections;
import io.jsonwebtoken.security.Keys;
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
				
				Claims claims=Jwts.parserBuilder()
						.setSigningKey(secret.getBytes())
						.build()
						.parseClaimsJws(token)
						.getBody();
				
				String userIdStr=claims.getSubject();
				Long userId=Long.parseLong(userIdStr);
				
		
				
				//  Created Authentication and set it
				UsernamePasswordAuthenticationToken authentication =
				        new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				request.setAttribute("userId", userId);
			}
			filterChain.doFilter(request, response);
		}
			catch(Exception e) {
				throw new RuntimeException("Invalid JWT Token");
			}
			
			try {
				filterChain.doFilter(request, response);
			}catch(Exception ignored) {}
		}
	}

