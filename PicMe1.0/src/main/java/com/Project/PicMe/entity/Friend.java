package com.Project.PicMe.entity;

import com.Project.PicMe.compositeKey.FriendId;
import jakarta.persistence.*;
import lombok.*;


/**
 * Friend is an associative table that allows for
 * a person to be friends with zero to many person(s).
 */
@Entity
@Table(name = "friend")
@Data
@ToString(exclude = {
        "person1",
        "person2"
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {

    /**
     * Embedded id is the composite key that will be
     * used as the PK for the FRIEND entity.
     */
    @EmbeddedId
    private FriendId id;

    /**
     * person1_id is the FK column to the Person entity
     */
    @ManyToOne
    @MapsId("person1Id")
    @JoinColumn(name = "person1_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "friend1_fk_person"))
    Person person1;

    /**
     * person2_id is the FK column to the Person entity
     */
    @ManyToOne
    @MapsId("person2Id")
    @JoinColumn(name = "person2_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "friend2_fk_person"))
    Person person2;
}
