package com.Project.PicMe.service;

import com.Project.PicMe.dto.PersonDTO;
import com.Project.PicMe.dto.PictureDTO;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Picture;
import com.Project.PicMe.repository.PersonRepository;
import com.Project.PicMe.repository.PictureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PersonRepository personRepository;

    /**
     * Method to store a picture into the database.
     * @param picture Picture object to be stored.
     * @return A status message.
     */
    public String savePicture(Picture picture){

        //check for null values
        if(picture.getImage() == null ||
        picture.getFile() == null ||
        picture.getPerson() == null) return "A value that should not be empty was left null.";

        //check for user in database
        Person person = personRepository.findById(picture.getPerson().getId()).orElse(null);
        if(person == null) return "No user found with id: " + picture.getPerson().getId();

        pictureRepository.save(picture);
        return "picture saved successfully";
    }

    /**
     * Method to find Picture by picture id.
     * @param id Id of the picture object to search for.
     * @return Picture if it was found in the database, otherwise returns null.
     */
    public Picture findById(int id){
        return pictureRepository.findById(id).orElse(null);
    }

    /**
     * Method to find a List of Pictures that were posted by a Person.
     * @param personId Id of person who Pictures are attached to.
     * @return List of all the picture that have the person id.
     */
    public List<PictureDTO> findByPersonId(int personId){
        return pictureAdapter(pictureRepository.findByPersonId(personId));
    }

    /**
     * A finder method to get all Pictures in the database.
     * @return A list of every picture in the database.
     */
    public List<PictureDTO> findAll(){
        return pictureAdapter(pictureRepository.findAll());
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

    /**
     * Helper method for Picture object into a PictureDTO object adapter.
     * @param pictures List of pictures to convert.
     * @return List of PictureDTOs from the Pictures
     */
    private List<PictureDTO> pictureAdapter(List<Picture> pictures){
        List<PictureDTO> list = new ArrayList<>();

        for (Picture picture: pictures) {
            list.add(pictureAdapter(picture));
        }

        return list;
    }

    /**
     * Method to swap a Picture to a pictureDto
     * @param picture Picture object to adapt.
     * @return Picture Dto output.
     */
    private PictureDTO pictureAdapter(Picture picture){
        Person person = picture.getPerson();
        return  PictureDTO.builder()
                .id(picture.getId())
                .image(picture.getImage())
                .file(picture.getFile())
                .text(picture.getText())
                .date(picture.getDate())
                //nesting the PersonDTO in
                .personDTO(PersonDTO.builder()
                        .id(person.getId())
                        .fname(person.getFname())
                        .lname(person.getLname())
                        .date(person.getDate())
                        .username(person.getUsername())
                        .email(person.getEmail())
                        .password(person.getPassword())
                        .build()
                )
                .build();
    }
}
