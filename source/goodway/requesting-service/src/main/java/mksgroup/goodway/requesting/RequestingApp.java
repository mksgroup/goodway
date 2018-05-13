/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.requesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ThachLN
 */
@SpringBootApplication
@EnableEurekaClient
public class RequestingApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RequestingApp.class, args);
    }

}
