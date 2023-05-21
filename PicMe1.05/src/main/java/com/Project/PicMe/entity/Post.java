package com.Project.PicMe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Post table is used to store the
 * posts created by users of the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {
        "comments",
        "person"
})
@Entity
@Builder
@Table(name = "post")

public class Post {

    /**
     * post_id is the primary key for the entity POST.
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "post_id",
            nullable = false)
    private int id;

    /**
     * post_text is the column in the entity POST which stores
     * the user input (caption) for the post.
     */
    @Column(name = "post_text",
            length = 500)
    private String text;

    /**
     * person_id is a FK column in the POST entity
     * which relates to the PK person_id in the PERSON entity.
     */
    @ManyToOne()
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "person_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "post_fk_person")
    )
    @JsonBackReference
    private Person person;

    /**
     * Establishes bidirectional mapping between Post and Comment.
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;


    /**
     * Establishes bidirectional relationship between
     * Post and PicturePost entity
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PostPicture> postPictures;
}
