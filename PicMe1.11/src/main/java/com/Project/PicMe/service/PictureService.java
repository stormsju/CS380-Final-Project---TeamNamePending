package com.Project.PicMe.service;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Picture;
import com.Project.PicMe.repository.PersonRepository;
import com.Project.PicMe.repository.PictureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PersonRepository personRepository;

    //create
    public String savePicture(Picture picture){

        //check for null values
        if(picture.getImage() == null ||
        picture.getPictureFile() == null ||
        picture.getPerson() == null) return "A value that should not be empty was null.";

        //check for user in database
        Person person = personRepository.findById(picture.getPerson().getId()).orElse(null);
        if(person == null) return "No user found with id: " + picture.getPerson().getId();

        pictureRepository.save(picture);
        return "picture saved successfully";
    }

    //read
    public Picture findByPictureId(int id){
        return pictureRepository.findById(id).orElse(null);
    }

    //update
    @Transactional
    public void updatePicture(){
        //has to be implemented
    }


    //delete
    public void deletePicture(int id){
        pictureRepository.deleteById(id);
    }
}
