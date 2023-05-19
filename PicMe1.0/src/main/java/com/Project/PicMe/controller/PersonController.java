package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String printGreeing(){
        return "Hello from the api!";
    }

    @PostMapping("/addPerson")
    public Person addProduct(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PostMapping("/addPeople")
    public List<Person> addPeople(@RequestBody List<Person> people){
        return personService.savePersons(people);
    }

    @GetMapping("/people")
    public List<Person> findAllPeople(){
        return personService.getPersons();
    }

    @GetMapping("/person/{id}")
    public Person findPersonById(@PathVariable int id){
        return personService.findPersonById(id);
    }

    @GetMapping("/people/{name}")
    public List<Person> findPersonByName(@PathVariable String name){
        return personService.findPersonsByName(name);
    }

    @DeleteMapping("/deletePerson/{id}")
    public boolean deletePerson(@PathVariable int id){
        return personService.deletePerson(id);
    }

}
