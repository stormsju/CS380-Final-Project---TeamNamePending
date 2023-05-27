package com.Project.PicMe.compositeKey;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * Class is used to create the composite primary key
 * used in the Friend entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
@Embeddable
public class FriendId implements Serializable {

    /**
     * the id used for the first person
     * in the friendship
     */
    private int friendToId;

    /**
     * the id used for the first person
     * in the friendship
     */
    private int friendOfId;

}
