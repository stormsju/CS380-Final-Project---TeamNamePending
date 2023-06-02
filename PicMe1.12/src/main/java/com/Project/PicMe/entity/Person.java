package com.Project.PicMe.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List ;


/**
 * Person table is used to store the
 * information of users on the system
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {
        "posts",
        "friendTo",
        "friendOf",
        "pictures",
        "comments"
})
@Entity(name = "Person")
@Table(name = "person",
        uniqueConstraints = {
            @UniqueConstraint(name = "person_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "person_username_unique", columnNames = "username")
        })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person {

    /**
     * person_id column in the PERSON entity of the database.
     * Used as the primary key for Person
     */
    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    @Column(name = "person_id")
    private int id;

    @Column(name = "username",
            length = 50,
            nullable = false)
    @JsonProperty
    private String username;

    @Column(name = "password",
            length = 50,
            nullable = false)
    @JsonProperty
    private String password;

    /**
     * first_name column in the PERSON entity of the database.
     * stored as a VARCHAR size 50
     */
    @Column(name = "fname",
            length = 50,
            nullable = false)
    @JsonProperty
    private String fname;

    /**
     * last_name column in the PERSON entity of the database.
     * stored as a VARCHAR size 50
     */
    @Column(name = "lname",
            length = 50,
            nullable = false)
    @JsonProperty
    private String lname;

    /**
     * email column in the PERSON entity of the database.
     */
    @Column(name = "email",
            nullable = false)
    @JsonProperty
    private String email;

    /**
     * birth_date column in the PERSON entity of the database.
     */
    @Column(name = "birth_date",
            nullable = false)
    @JsonProperty
    private String date;


    /**
     * Creates the bidirectional mapping between Person and Post.
     * When a Person object(tuple) is deleted, all Post objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    /**
     * Creates the bidirectional mapping between Person and Friend.
     * When a Person object(tuple) is deleted, all Friend objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "friendTo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friend> friendTo;

    /**
     * Creates the bidirectional mapping between Person and Friend.
     * When a Person object(tuple) is deleted, all Friend objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "friendOf", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friend> friendOf;

    /**
     * Creates the bidirectional mapping between Person and Picture.
     * When a Person object(tuple) is deleted, all Picture objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Picture> pictures;

    /**
     * Establishes bidirectional mapping between Person and Comment.
     * When Person obj is deleted all related foreign key tuples in
     * Comment are deleted.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    /**
     * Establishes bidirectional relation between Admin and Person.
     */
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private Admin admin;
}
