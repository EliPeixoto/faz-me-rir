package br.com.fazmerir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FazmerirApplication {

	public static void main(String[] args) {
		SpringApplication.run(FazmerirApplication.class, args);
	}

}
