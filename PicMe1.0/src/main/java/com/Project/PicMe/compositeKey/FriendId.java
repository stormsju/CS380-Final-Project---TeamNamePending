package com.Project.PicMe.compositeKey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Class is used to create the composite primary key
 * used in the Friend entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Embeddable
public class FriendId implements Serializable {

    /**
     * the id used for the first person
     * in the friendship
     */
    private int person1Id;

    /**
     * the id used for the first person
     * in the friendship
     */
    private int person2Id;

}
