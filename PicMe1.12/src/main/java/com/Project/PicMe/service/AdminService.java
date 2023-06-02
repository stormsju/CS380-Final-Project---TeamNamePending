package com.Project.PicMe.service;

import com.Project.PicMe.entity.Admin;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.repository.AdminRepository;
import com.Project.PicMe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PersonRepository personRepository;


    /**
     * A save method to store an admin into the database with Person id provided.
     * @param id An id of the Person entity to be stored in the database.
     * @return A status message for the save.
     */
    public String saveByPersonId(int id){

        //check person with provided id exists.
        Person person = personRepository.findById(id).orElse(null);
        if(person == null) return "No person with "+id+" found in database";

        //build admin object
        Admin admin = Admin.builder()
                .person(person)
                .build();

        //save admin
        adminRepository.save(admin);
        return person.getUsername()+" is now an admin.";
    }

    /**
     * A finder method to search for every admin in the database.
     * @return A list of every Admin object in the databse.
     */
    public List<Admin> findAll(){
        return adminRepository.findAll();
    }

    /**
     * A finder method to search the database for an admin with the Person id provided.
     * @param personId An id of the person to search for in the Admin table.
     * @return The admin entity if one is found in the database. Otherwise, returns null.
     */
    public boolean personIsAdmin(int personId){
        Admin admin = adminRepository.findByPersonId(personId).orElse(null);
        return admin != null;
    }


    public void deleteByPersonId(int personId){
        //check if admin entity is in database
        Admin admin = adminRepository.findByPersonId(personId).orElse(null);
        if (admin == null)return;


        //remove from database
        adminRepository.deleteById(admin.getId());
    }
}
