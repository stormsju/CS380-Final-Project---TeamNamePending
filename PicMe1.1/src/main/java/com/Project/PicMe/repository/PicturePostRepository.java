package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.PostPictureId;
import com.Project.PicMe.entity.PostPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicturePostRepository extends JpaRepository<PostPicture, PostPictureId> {
}
