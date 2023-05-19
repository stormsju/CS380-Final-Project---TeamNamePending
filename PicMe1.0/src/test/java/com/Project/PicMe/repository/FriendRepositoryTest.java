package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.FriendId;
import com.Project.PicMe.entity.Friend;
import com.Project.PicMe.entity.Person;
import jakarta.persistence.EmbeddedId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FriendRepositoryTest {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void addFriend(){
        Person person1 = personRepository.findById(1000).orElse(null);
        if(person1 == null) return;

        Person person2 = personRepository.findById(1001).orElse((null));
        if(person2 == null) return;

        Friend friend = new Friend();
        friend.setPerson1(person1);
        friend.setPerson2(person2);
        FriendId friendId = new FriendId();
        friendId.setPerson1Id(person1.getId());
        friendId.setPerson2Id(person2.getId());
        friend.setId(friendId);


        System.out.println(friendId.toString());
        System.out.println(friend.toString());

        friendRepository.save(friend);
    }

}