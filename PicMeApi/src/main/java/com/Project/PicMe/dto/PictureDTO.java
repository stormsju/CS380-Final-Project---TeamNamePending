package com.Project.PicMe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    @JsonProperty("text")
    private String text;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("person")
    private PersonDTO personDTO;
}
