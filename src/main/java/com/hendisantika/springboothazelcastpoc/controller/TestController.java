package com.hendisantika.springboothazelcastpoc.controller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hazelcast-poc
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-25
 * Time: 07:56
 */
@RestController
@RequestMapping(value = "api")
public class TestController {

    @Autowired
    private HazelcastInstance instance;

    @GetMapping("test")
    public String appName() {

        return "App1" + instance.getAtomicLong("long-test").incrementAndGet();
    }


}