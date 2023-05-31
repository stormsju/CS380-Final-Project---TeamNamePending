package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query(value = "SELECT a FROM Admin a WHERE a.person.id =?1")
    Optional<Admin> findByPersonId(int id);
}
