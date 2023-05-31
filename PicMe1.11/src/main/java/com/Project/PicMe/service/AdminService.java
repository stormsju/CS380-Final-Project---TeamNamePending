package com.Project.PicMe.service;

import com.Project.PicMe.entity.Admin;
import com.Project.PicMe.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public String saveAdmin(Admin admin){
        Admin optionalAdmin = adminRepository.findByPersonId(admin.getPerson().getId()).orElse(null);

        if(optionalAdmin != null){
            return "Admin with person id already exists.";
        }

        return "Admin was successfully saved.";
    }


}
