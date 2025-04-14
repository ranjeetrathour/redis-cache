package com.example.service;

import com.example.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public String get(String id){
        final var resultFromCache = redisTemplate.opsForValue().get(id);
        if (resultFromCache!=null){
            return resultFromCache;
        }else {
            return null;
        }
    }

    public void set(String key, Object student, long ttl){
        try {
            String json  = objectMapper.writeValueAsString(student);
            redisTemplate.opsForValue().set(key,json,ttl, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
