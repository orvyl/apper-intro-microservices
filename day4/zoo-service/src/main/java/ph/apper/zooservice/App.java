package ph.apper.zooservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ph.apper.payloads.CreateAnimalRequest;
import ph.apper.payloads.CreateAnimalResponse;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public CommandLineRunner run() {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				RestTemplate restTemplate = new RestTemplate();
//
//				CreateAnimalRequest request = new CreateAnimalRequest();
//				request.setName("Dragon");
//				request.setIsMammal(false);
//
//				ResponseEntity<CreateAnimalResponse> entity = restTemplate.postForEntity("http://localhost:8080/animal", request, CreateAnimalResponse.class);
//				if (entity.getStatusCode().is2xxSuccessful()) {
//					CreateAnimalResponse body = entity.getBody();
//					System.out.println("New animal created with id " + body.getId());
//					return;
//				}
//
//				System.out.println("Sad!");
//			}
//		};
//	}
}
