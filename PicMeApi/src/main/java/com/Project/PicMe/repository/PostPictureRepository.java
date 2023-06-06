package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.PostPictureId;
import com.Project.PicMe.entity.PostPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostPictureRepository extends JpaRepository<PostPicture, PostPictureId> {

    @Query("SELECT p FROM PostPicture p WHERE p.id.pictureId = ?1")
    public List<PostPicture> findByPictureId(int pictureId);

    @Query("SELECT p FROM PostPicture p WHERE p.id.postId = ?1")
    public List<PostPicture> findByPostId(int postId);

}
