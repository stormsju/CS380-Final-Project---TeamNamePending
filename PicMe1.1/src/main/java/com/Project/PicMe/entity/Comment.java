package com.Project.PicMe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

/**
 * Comment table is used to store
 * user comments on posts
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {
        "person",
        "post"
})
@Entity(name = "Comment")
@Builder
@Table(name = "comment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment {

    /**
     * comment_id is the PK for the entity Comment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "comment_id",
            nullable = false
    )
    private int id;

    /**
     * comment_text is the user generated text
     * for the comment on post.
     */
    @Column(
            name = "comment_text",
            nullable = false
    )
    private String comment;

    /**
     * post_id is the FK column that relates Comment to Post.
     */
    @ManyToOne()
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "post_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "comment_fk_post")
    )
    private Post post;

    /**
     * person_id is the FK column that relates comment to Person
     */
    @ManyToOne()
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "person_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "comment_fk_person")
    )
    private Person person;
}
