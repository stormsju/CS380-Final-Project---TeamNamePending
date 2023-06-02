package com.Project.PicMe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PictureDTO {
    private int id;
    private byte[] image;
    private String pictureFile;
    @JsonProperty("person")
    private PersonDTO personDTO;
}
