package ro.ucv.inf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ro.ucv.inf")
public class WebStatsTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStatsTwoApplication.class, args);
	}
}
