package com.Project.PicMe.service;

import com.Project.PicMe.dto.PostDTO;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void savePost(){
        Person person = personRepository.findById(1001).orElse(null);
        if(person == null) return;

        Post post = Post.builder()
                .text("Posted by Cade Duboi")
                .person(person)
                .build();

        postService.save(post);
    }

    @Test
    public void getPosts(){
        List<PostDTO> posts = postService.findAll();

        System.out.println(posts.get(1).getPerson());


    }

    @Test
    public void deletePost(){
        postService.deleteById(1);

    }

}