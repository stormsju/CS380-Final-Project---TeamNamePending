package com.Project.PicMe.controller;

import com.Project.PicMe.dto.PostDTO;
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

    @GetMapping()
    public List<PostDTO> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{postId}")
    public PostDTO findById(@PathVariable(name = "postId") int postId){
        return postService.findById(postId);
    }

    @GetMapping("/person/{personId}")
    public List<PostDTO> findByPersonId(@PathVariable(name = "personId") int personId){
        return postService.findByPersonId(personId);
    }

    @PostMapping("/save")
    public String addPost(@RequestBody Post post){
        return postService.save(post);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePostById(@PathVariable(name = "postId") int postId){
        postService.deleteById(postId);
    }
}
