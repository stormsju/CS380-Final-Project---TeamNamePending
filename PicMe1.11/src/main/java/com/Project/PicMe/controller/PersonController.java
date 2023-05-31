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

    @GetMapping("/getAll")
    public List<Person> findAllPeople(){
        return personService.getPeople();
    }

    @GetMapping("/getById/{id}")
    public Person findPersonById(@PathVariable("id") int id){
        return personService.findById(id);
    }

    @GetMapping("/getByUsername/{username}")
    public Person findPersonByUserame(@PathVariable("username") String username){
        return personService.findByUsername(username);
    }

    @GetMapping("/getByEmail/{email}")
    public Person findPersonByEmail(@PathVariable("email") String email){
        return personService.findByEmail(email);
    }


    @DeleteMapping("/deleteById/{id}")
    public String  deletePerson(@PathVariable("id") int id){
        return personService.deletePersonById(id);
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
