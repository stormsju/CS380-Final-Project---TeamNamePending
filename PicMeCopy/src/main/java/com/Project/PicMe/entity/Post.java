package com.Project.PicMe.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "person")
@Entity
@Builder
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    @Column(name = "id",
            nullable = false)
    private int id;

    @Column(name = "text",
            nullable = false)
    private String text;

    @ManyToOne()
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "post_fk_person")
    )
    private Person person;
}
