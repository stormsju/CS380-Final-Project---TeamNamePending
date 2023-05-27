package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Comment;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/getCommentById/{id}")
    public Comment findById(@PathVariable("id") int commentId){
        return commentService.findById(commentId);
    }

    @GetMapping("/getCommentByPostId/{id}")
    public List<Comment> findByPostId(@PathVariable("id") int postId){
        return commentService.findByPostId(postId);
    }
}
