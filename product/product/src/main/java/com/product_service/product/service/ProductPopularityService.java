package com.product_service.product.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

public class ProductPopularityService {

	private static final String DAILY_KEY="popular:products:daily";
	
	private final RedisTemplate<String,String> redisTemplate;

	public ProductPopularityService(RedisTemplate<String, String> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}	
	
	public void init() {
		initKeyWithTTL(DAILY_KEY,Duration.ofDays(1));
	}
	
	private void initKeyWithTTL(String key,Duration ttl) {
		Boolean exists=redisTemplate.hasKey(key);
		if(Boolean.FALSE.equals(exists)) {
			redisTemplate.opsForZSet().add(key, "init",0);
			redisTemplate.expire(key,ttl);
		}
	}
	
	public void recordView(String productId) {
		redisTemplate.opsForZSet().incrementScore(DAILY_KEY, productId, 1);
	}
	
	public List<String> getTopDaily(int limit){
		Set<String> ids=redisTemplate.opsForZSet()
				.reverseRange(DAILY_KEY, 0, limit-1);
		
		return ids==null?List.of(): new ArrayList<>(ids);
	}
}
