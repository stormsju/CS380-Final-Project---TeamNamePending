package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Comment;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void writeComment(){
        Post post  = postRepository.findById(1).orElse(null);
        if(post == null) return;

        Person person = personRepository.findById(1001).orElse(null);
        if(person == null) return;

        Comment comment = Comment.builder()
                .comment("Well I'm going to leave a comment on this post.")
                .person(person)
                .post(post)
                .build();

        commentRepository.save(comment);
    }
}