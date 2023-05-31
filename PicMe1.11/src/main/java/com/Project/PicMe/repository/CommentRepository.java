package com.Project.PicMe.repository;

import com.Project.PicMe.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT c FROM Comment c WHERE c.post.id = ?1")
    public List<Comment> findByPostId(int postId);
}
