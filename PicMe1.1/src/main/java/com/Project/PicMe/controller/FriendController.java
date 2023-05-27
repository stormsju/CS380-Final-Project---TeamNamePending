package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Friend;
import com.Project.PicMe.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/getFriends")
    public List<Friend> getFriends(){
        return friendService.findAllFriends();
    }
}
