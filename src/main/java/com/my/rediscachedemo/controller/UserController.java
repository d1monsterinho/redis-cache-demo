package com.my.rediscachedemo.controller;

import com.my.rediscachedemo.data.UserRepository;
import com.my.rediscachedemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users", key = "#id", unless = "#result.followers < 12000")
    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable String id) {
        LOG.info("Getting user with ID {}", id);
        return userRepository.findById(Long.parseLong(id)).get();
    }
}
