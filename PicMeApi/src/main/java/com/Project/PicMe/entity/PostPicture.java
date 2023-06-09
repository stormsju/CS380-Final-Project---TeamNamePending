package com.Project.PicMe.entity;

import com.Project.PicMe.compositeKey.PostPictureId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

/**
 * PostPicture is an associative table that
 * between post and picture
 */
@Entity(name = "PostPicture")
@Table(name = "postpicture")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PostPicture {

    /**
     * The pk object for the postPicture entity.
     */
    @EmbeddedId
    private PostPictureId id;

    /**
     * Maps the post entity fk for a join column.
     */
    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "postpicture_fk_post"))
    @JsonIgnore
    private Post post;

    /**
     * Maps the picture entity fk for a join column.
     */
    @ManyToOne
    @MapsId("pictureId")
    @JoinColumn(name = "picture_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "postpicture_fk_picture"))
    @JsonIgnore
    private Picture picture;
}
