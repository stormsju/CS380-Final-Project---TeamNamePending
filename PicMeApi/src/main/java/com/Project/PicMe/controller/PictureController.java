package com.Project.PicMe.controller;

import com.Project.PicMe.dto.PictureDTO;
import com.Project.PicMe.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/picture")
public class PictureController {

    @Autowired
    PictureService pictureService;

    @GetMapping("/person/{personId}")
    public List<PictureDTO> findByPersonId(@PathVariable(name = "personId") int personId){
        return pictureService.findByPersonId(personId);
    }

    @GetMapping()
    public List<PictureDTO> findAll(){
        return pictureService.findAll();
    }
}
