package com.Project.PicMe.service;

import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public String savePost(Post post){
        //complete business logic
        postRepository.save(post);
        return "Post was saved.";
    }

    public Post getPostById(int id){
        return postRepository.findById(id).orElse(null);
    }

    public String deletePostById(int id){
        Optional<Post> optionalPost = Optional.ofNullable(postRepository.findById(id).orElse(null));
        if(!optionalPost.isPresent()) return "No post found with id: "+id;

        postRepository.deleteById(id);
        return "Deleted post with id: "+id;
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }


}
