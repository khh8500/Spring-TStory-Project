package site.metacoding.blogv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class Blogv3Application {

	public static void main(String[] args) {
		SpringApplication.run(Blogv3Application.class, args);

	}

}
