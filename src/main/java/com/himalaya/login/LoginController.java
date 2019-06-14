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

    @GetMapping("/isValidSession")
    public String isValidSession(@RequestParam String sessionId) {
        return loginService.isValidSession(sessionId);
    }

    @GetMapping("/getSessionAttribute")
    public String getSessionAttribute(@RequestParam String sessionId, @RequestParam String name) {
        return loginService.getSessionAttribute(sessionId, name);
    }

    @GetMapping("/getSessionAllAttribute")
    public String getSessionAllAttributes(@RequestParam String sessionId) {
        return loginService.getSessionAllAttributes(sessionId);
    }

    @GetMapping("/getSessionAttributeNames")
    public String getSessionAttributeNames(@RequestParam String sessionId) {
        return loginService.getSessionAttributeNames(sessionId);
    }

    @GetMapping("/deleteSession")
    public void deleteSession(@RequestParam String sessionId) {
        loginService.deleteSession(sessionId);
    }

    @GetMapping("/setSessionAttribute")
    public void setSessionAttribute(@RequestParam String sessionId, @RequestParam String name, @RequestParam String value) {
        loginService.setSessionAttribute(sessionId, name, value);
    }

    @GetMapping("/removeSessionAttribute")
    public void removeSessionAttribute(@RequestParam String sessionId, @RequestParam String name) {
        loginService.removeSessionAttribute(sessionId, name);
    }

    @GetMapping("/extendSessionExpirationTime")
    public void extendSessionExpirationTime(@RequestParam String sessionId) throws Exception {
        loginService.extendSessionExpirationTime(sessionId);
    }

    @GetMapping("/getSessionExpirationTime")
    public String getSessionExpirationTime(@RequestParam String sessionId) {
        return loginService.getSessionExpirationTime(sessionId);
    }
}
