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
    public String addPerson(@RequestBody Person person){
        return personService.insertPerson(person);
    }

    @PostMapping("/addPeople")
    public List<Person> addPeople(@RequestBody List<Person> people){
        return personService.insertPeople(people);
    }

    @GetMapping("/getPeople")
    public List<Person> findAllPeople(){
        return personService.getPeople();
    }

    @GetMapping("/getPersonById/{id}")
    public Person findPersonById(@PathVariable("id") int id){
        return personService.findPersonById(id);
    }

    @GetMapping("/getPersonByUsername/{username}")
    public Person findPersonByName(@PathVariable("username") String username){
        return personService.findPersonByUsername(username);
    }

    @DeleteMapping("/deletePerson/{id}")
    public String  deletePerson(@PathVariable("id") int id){
        return personService.deletePersonById(id);
    }

    @PutMapping("{id}")
    public void updatePerson(
        @PathVariable("id") int id,
        @RequestParam(required = false) String fname,
        @RequestParam(required = false) String lname,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String password
    ){
        personService.updateStudent(id, fname, lname, username, email, password);
    }
}
