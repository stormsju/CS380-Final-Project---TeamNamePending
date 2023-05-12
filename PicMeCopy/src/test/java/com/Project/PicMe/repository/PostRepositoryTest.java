package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void savePostWithPersonObject(){

        Person person = personRepository.findById(2).orElse(null);

        if(person == null){
            return;
        }

        Post post = Post.builder()
                .text("Oops this post is now made By Blake")
                .person(person)
                .build();

        postRepository.save(post);
    }

    @Test
    public void deletePost(){
        postRepository.deleteById(10);
    }
}
