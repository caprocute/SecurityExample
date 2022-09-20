package com.untralvious.demo.security.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate redisTemplate;

    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setKeyValue(String key, String value, Long time ){
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
    }

    public Boolean checkValue(String key, String value){
        return redisTemplate.opsForValue().get(key).equals(value);
    }

    public String getValue(String key){
        return redisTemplate.opsForValue().get(key).toString();
    }

    public void delKey(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }
}
