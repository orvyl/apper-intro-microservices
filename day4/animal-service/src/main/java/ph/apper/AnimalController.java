package ph.apper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("animal")
public class AnimalController {

    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostMapping
    public CreateAnimalResponse createAnimal(@Valid @RequestBody CreateAnimalRequest request) {
        Animal animal = new Animal();
        animal.setName(request.getName());
        animal.setIsMammal(request.getIsMammal());

        Animal createdAnimal = animalRepository.save(animal);

        CreateAnimalResponse animalResponse = new CreateAnimalResponse();
        animalResponse.setId(createdAnimal.getId());

        return animalResponse;
    }

    @GetMapping("{id}")
    public GetAnimalResponse getAnimal(@PathVariable Integer id) throws AnimalNotFoundException {
        Optional<Animal> queryResult = animalRepository.findById(id);

        if (queryResult.isEmpty()) {
            throw new AnimalNotFoundException("Animal " + id + " not found");
        }

        Animal animal = queryResult.get();

        GetAnimalResponse response = new GetAnimalResponse();
        response.setId(animal.getId());
        response.setName(animal.getName());
        response.setIsMammal(animal.getIsMammal());

        return response;
    }

    @GetMapping("all")
    public List<GetAnimalResponse> getAll() {
        List<GetAnimalResponse> responses = new ArrayList<>();


        for (Animal animal : animalRepository.findAll()) {
            GetAnimalResponse response = new GetAnimalResponse();
            response.setId(animal.getId());
            response.setName(animal.getName());
            response.setIsMammal(animal.getIsMammal());

            responses.add(response);
        }

        return responses;
    }

    @GetMapping("by-name")
    public List<GetAnimalResponse> getByName(@RequestParam String name) {
        List<Animal> result = animalRepository.findByName(name);

        List<GetAnimalResponse> responses = new ArrayList<>();

        for (Animal animal : result) {
            GetAnimalResponse response = new GetAnimalResponse();
            response.setId(animal.getId());
            response.setName(animal.getName());
            response.setIsMammal(animal.getIsMammal());

            responses.add(response);
        }


        return responses;
    }

}




















