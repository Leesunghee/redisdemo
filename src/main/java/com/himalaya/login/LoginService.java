package com.himalaya.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Service
public class LoginService<S extends Session> {

    @Autowired
    private SessionRepository sessionRepository;

    public String getSessionValue(String encodedSessionId, String sessionKey) {
        Map<String, Object> jsonMap = new HashMap<>();
        Session session = getStoredRedisSession(encodedSessionId);

        if (session != null) {
            jsonMap.put(sessionKey, session.getAttribute(sessionKey));
            return asString(jsonMap);
        }
        return "";
    }

    public String getSessionKeys(String encodedSessionId) {
        Map<String, Object> jsonMap = new HashMap<>();
        Session session = getStoredRedisSession(encodedSessionId);

        if (session != null) {
            jsonMap.put("keys", session.getAttributeNames());
            return asString(jsonMap);
        }
        return "";
    }

    public String getSessionValues(String encodedSessionId) {
        Map<String, Object> jsonMap = new HashMap<>();
        Session session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            for (String sessionKey: session.getAttributeNames()) {
                jsonMap.put(sessionKey, session.getAttribute(sessionKey));
            }
            return asString(jsonMap);
        }
        return "";
    }

    public void deleteSession(String encodedSessionId) {
        Session session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            sessionRepository.deleteById(sessionIdToBase64Decode(encodedSessionId));
        }
    }

    public void extendSessionExpirationTime(String encodedSessionId) {
        Session session = getStoredRedisSession(encodedSessionId);
        session.setAttribute("test", "11111");
        sessionRepository.save(session);

    }

    public String getSessionExpirationTime(String encodedSessionId) {
        Map<String, Object> jsonMap = new HashMap<>();
        Session session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            jsonMap.put("expirationTime", session.getMaxInactiveInterval());
            jsonMap.put("lastAccessTime", session.getLastAccessedTime());
            jsonMap.put("creationTime", session.getCreationTime());
            return asString(jsonMap);
        }
        return "";
    }

    public Session getStoredRedisSession(final String encodedSessionId) {
        return sessionRepository.findById(sessionIdToBase64Decode(encodedSessionId));
    }

    public String sessionIdToBase64Decode(final String encodedSessionId) {
        byte[] bytes = Base64.getDecoder().decode(encodedSessionId);
        return new String(bytes);
    }

    public static String asString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
