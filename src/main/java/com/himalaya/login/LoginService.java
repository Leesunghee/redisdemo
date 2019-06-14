package com.himalaya.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Service
public class LoginService<S extends Session> {

    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    public String isValidSession(final String encodedSessionId) {
        Map<String, Object> jsonMap = new HashMap<>();
        S session = getStoredRedisSession(encodedSessionId);

        if (session != null) {
            jsonMap.put("isValidSession", "true");
        } else {
            jsonMap.put("isValidSession", "false");
        }
        return asString(jsonMap);
    }

    public String getSessionAttribute(final String encodedSessionId, final String name) {
        Map<String, Object> jsonMap = new HashMap<>();
        S session = getStoredRedisSession(encodedSessionId);

        if (session != null) {
            jsonMap.put(name, session.getAttribute(name));
            return asString(jsonMap);
        }
        return "";
    }

    public String getSessionAttributeNames(final String encodedSessionId) {
        Map<String, Object> jsonMap = new HashMap<>();
        S session = getStoredRedisSession(encodedSessionId);

        if (session != null) {
            jsonMap.put("names", session.getAttributeNames());
            return asString(jsonMap);
        }
        return "";
    }

    public String getSessionAllAttributes(final String encodedSessionId) {
        Map<String, Object> jsonMap = new HashMap<>();
        S session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            for (String name: session.getAttributeNames()) {
                jsonMap.put(name, session.getAttribute(name));
            }
            return asString(jsonMap);
        }
        return "";
    }

    public void deleteSession(final String encodedSessionId) {
        S session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            sessionRepository.deleteById(sessionIdToBase64Decode(encodedSessionId));
        }
    }

    public void setSessionAttribute(final String encodedSessionId, final String name, final Object value) {
        S session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            session.setAttribute(name, value);
            ((SessionRepository) sessionRepository).save(session);
        }
    }

    public void removeSessionAttribute(final String encodedSessionId, final String name) {
        S session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            session.removeAttribute(name);
            ((SessionRepository) sessionRepository).save(session);
        }
    }

    public void extendSessionExpirationTime(final String encodedSessionId) throws Exception {
        S session = getStoredRedisSession(encodedSessionId);

        if (session != null) {
            session.setMaxInactiveInterval(Duration.ofMinutes(30));
            ((SessionRepository) sessionRepository).save(session);
        }
    }

    public String getSessionExpirationTime(final String encodedSessionId) {
        Map<String, Object> jsonMap = new HashMap<>();
        S session = getStoredRedisSession(encodedSessionId);
        if (session != null) {
            jsonMap.put("expirationTime", session.getMaxInactiveInterval());
            jsonMap.put("lastAccessTime", session.getLastAccessedTime());
            jsonMap.put("creationTime", session.getCreationTime());
            return asString(jsonMap);
        }
        return "";
    }

    public S getStoredRedisSession(final String encodedSessionId) {
        S session = (S)sessionRepository.findById(sessionIdToBase64Decode(encodedSessionId));
        if (session != null) {
            session.setLastAccessedTime(Instant.now());
            return session;
        }
        return null;
    }

    public String sessionIdToBase64Decode(final String encodedSessionId) {
        byte[] bytes = Base64.getDecoder().decode(encodedSessionId);
        return new String(bytes);
    }

    public static String asString(final Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
