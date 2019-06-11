package com.himalaya;

import com.himalaya.login.LoginService;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.test.context.junit4.SpringRunner;

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
        String encodedSessionId = "NjYzMGRlYWYtMzQxOC00MDZjLWI5N2QtYTg5Y2U4YTExM2M5";
        assertThat(loginService.sessionIdToBase64Decode(encodedSessionId)).isEqualTo("6630deaf-3418-406c-b97d-a89ce8a113c9");
    }

    @Test
    public void getStoredRedisSessionTest() {
        String encodedSessionId = "N2M3ZDFhOWMtYTA5Ni00Y2VmLTk0M2YtYjM2ZTYxYjA3ODk3";
        Session session = sessionRepository.findById(loginService.sessionIdToBase64Decode(encodedSessionId));

        Assume.assumeTrue(session != null);
        assertThat((String) session.getAttribute("name")).isEqualTo("sunghee");
        assertThat(((UserInfo) session.getAttribute("userVo")).getUsername()).isEqualTo("himalaya");
    }
}