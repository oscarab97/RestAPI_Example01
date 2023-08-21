package com.example.ProjectBBVA;

import com.example.ProjectBBVA.Model.Client;
import com.example.ProjectBBVA.Persistences.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProjectBbvaApplication implements CommandLineRunner {

	@Autowired
	ClientRepository clientRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProjectBbvaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		clientRepository.save(new Client(1L,"Oscar ","o.alvarezbarroso@ugto.mx",4621234564L,"Mexico",true));
		clientRepository.save(new Client(2L,"Jackie ","j.cardosom@ugto.mx",4627845612L,"Mexico",true));

	}
}
