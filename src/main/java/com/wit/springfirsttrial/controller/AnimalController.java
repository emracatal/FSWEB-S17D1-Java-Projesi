package com.wit.springfirsttrial.controller;

import com.wit.springfirsttrial.entity.Animal;
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

    @GetMapping("/{id}")
    public Animal find(@PathVariable int id) {
        return animals.get(id);
    }

    @PostMapping("/")
    public Animal save(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return animal;
    }

    @PutMapping("/{id}")
    public Animal update(@PathVariable int id, @RequestBody Animal animal) {
        animals.put(id, new Animal(id, animal.getName()));
        return animals.get(id);
    }

    @DeleteMapping("{id}")
    public Animal remove(@PathVariable int id){
        return animals.remove(id);
    }
}
