package com.Project.PicMe.entity;

import com.Project.PicMe.compositeKey.PostPictureId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

/**
 * PostPicture is an associative table that
 * between post and picture
 */
@Entity
@Table(name = "postpicture")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PostPicture {

    @EmbeddedId
    private PostPictureId id;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "postpicture_fk_post"))
    private Post post;

    @ManyToOne
    @MapsId("pictureId")
    @JoinColumn(name = "picture_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "postpicture_fk_picture"))
    private Picture picture;
}
