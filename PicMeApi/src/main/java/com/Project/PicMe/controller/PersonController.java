package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public String addPerson(@RequestBody Person person){
        return personService.insertPerson(person);
    }

    @PostMapping("/addMany")
    public List<Person> addPeople(@RequestBody List<Person> people){
        return personService.insertPeople(people);
    }

    @GetMapping()
    public List<Person> findAllPeople(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable("id") int id){
        return personService.findById(id);
    }

    @GetMapping("/username/{username}")
    public Person findPersonByUsername(@PathVariable("username") String username){
        return personService.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public Person findPersonByEmail(@PathVariable("email") String email){
        return personService.findByEmail(email);
    }


    @DeleteMapping("/delete/{id}")
    public void  deletePerson(@PathVariable("id") int id){
        personService.deletePersonById(id);
    }

    @PutMapping("/{id}")
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
