package com.Project.PicMe.service;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Person service class contains the
 * business logic for transactions of People
 * between the front end and back end
 */
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * An insert method to place a Person (user) into the database.
     * Checks business layer logic before doing so.
     *
     * @param person type Person, the person
     *               object that will be inserted into the database.
     * @return Returns the person object
     * that was inserted into the database.
     */
    public String insertPerson(Person person){

        //business logic

        personRepository.save(person);
        return "Account successfully added to database";
    }

    /**
     * An insert method to place multiple people into the database.
     * Checks business layer logic before doing so.
     *
     * @param personList A list of people to be placed into the database.
     * @return The list of people who were inserted into the database.
     */
    public List<Person> insertPeople(List<Person> personList){
        return personRepository.saveAll(personList);
    }


    /**
     * A get method to find a user in the database that matches username searched for.
     *
     * @param name The username of the person to search for.
     * @return Returns the Person if they were in the database, else
     * returns null.
     */
    public Person findPersonByUsername(String name){
        Optional<Person> person = personRepository.findByUsername(name);
        return person.orElse(null);
    }

    /**
     * A get method to find all users in the database.
     * @return A list of every user in the database.
     */
    public List<Person> getPeople(){
        return personRepository.findAll();
    }

    /**
     * A get method to find a user given the id of person.
     * @param id The id to search for in the database, integer.
     * @return The person if found, else null.
     */
    public Person findPersonById(int id){
        return personRepository.findById(id).orElse(null);
    }


    /**
     * A delete method to remove a Person from database with given id.
     * @param id The id of Person that will be removed from the database.
     * @return A status message of deletion.
     */
    public String deletePersonById(int id){

        Optional<Person> personOptional = Optional.ofNullable(personRepository.findById(id).orElse(null));
        if(personOptional.isPresent()){
            personRepository.deleteById(id);
            return "Person was successfully removed from database.";
        }
        return "No person in the database with id: "+id;
    }


    /**
     * An update method to change a given field on a certain Person.
     * @param id Expected id of Person intending to alter.
     * @param firstName Changes the first name of person if given.
     * @param lastName Changes the last name of person if given.
     * @param username Changes the username of person if given.
     * @param password Changes the password of person if given.
     */
    @Transactional
    public void updateStudent(int id, String firstName, String lastName, String username,String email, String password){
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Perosn with id "+id+ " does not exist"));

        if(firstName != null
                && firstName.length() > 0
                && !person.getFname().equals(firstName)){
            person.setFname(firstName);
        }

        if(lastName != null
                && lastName.length() > 0
                && !person.getLname().equals(lastName)){
            person.setLname(lastName);
        }

        if(username != null
                && username.length() > 0
                && !person.getUsername().equals(username)
        ){
            Optional<Person> personWithUsername = personRepository.findByUsername(username);
            if(personWithUsername.isPresent()){
                throw new IllegalStateException("user name is already taken.");
            }
            person.setUsername(username);
        }

        if(email != null
                && email.length() > 0
                && !person.getEmail().equals(email)
        ){
            Optional<Person> personWithEmail = personRepository.findByEmail(email);
            if(personWithEmail.isPresent()){
                throw new IllegalStateException("email is already taken.");
            }
            person.setEmail(email);
        }

        if(password != null
                && password.length() > 0
                && !person.getPassword().equals(password)
        ){
            person.setPassword(password);
        }
    }



}
