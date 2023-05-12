package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void printPeople(){
        List<Person> people = personRepository.findAll();
        for (Person p: people) {
            System.out.println(p.toString());
        }
    }

    @Test
    public void savePerson(){
        Person person = Person.builder()
                .fname("Blake")
                .lname("Stohr")
                .email("Stohrb@Icloud.com")
                .date("08/10/1998")
                .build();
        personRepository.save(person);
    }

    @Test
    public void deletePerson(){
        personRepository.deleteById(2);
    }


}