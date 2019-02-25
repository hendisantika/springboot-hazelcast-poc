package com.hendisantika.springboothazelcastpoc.config;

import com.hazelcast.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hazelcast-poc
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-25
 * Time: 07:55
 */
@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelConfig() {

        Config config = new Config();
        config.setInstanceName("hazel-test");
        config.getGroupConfig().setName("dev").setPassword("pass");
        return config;
    }
}