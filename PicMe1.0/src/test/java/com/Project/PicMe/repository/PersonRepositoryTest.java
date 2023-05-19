package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Retrieves a list of users on the database
     * and prints it off to console
     */
    @Test
    public void printPeople(){
        List<Person> people = personRepository.findAll();
        for (Person p: people) {
            System.out.println(p.toString());
        }
    }

    /**
     * Can be used to insert test users
     * into the database.
     */
    @Test
    public void savePerson(){
        Person person = Person.builder()
                .fname("Test")
                .lname("User")
                .email("fake@Icloud.com")
                .date("11/11/1111")
                .build();
        personRepository.save(person);
    }

    @Test
    public void loadPeopleIntoDb(){
        List<Person> people = new ArrayList<>();
        people.add(
                Person.builder()
                .fname("Jake")
                .lname("Emerson")
                .email("EmersonJ@Icloud.com")
                .date("05/25/2001")
                .build()
                );
        people.add(
                Person.builder()
                        .fname("Cade")
                        .lname("Duboi")
                        .email("DuboiC@Icloud.com")
                        .date("08/10/1998")
                        .build()
        );
        people.add(
                Person.builder()
                        .fname("Ethan")
                        .lname("Clyde")
                        .email("ClydeE@outlook.com")
                        .date("01/01/2000")
                        .build()
        );
        people.add(
                Person.builder()
                        .fname("Thor")
                        .lname("Randall")
                        .email("RandallT@Icloud.com")
                        .date("09/20/1960")
                        .build()
        );
        people.add(
                Person.builder()
                        .fname("Charlotte")
                        .lname("Noel")
                        .email("NoelC@gmail.com")
                        .date("02/17/1987")
                        .build()
        );
        personRepository.saveAll(people);
    }

    @Test
    public void deletePerson(){
        personRepository.deleteById(1000);
    }


}