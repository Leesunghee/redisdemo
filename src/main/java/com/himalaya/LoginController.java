package com.himalaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String getLoginUsername() {
        return loginService.getLoginUsername();
    }

    @GetMapping("/test")
    public void setSession(HttpSession session) {
        System.out.println("session id :: " + session.getId());
        session.setAttribute("test", "himalaya");
    }
}
