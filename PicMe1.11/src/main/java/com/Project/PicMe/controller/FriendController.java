package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Friend;
import com.Project.PicMe.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/getAll")
    public List<Friend> getFriends(){
        return friendService.findAllFriends();
    }

    @GetMapping("/getFriendsOf/{id}")
    public List<Friend> getFriendsOf(@PathVariable(name = "id") int id){
        return friendService.findByFriendOfId(id);
    }

    @GetMapping("/getFriendsTo/{id}")
    public List<Friend> getFriendsTo(@PathVariable(name = "id") int id){
        return friendService.findByFriendToId(id);
    }

    @DeleteMapping("/delete/{firstId}/{secondId}")
    public void deleteById(@PathVariable("firstId") int firstId, @PathVariable("secondId") int secondId){
        friendService.deleteById(firstId,secondId);
    }
}
