package com.Project.PicMe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "post")
@Entity(name = "Person")
@Table(name = "person",
        uniqueConstraints = {
            @UniqueConstraint(name = "person_email_unique", columnNames = "email")
        })
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name",
            nullable = false)
    private String fname;

    @Column(name = "last_name",
            nullable = false)
    private String lname;

    @Column(name = "email",
            nullable = false)
    private String email;

    @Column(name = "birth_date",
            nullable = false)
    private String date;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "id"
    )
    private List<Post> post;


}
