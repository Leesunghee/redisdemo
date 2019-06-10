package com.himalaya;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    private LoginService loginService;


    @Before
    public void setUp() {
        loginService = new LoginService();
    }

    @Test
    public void sessionIdToBase64DecodeTest() {
        String encodedSessionId = "ZDNlY2Q1YmUtNjgyNi00MDNiLWI4Y2YtNmViZTI5NGJmZTZi";
        assertThat(loginService.sessionIdToBase64Decode(encodedSessionId)).isEqualTo("d3ecd5be-6826-403b-b8cf-6ebe294bfe6b");
    }

    @Test
    public void getStoredRedisSessionTest() {
        String encodedSessionId = "MGQwZGIyZTUtZjNlNi00YTEyLTkyNWQtZWQxOTg3M2EwNmYz";
        Session session = sessionRepository.findById(loginService.sessionIdToBase64Decode(encodedSessionId));

        Assume.assumeTrue(session != null);
        assertThat((String) session.getAttribute("name")).isEqualTo("sunghee");
    }
}