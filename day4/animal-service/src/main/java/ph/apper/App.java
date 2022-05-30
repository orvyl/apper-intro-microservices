package ph.apper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

//	@Bean
//	public CommandLineRunner run(AnimalRepository animalRepository) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//
//				Animal animal = new Animal();
//				animal.setName("Dog");
//				animal.setIsMammal(true);
//
//				animalRepository.save(animal);
//			}
//		};
//	}
}
