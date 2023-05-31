package com.Project.PicMe.compositeKey;


import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Class creates a composite primary key
 * used in the PostPicture entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Embeddable
public class PostPictureId {

    private int pictureId;
    private int postId;
}
