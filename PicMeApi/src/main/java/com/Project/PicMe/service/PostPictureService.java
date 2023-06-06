package com.Project.PicMe.service;

import com.Project.PicMe.entity.PostPicture;
import com.Project.PicMe.repository.PostPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostPictureService {

    @Autowired
    PostPictureRepository postPictureRepository;

    /**
     * Query database for PostPicture objects.
     * @return returns a list of PostPictures
     */
    public List<PostPicture> findAll(){
        return postPictureRepository.findAll();
    }
}
