package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.FriendId;
import com.Project.PicMe.entity.Friend;
import com.Project.PicMe.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FriendRepositoryTest {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findByFriendTo(){
        List<Friend> friendList = friendRepository.findByFriendToId(1001);

        for (Friend f: friendList) {
            System.out.println(f.toString());
        }
    }

    @Test
    public void findByFriendOf(){
        List<Friend> friendList = friendRepository.findByFriendOfId(1001);

        for (Friend f: friendList) {
            System.out.println(f.toString());
        }
    }

    @Test
    public void addFriend(){
        Person person1 = personRepository.findById(1000).orElse(null);
        if(person1 == null) return;

        Person person2 = personRepository.findById(1001).orElse((null));
        if(person2 == null) return;

        Friend friend = new Friend();
        friend.setFriendTo(person1);
        friend.setFriendOf(person2);
        FriendId friendId = new FriendId();
        friendId.setFriendToId(person1.getId());
        friendId.setFriendOfId(person2.getId());
        friend.setId(friendId);

        System.out.println(friendId.toString());
        System.out.println(friend.toString());


        friendRepository.save(friend);
    }

    @Test
    public void deleteFriend(){
        Person person1 = personRepository.findById(1000).orElse(null);
        if(person1 == null) return;

        Person person2 = personRepository.findById(1001).orElse((null));
        if(person2 == null) return;

        FriendId id = FriendId.builder()
                .friendToId(person1.getId())
                .friendOfId(person2.getId())
                .build();

        friendRepository.deleteById(id);
    }

}