package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUsername(String username);

    Person findByEmail(String email);
}
