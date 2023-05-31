package com.Project.PicMe.entity;

import com.Project.PicMe.compositeKey.FriendId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;


/**
 * Friend is an associative table that allows for
 * a person to be friends with zero to many person(s).
 */
@Entity(name = "Friend")
@Table(name = "friend")
@Data
@ToString(exclude = {
        "friendTo",
        "friendOf"
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @MapsId("friendToId")
    @JoinColumn(name = "friend_to_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "friend1_fk_person"))
    @JsonIgnore
    Person friendTo;

    /**
     * person2_id is the FK column to the Person entity
     */
    @ManyToOne
    @MapsId("friendOfId")
    @JoinColumn(name = "friend_of_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(name = "friend2_fk_person"))
    @JsonIgnore
    Person friendOf;

}
