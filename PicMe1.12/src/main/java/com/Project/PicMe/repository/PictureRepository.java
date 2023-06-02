package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer>{

    @Query(value = "SELECT p FROM Picture p WHERE p.person.id = ?1")
    public List<Picture> findByPersonId(int personId);
}
