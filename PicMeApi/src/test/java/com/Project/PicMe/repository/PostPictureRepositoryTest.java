package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.PostPictureId;
import com.Project.PicMe.entity.PostPicture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
                .postId(1)
                .pictureId(2)
                .build();

        PostPicture postPicture = PostPicture.builder()
                .id(id)
                .post(postRepository.findById(1).orElse(null))
                .picture(pictureRepository.findById(2).orElse(null))
                .build();

        postPictureRepository.save(postPicture);
    }

    @Test
    public void findByPictureId(){
        List<PostPicture> list =  postPictureRepository.findByPictureId(1);

        list.forEach(PostPicture::toString);
    }
}