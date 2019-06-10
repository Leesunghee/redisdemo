package com.himalaya;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Service;

import java.util.Base64;


@Service
@EnableRedisHttpSession
public class LoginService {


    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    public String getSessionValue(String encodedSessionId, String key) {

        Session session = getStoredRedisSession(sessionIdToBase64Decode(encodedSessionId));

        if (session == null) {
            return "";
        } else {
            return asString(session.getAttribute(key));
        }
    }

    public Session getStoredRedisSession(final String decodedSessionId) {
        return sessionRepository.findById(decodedSessionId);
    }

    public String sessionIdToBase64Decode(final String encodedSessionId) {
        byte[] bytes = Base64.getDecoder().decode(encodedSessionId);
        return new String(bytes);
    }

    public static String asString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonStr = mapper.writeValueAsString(obj);

            System.out.println("jsonStr = " + jsonStr);
            return jsonStr;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
