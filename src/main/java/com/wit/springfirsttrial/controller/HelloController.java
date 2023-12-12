package com.wit.springfirsttrial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${instructor.name}")
    private String name;

    @Value("${instructor.surname}")
    private String surname;

    @GetMapping("/hi")
    public String sayHi(){
        return "Hello my lovely dear!!"+name+" "+surname;
    }
}
