package mksgroup.goodway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"mksgroup.goodway.repository"})
//@EntityScan(basePackages = {"mksgroup.goodway.entity"})
public class GoodwayDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodwayDomainApplication.class, args);
	}
}
