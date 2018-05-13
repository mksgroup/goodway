/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * @author ThachLN
 */
@SpringBootApplication
@EnableEurekaClient
public class UsersApp {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(UsersApp.class, args);
    }

}
