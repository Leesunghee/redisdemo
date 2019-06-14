package com.himalaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;

@Configuration
public class AppConfig implements ApplicationRunner {

    @Autowired
    RedisOperationsSessionRepository redisOperationsSessionRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("redisOperationsSessionRepository :::" + redisOperationsSessionRepository);
        System.out.println("sessionRepository :::" + sessionRepository);

        if (redisOperationsSessionRepository == sessionRepository) {
            System.out.println("two objects is equal.!!");
        }

    }
}
