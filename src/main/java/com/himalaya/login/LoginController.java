package com.himalaya.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController{

    @Autowired
    LoginService loginService;

    @GetMapping("/getSessionValue")
    public String getSessionValue(@RequestParam String sessionId, @RequestParam String key) {
        return loginService.getSessionValue(sessionId, key);
    }

    @GetMapping("/getSessionValues")
    public String getSessionValues(@RequestParam String sessionId) {
        return loginService.getSessionValues(sessionId);
    }

    @GetMapping("/getSessionKeys")
    public String getSessionKeys(@RequestParam String sessionId) {
        return loginService.getSessionKeys(sessionId);
    }

    @GetMapping("/deleteSession")
    public void deleteSession(@RequestParam String sessionId) {
        loginService.deleteSession(sessionId);
    }

    @GetMapping("/extendSessionExpirationTime")
    public void extendSessionExpirationTime(@RequestParam String sessionId) {
        loginService.extendSessionExpirationTime(sessionId);
    }

    @GetMapping("/getSessionExpirationTime")
    public String getSessionExpirationTime(@RequestParam String sessionId) {
        return loginService.getSessionExpirationTime(sessionId);
    }
}
