package site.bjcoding.hospservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HospserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospserviceApplication.class, args);
	}

}
