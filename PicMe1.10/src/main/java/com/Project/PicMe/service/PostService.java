package com.Project.PicMe.service;

import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public String savePost(Post post){

        //ensure that user is in database

        postRepository.save(post);
        return "Post was saved.";
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }


}
