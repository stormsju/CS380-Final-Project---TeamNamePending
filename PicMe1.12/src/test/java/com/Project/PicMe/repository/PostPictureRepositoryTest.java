package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.PostPictureId;
import com.Project.PicMe.entity.PostPicture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostPictureRepositoryTest {

    @Autowired
    PostPictureRepository postPictureRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PostRepository postRepository;


    @Test
    public void save(){

        PostPictureId id = PostPictureId.builder()
                .pictureId(1)
                .postId(1)
                .build();

        PostPicture postPicture = PostPicture.builder()
                .id(id)
                .post(postRepository.findById(1).orElse(null))
                .picture(pictureRepository.findById(1).orElse(null))
                .build();

        postPictureRepository.save(postPicture);
    }
}