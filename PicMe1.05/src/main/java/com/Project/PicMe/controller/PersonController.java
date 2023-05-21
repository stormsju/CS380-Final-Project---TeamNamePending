package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/addPerson")
    public String addProduct(@RequestBody Person person){
        return personService.insertPerson(person);
    }

    @PostMapping("/addPeople")
    public List<Person> addPeople(@RequestBody List<Person> people){
        return personService.insertPeople(people);
    }

    @GetMapping("/getPeople")
    public List<Person> findAllPeople(){
        return personService.getPersons();
    }

    @GetMapping("/personWithId/{id}")
    public Person findPersonById(@PathVariable int id){
        return personService.findPersonById(id);
    }

    @GetMapping("/peopleWithName/{name}")
    public List<Person> findPersonByName(@PathVariable String name){
        return personService.findPersonsByName(name);
    }

    @DeleteMapping("/deletePerson/{id}")
    public boolean deletePerson(@PathVariable int id){
        return personService.deletePerson(id);
    }

}
