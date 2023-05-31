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

    /**
     * Save Friend relation using Person obj, person 1 is friend to person 2.
     * @param person1 First Person object for friend table.
     * @param person2 Second Person object for friend table.
     * @return A status message, String.
     */
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

    /**
     * Search database for all Friends currently stored.
     * @return A list of all friends in the database.
     */
    public List<Friend> findAllFriends(){
        return friendRepository.findAll();
    }

    /**
     * Search database for Friend entity using two Person id's.
     * @param firstId The FriendToId that will be searched.
     * @param secondId The FriendOfId that will be searched.
     * @return A Friend obj if found, else null.
     */
    public Friend findById(int firstId, int secondId){

         return  friendRepository
                 .findById(FriendId.builder()
                 .friendToId(firstId)
                 .friendOfId(secondId)
                 .build()).orElse(null);

    }

    /**
     * Search database for Friend entity using two Person objects.
     * @param prsn1 Person in FriendTo column.
     * @param prsn2 Person in FriendOf column.
     * @return A Friend object if one is found, otherwise null.
     */
    public Friend findByPeople(Person prsn1, Person prsn2){

        return  friendRepository
                .findById(FriendId.builder()
                        .friendToId(prsn1.getId())
                        .friendOfId(prsn2.getId())
                        .build()).orElse(null);
    }

    /**
     * Searches database for id provided that is in the FriendToId of a Friend.
     * @param id The id of Person to search for.
     * @return A list of people the Person 'follows'.
     */
    public List<Friend> findByFriendToId(int id){
        return friendRepository.findByFriendToId(id);
    }

    /**
     * Searches database for id provided that is in the FriendOfId of a Friend.
     * @param id The id of Person to search for.
     * @return A list of people the Person is 'followed by'.
     */
    public List<Friend> findByFriendOfId(int id){
        return friendRepository.findByFriendOfId(id);
    }

    /**
     * Deletes Friend by Composite id.
     * @param firstId The id of the first Person in the Friend relation.
     * @param secondId The id of the second Person in the Friend relation.
     */
    public void deleteById(int firstId, int secondId){
          friendRepository.deleteById(FriendId.builder()
                        .friendToId(firstId)
                        .friendOfId(secondId)
                        .build());
    }

}
