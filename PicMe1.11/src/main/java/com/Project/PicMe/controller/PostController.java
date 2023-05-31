package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Post;
import com.Project.PicMe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/getAll")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/getById/{id}")
    public Post findPostById(@PathVariable int id){
        return postService.getPostById(id);
    }

    @PostMapping("/add")
    public String addPost(@RequestBody Post post){
        return postService.savePost(post);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deletePostById(@PathVariable int id){
        return postService.deletePostById(id);
    }
}
