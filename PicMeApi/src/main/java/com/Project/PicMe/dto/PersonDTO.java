package com.Project.PicMe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private int id;
    private String fname;
    private String lname;
    private String username;
    private String email;
    private String password;
    private String date;

}
