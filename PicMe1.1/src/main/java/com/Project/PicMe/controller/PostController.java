package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Post;
import com.Project.PicMe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/getPosts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/postById/{id}")
    public Post findPostById(@PathVariable int id){
        return postService.getPostById(id);
    }

    @PostMapping("/addPost")
    public String addPost(@RequestBody Post post){
        return postService.savePost(post);
    }

    @DeleteMapping("/deletePostWithId/{id}")
    public String deletePostById(@PathVariable int id){
        return postService.deletePostById(id);
    }
}
