package fi.experis.eyeTunes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EyeTunesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EyeTunesApplication.class, args);
	}

}
