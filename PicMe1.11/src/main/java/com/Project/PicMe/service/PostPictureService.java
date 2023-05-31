package com.Project.PicMe.service;

import com.Project.PicMe.repository.PostPictureRepository;
import com.Project.PicMe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostPictureService {

    @Autowired
    PostPictureRepository postPictureRepository;
}
