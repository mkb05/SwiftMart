package com.order_service.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.order_service.order.jwtutil.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class OrderConfig {

	private final JwtAuthFilter jwtAuthFilter;
	
	public OrderConfig(JwtAuthFilter jwtAuthFilter) {
		super();
		this.jwtAuthFilter = jwtAuthFilter;
	}
	
	@Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/**").permitAll()
				.anyRequest().authenticated()
				);
				return http.build();
	}

}
