package com.wit.springfirsttrial.controller;

import com.wit.springfirsttrial.dto.AnimalResponse;
import com.wit.springfirsttrial.entity.Animal;
import com.wit.springfirsttrial.validation.AninalValidation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private Map<Integer, Animal> animals = new HashMap<>();

    @GetMapping("/")
    public List<Animal> findAll() {
        return animals.values().stream().toList();
    }
    //TODO validation ekle

    @GetMapping("/{id}")
    public AnimalResponse find(@PathVariable int id) {
        if(!AninalValidation.isIdValid(id)){
            return new AnimalResponse(null,"animal id is not valid",400);
        }
        if(!AninalValidation.isAnimalContains(animals,id)){
            return new AnimalResponse(null,"animal with given id is not exist " + id,400);
        }
        return new AnimalResponse(animals.get(id).getName(),"Success",200);
    }

    @PostMapping("/")
    public AnimalResponse save(@RequestBody Animal animal) {
        if(!AninalValidation.isAnimalCredentialsValid(animal)){
        return new AnimalResponse(null,"animal credentials are not valid",400);
        }
        animals.put(animal.getId(),animal);
        return new AnimalResponse(animals.get(animal.getId()).getName(),"success",200);
    }

    @PutMapping("/{id}")
    public AnimalResponse update(@PathVariable int id, @RequestBody Animal animal) {
        animal.setId(id);
        if(!AninalValidation.isIdValid(id)){
            return new AnimalResponse(null,"animal id is not valid",400);
        }
        if(!AninalValidation.isAnimalContains(animals,id)){
            return new AnimalResponse(null,"animal with given id is not exist " + id,400);
        }
        if(!AninalValidation.isAnimalCredentialsValid(animal)){
            return new AnimalResponse(null,"animal credentials are not valid",400);
        }
        animals.put(id, new Animal(id, animal.getName()));
        return new AnimalResponse(animals.get(id).getName(),"success",200);
    }

    @DeleteMapping("{id}")
    public AnimalResponse remove(@PathVariable int id){
        if(!AninalValidation.isIdValid(id)){
            return new AnimalResponse(null,"animal id is not valid",400);
        }
        if(!AninalValidation.isAnimalContains(animals,id)){
            return new AnimalResponse(null,"animal with given id is not exist " + id,400);
        }
        return new AnimalResponse(animals.remove(id).getName(), "success",200);
    }
}
