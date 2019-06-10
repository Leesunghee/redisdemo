package com.himalaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/getSessionValue")
    public String getSessionValue(@RequestParam String sessionId, @RequestParam String key) {
        return loginService.getSessionValue(sessionId, key);
    }

}
