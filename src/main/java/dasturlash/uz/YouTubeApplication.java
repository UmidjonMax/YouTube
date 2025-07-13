package dasturlash.uz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// bu yo‘q bo‘lishi kerak:
// @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class YouTubeApplication {
	public static void main(String[] args) {
		SpringApplication.run(YouTubeApplication.class, args);
	}

}
