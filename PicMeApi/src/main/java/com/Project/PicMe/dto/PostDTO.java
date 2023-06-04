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
public class PostDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("text")
    private String text;

    @JsonProperty("person")
    private PersonDTO person;
}
