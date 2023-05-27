package com.Project.PicMe.service;

import com.Project.PicMe.entity.Friend;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FriendServiceTest {

    @Autowired
    FriendService friendService;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void saveFriendTest(){
        Person person1 = personRepository.findById(1000).orElse(null);
        Person person2 = personRepository.findById(1001).orElse(null);
        if(person1 == null || person2 == null){
            System.out.println("One of the people were not found in the database");
            return;
        }
        friendService.saveFriend(person1, person2);
    }

    @Test
    public void findFriendByIdTest(){
        Friend friends = friendService.findFriendById(1000,1001);
        System.out.println(friends);
    }

    @Test
    public void findFriendByPeopleTest(){
        Person personMaybe1 = personRepository.findById(1001).orElse(null);
        Person personMyabe2 = personRepository.findById(1000).orElse(null);
        if(personMyabe2 == null || personMaybe1 == null) return;

        System.out.println(friendService.findFriendByPeople(personMaybe1, personMyabe2));
    }
}