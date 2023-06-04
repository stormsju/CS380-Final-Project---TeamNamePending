package com.Project.PicMe.controller;

import com.Project.PicMe.entity.PostPicture;
import com.Project.PicMe.service.PostPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/postPicture")
public class PostPictureController {

    @Autowired
    PostPictureService postPictureService;

    @GetMapping()
    public List<PostPicture> findAll(){
        return postPictureService.findAll();
    }
}
