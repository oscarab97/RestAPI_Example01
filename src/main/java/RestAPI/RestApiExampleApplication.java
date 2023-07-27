package RestAPI;

import RestAPI.Persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestApiExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestApiExampleApplication.class, args);
	}



	@Autowired
	ClienteRepository clienteRepository;
	@Override
	public void run(String... args) throws Exception {

	}
}
