package com.Project.PicMe.service;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.repository.PersonRepository;
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
     *
     * @param person type Person, the person
     *               object that will be inserted into the database.
     * @return Returns the person object
     * that was inserted into the database.
     */
    public String insertPerson(Person person){
        String returnMessage = "Account successfully created.";

        //validity checks on fields of parameter person.
        if(checkUsernameAvailability(person.getUsername()) != null) return "The username: \""+person.getUsername() + "\" is already taken.";
        if(checkEmailAvailability(person.getEmail()) != null) return "The email address: \""+person.getEmail() + "\" is already associated with an account.";
        if(!validEmailAddress(person.getEmail()))  return "Invalid email entry.";

        person.setEmail(person.getEmail().toLowerCase());
        personRepository.save(person);
        return returnMessage;
    }


    public Person findPersonByUsername(String name){
        Optional<Person> person = Optional.ofNullable(personRepository.findByUsername(name));
        return person.orElse(null);
    }

    public List<Person> insertPeople(List<Person> personList){
        return personRepository.saveAll(personList);
    }

    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public Person findPersonById(int id){
        return personRepository.findById(id).orElse(null);
    }

    public boolean deletePerson(int id){
        if(findPersonById(id) != null){
            personRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public List<Person> findPersonsByName(String name){
        return personRepository.findAll()
                .stream()
                .filter(s -> s.getFname().equalsIgnoreCase(name) || s.getLname().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }


    public Person updatePerson(Person person){
        Person existingPerson = personRepository.findById(person.getId()).orElse(null);
        existingPerson.setFname(person.getFname());
        existingPerson.setLname(person.getLname());
        existingPerson.setEmail(person.getEmail());
        return personRepository.save(existingPerson);
    }

    //Helper Functions

    private Person checkEmailAvailability(String email){
        Optional<Person> personWithEmail = Optional.ofNullable(personRepository
                .findByEmail(email));
        return personWithEmail.orElse(null);
    }

    private Person checkUsernameAvailability(String username){
        Optional<Person> personWithUsername = Optional.ofNullable(personRepository
                .findByUsername(username));
        return personWithUsername.orElse(null);
    }

    private boolean validEmailAddress(String email){
        final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();

    }



}
