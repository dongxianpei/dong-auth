package com.sansei.auth;

import com.sansei.boot.SanSeiApplication;
import com.sansei.boot.security.config.WebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.sansei", exclude = {WebSecurityAutoConfiguration.class})
public class DongAuthApplication {

    public static void main(String[] args) {
        SanSeiApplication.run(DongAuthApplication.class, args);
    }

}
