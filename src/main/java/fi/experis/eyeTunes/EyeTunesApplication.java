package fi.experis.eyeTunes;

import fi.experis.eyeTunes.dataAccess.models.Customer;
import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.util.List;
import java.util.Optional;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EyeTunesApplication {
	public static void main(String[] args) {
		SpringApplication.run(EyeTunesApplication.class, args);

	}

}
