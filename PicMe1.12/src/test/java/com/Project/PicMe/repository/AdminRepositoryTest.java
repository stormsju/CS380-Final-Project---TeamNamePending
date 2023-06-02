package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void saveAdmin(){

        Admin admin = Admin.builder()
                .person(personRepository.findById(1001).orElse(null))
                .build();
        adminRepository.save(admin);
    }

    @Test
    public void findByPersonId(){
        System.out.println(adminRepository.findByPersonId(10090).orElse(null));
    }

    @Test
    public void deleteById(){
        adminRepository.deleteById(4);
    }
}
