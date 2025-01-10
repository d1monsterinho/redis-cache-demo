package com.my.rediscachedemo;

import com.my.rediscachedemo.data.UserRepository;
import com.my.rediscachedemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisCacheDemoApplication implements CommandLineRunner {

    private final Logger LOG = LoggerFactory.getLogger(RedisCacheDemoApplication.class);
    private final UserRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheDemoApplication.class, args);
    }

    @Autowired
    public RedisCacheDemoApplication(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Saving users. Current user count: {}", repo.count());
        User shubham = new User("Shubham", 2000);
        User pankaj = new User("Pankaj", 29000);
        User lewis = new User("Lewis", 550);

        repo.save(shubham);
        repo.save(pankaj);
        repo.save(lewis);

        LOG.info("Users saved. Data: {}", repo.findAll());
    }
}
