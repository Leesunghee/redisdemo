package com.himalaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Service;


@Service
@EnableRedisHttpSession
public class LoginService {

//    @Autowired
//    private ReactiveRedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    public String getLoginUsername() {

//        ReactiveHashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String sessionKey = "591881c4-7e85-4979-9591-991787830e92";
        String key = "name";
        String voKey = "userVo";

        Session session = sessionRepository.findById(sessionKey);
        String name = (String) session.getAttribute(key);
        
//        UserInfo2 userInfo = (UserInfo2) session.getAttribute(voKey);
//
//        System.out.println("userInfo.getUsername() = " + userInfo.getUsername());
//        System.out.println("userInfo.getAge() = " + userInfo.getAge());
//
//        System.out.println("name = " + name);


//        Flux<Map.Entry<Object, Object>> map =  hashOperations.entries(sessionKey);


//        System.out.println("username = " + hashOperations.entries(sessionKey));

//        System.out.println("username.get(key) = " + username.get(key));
//
//        return (String) username.get(key);

        return name;

    }
}
