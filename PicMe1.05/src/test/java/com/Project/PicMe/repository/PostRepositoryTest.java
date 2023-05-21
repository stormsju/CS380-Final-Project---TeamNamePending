package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void savePost(){
        Person prsn0 = personRepository.findById(1000).orElse(null);
        if(prsn0 == null) return;

        Post p0 = Post.builder()
                .text("I thought about writing a post today, then I did it.")
                .person(prsn0)
                .build();

        postRepository.save(p0);
    }

    @Test
    public void savePostsWithPersonObject(){

        Person prsn0 = personRepository.findById(1000).orElse(null);
        Person prsn1 = personRepository.findById(1001).orElse(null);
        Person prsn2 = personRepository.findById(1002).orElse(null);
        Person prsn3 = personRepository.findById(1003).orElse(null);
        Person prsn4 = personRepository.findById(1004).orElse(null);

        if(prsn0 == null|| prsn1 == null||prsn2 == null
                || prsn3 == null || prsn4 == null) return;


        Post p0 = Post.builder()
                .text("I thought about writing a post today, then I did it.")
                .person(prsn0)
                .build();

        Post p1 = Post.builder()
                .text("Here's another post that I thought about posting and then posted.")
                .person(prsn0)
                .build();

        Post p2 = Post.builder()
                .text("Ok this is my last post no more posts after this one")
                .person(prsn0)
                .build();

        Post p3 = Post.builder()
                .text("Wow what lovely weather!")
                .person(prsn1)
                .build();

        Post p4 = Post.builder()
                .text("Reading my favorite book today #winnieThePooh")
                .person(prsn2)
                .build();
        List<Post> posts =  Arrays.asList(p0, p1, p2, p3, p4);
        postRepository.saveAll(posts);
    }

    @Test
    public void deletePost(){
        postRepository.deleteById(10);
    }
}
