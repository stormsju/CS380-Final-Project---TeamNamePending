package com.Project.PicMe.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Picture is a table to store the data for
 * any picture.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {
        "person",
        "postPictures"
})
@Entity(name = "Picture")
@Builder
@Table(name = "picture")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Picture {

    /**
     * Column named picture_id in the PICTURE entity in the database.
     * This is the primary key of the entity and is auto incremented.
     * No value should be passed when creating the Picture object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private int id;

    /**
     * Column named picture_data in the PICTURE entity in the database
     * Used to store the raw data of the file
     */
    @Column(name = "picture_data",
            length = 10000000,
            nullable = false
    )
    private byte[] image;

    /**
     * Column named picture_file in the PICTURE entity in database
     * Used to store the file data of the picture uploaded on the database
     */
    @Column(name = "picture_file",
        nullable = false)
    private String file;

    /**
     * The foreign key column between Picture and Person.
     *     (A picture can belong to one and only one person,
     *     A person can have zero to many pictures)
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "person_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "picture_fk_person")
    )

    private Person person;

    /**
     * Establishes bidirectional relationship between
     * picture and picture post entity
     */
    @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostPicture> postPictures;

}
