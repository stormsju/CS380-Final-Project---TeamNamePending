package com.Project.PicMe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
        "post",
        "friendsA",
        "friendsB",
        "pictures",
        "comments"
})
@Entity(name = "Person")
@Table(name = "person",
        uniqueConstraints = {
            @UniqueConstraint(name = "person_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "person_username_unique", columnNames = "username")
        })
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
    private String username;

    @Column(name = "password",
            length = 50,
            nullable = false)
    private String password;

    /**
     * first_name column in the PERSON entity of the database.
     * stored as a VARCHAR size 50
     */
    @Column(name = "fname",
            length = 50,
            nullable = false)
    private String fname;

    /**
     * last_name column in the PERSON entity of the database.
     * stored as a VARCHAR size 50
     */
    @Column(name = "lname",
            length = 50,
            nullable = false)
    private String lname;

    /**
     * email column in the PERSON entity of the database.
     */
    @Column(name = "email",
            nullable = false)
    private String email;

    /**
     * birth_date column in the PERSON entity of the database.
     */
    @Column(name = "birth_date",
            nullable = false)
    private String date;


    /**
     * Creates the bidirectional mapping between Person and Post.
     * When a Person object(tuple) is deleted, all Post objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> post;

    /**
     * Creates the bidirectional mapping between Person and Friend.
     * When a Person object(tuple) is deleted, all Friend objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "person1", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Friend> friendsA;

    /**
     * Creates the bidirectional mapping between Person and Friend.
     * When a Person object(tuple) is deleted, all Friend objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "person2", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Friend> friendsB;

    /**
     * Creates the bidirectional mapping between Person and Picture.
     * When a Person object(tuple) is deleted, all Picture objects(tuples) which
     * have foreign keys pointing to specific Person obj primary key will be deleted as well.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Picture> pictures;

    /**
     * Establishes bidirectional mapping between Person and Comment.
     * When Person obj is deleted all related foreign key tuples in
     * Comment are deleted.
     */
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Comment> comments;
}
