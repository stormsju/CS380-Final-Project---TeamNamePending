package com.Project.PicMe.controller;

import com.Project.PicMe.entity.Admin;
import com.Project.PicMe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping()
    public List<Admin> findAll(){
        return adminService.findAll();
    }

    @GetMapping("/person/{personId}")
    public boolean findByPersonId(@PathVariable(name = "personId") int personId){
        return adminService.personIsAdmin(personId);
    }
}
