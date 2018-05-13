/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.plan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ThachLN
 */
@SpringBootApplication
@EnableEurekaClient
public class PlanApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(PlanApp.class, args);
    }
}
