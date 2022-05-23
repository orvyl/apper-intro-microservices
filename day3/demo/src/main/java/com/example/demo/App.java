package com.example.demo;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RestController
    public static class ZooController {

        private static Map<String, Animal> animals = new HashMap<>();

        @PostMapping("animal")
        public Animal createAnimal(@RequestBody Animal animal) {
            animals.put(animal.getId(), animal);

            return animal;
        }

        @GetMapping("animal")
        public List<Animal> getAllAnimals() {
            return new ArrayList<>(animals.values());
        }

        @GetMapping("animal/{id}")
        public Animal getAnimal(@PathVariable String id) {
            return animals.get(id);
        }

        @PutMapping("animal/{id}")
        public void updateAnimal(@PathVariable String id, @RequestBody Animal animal) {
            animals.put(id, animal);
        }

        @DeleteMapping("animal/{id}")
        public void deleteAnimal(@PathVariable String id) {
            animals.remove(id);
        }

    }

    @Data
    public static class Animal {
        private String id;
        private String name;
    }

}
