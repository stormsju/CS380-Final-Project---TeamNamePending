package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.PostRepository;
import com.Project.PicMe.service.PostService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/getPosts")
    public List<Post> findAllPeople(){
        Gson gson = new Gson();
        List<Post> posts = postService.getPosts();
        System.out.println(posts.toString());
        return posts;
    }
}
