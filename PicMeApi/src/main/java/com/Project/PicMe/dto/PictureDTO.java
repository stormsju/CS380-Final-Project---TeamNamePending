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
    @JsonProperty("id")
    private int id;
    @JsonProperty("image")
    private byte[] image;
    @JsonProperty("file")
    private String file;
    @JsonProperty("person")
    private PersonDTO personDTO;
}
