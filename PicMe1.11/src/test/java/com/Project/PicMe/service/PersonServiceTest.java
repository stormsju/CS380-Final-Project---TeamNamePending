package com.Project.PicMe.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void findByUsername(){
        System.out.println(personService.findByUsername("JakeByTheLake"));
    }

    @Test
    public void updatePerson(){
        personService.updateStudent(1000, "", "", "JakeEarthquake", "","");
    }
}