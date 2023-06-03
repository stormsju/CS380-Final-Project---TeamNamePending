package com.Project.PicMe.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(
        exclude = "person"
)
@Data
@Entity(name = "Admin")
@Table(name = "admin",
    uniqueConstraints = {
            @UniqueConstraint(name = "admin_pid_unique", columnNames = "person_id")
    }
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "person_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "admin_fk_person")
    )
    private Person person;
}