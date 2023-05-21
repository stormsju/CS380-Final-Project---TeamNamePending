package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Post;
import com.Project.PicMe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/post")
    public void addPost(@RequestBody Post post){
        postService.savePost(post);
    }
}
