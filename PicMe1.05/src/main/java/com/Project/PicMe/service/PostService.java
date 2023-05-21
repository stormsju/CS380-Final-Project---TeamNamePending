package com.Project.PicMe.service;

import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void savePost(Post post){
        postRepository.save(post);
    }


}
