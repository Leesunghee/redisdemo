package com.himalaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;


@Service
@EnableRedisHttpSession
public class LoginService {

//    @Autowired
//    private ReactiveRedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    public String getLoginUsername() {

        String sessionId = "ZDNlY2Q1YmUtNjgyNi00MDNiLWI4Y2YtNmViZTI5NGJmZTZi";
        String key = "name";
        String voKey = "userVo";

        Session session = getStoredRedisSession(sessionId);
        if (session == null) {
            return "";
        } else {
            return (String) session.getAttribute(key);
        }
    }

    public Session getStoredRedisSession(final String encodedSessionId) {
        return sessionRepository.findById(sessionIdToBase64Decode(encodedSessionId));
    }

    public String sessionIdToBase64Decode(final String encodedSessionId) {
        byte[] bytes = Base64.getDecoder().decode(encodedSessionId);
        return new String(bytes);
    }
}
