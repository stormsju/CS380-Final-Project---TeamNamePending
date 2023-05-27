package com.Project.PicMe.service;

import com.Project.PicMe.compositeKey.FriendId;
import com.Project.PicMe.entity.Friend;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FriendService {

    @Autowired
    FriendRepository friendRepository;


    public String saveFriend(Person person1, Person person2){

        //complete business logic

        FriendId id = FriendId.builder()
                .friendToId(person1.getId())
                .friendOfId(person2.getId())
                .build();

        Friend friend = Friend.builder()
                .friendTo(person1)
                .friendOf(person2)
                .id(id)
                .build();

        friendRepository.save(friend);
        return person1.getUsername() + " and " + person2.getUsername() + "are now friends.";
    }

    public List<Friend> findAllFriends(){
        return friendRepository.findAll();
    }

    public Friend findFriendById(int firstId, int secondId){

         return  friendRepository
                 .findById(FriendId.builder()
                 .friendToId(firstId)
                 .friendOfId(secondId)
                 .build()).orElse(null);

    }

    public Friend findFriendByPeople(Person prsn1, Person prsn2){
        //business logic


        return  friendRepository
                .findById(FriendId.builder()
                        .friendToId(prsn1.getId())
                        .friendOfId(prsn2.getId())
                        .build()).orElse(null);
    }

}
