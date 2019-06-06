package com.himalaya.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;

    public String getLoginUsername() {

        ReactiveHashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String sessionKey = "spring:session:sessions:0ab4a4aa-4ddf-466b-b877-c37421fa349c";
        String key = "name";

        Flux<Map.Entry<Object, Object>> map =  hashOperations.entries(sessionKey);


        System.out.println("username = " + hashOperations.entries(sessionKey));

//        System.out.println("username.get(key) = " + username.get(key));
//
//        return (String) username.get(key);

        return "test";

    }
}
