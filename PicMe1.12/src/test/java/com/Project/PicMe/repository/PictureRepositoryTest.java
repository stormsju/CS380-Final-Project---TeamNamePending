package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Picture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PictureRepositoryTest {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void savePicture(){
        Picture picture = Picture.builder()
                .person(personRepository.findById(1000).orElse(null))
                .image(new byte[]{1,23,34,2,25,4,-12,63,5,67,90,4,3,2,1,14,53})
                .pictureFile("Desktop/file.png")
                .build();
        pictureRepository.save(picture);
    }

    @Test
    public void findByPersonId(){
        List<Picture> pictures = pictureRepository.findByPersonId(1000);

        for (Picture p: pictures) {
            System.out.println(p.toString());
        }
    }
}