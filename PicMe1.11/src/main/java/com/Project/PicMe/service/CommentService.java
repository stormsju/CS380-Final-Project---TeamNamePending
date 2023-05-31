package com.Project.PicMe.service;

import com.Project.PicMe.entity.Comment;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.CommentRepository;
import com.Project.PicMe.repository.PersonRepository;
import com.Project.PicMe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PersonRepository personRepository;

    /**
     * An insert method for a single comment. Contains
     *  business logic.
     * @param comment Comment to be inserted to the database
     * @return Status message, String.
     */
    public String saveComment(Comment comment){

        //ensure that the comment is relating to existent post.
        Optional<Post> post = postRepository.findById(comment.getPost().getId());
        if(!post.isPresent()){
            return "No post that matches the id "+comment.getPost().getId();
        }

        //ensure that the comment is relating to existent post.
        Optional<Person> person = personRepository.findById(comment.getPerson().getId());
        if(!person.isPresent()){
            return "No person that matches the id "+comment.getPerson().getId();
        }

        commentRepository.save(comment);
        return "Comment saved successfully.";
    }

    /**
     * A getter method to return a Comment given the comment id.
     * @param id The id of the comment to query for.
     * @return  The comment if found, else null.
     */
    public Comment findById(int id){
        return commentRepository.findById(id).orElse(null);
    }

    /**
     * A getter method that to retrieve every comment in the database.
     * @return A list of every Comment obj in the database.
     */
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    /**
     * A getter method to find all Comments related to a post.
     * @param id Post id for the post that the comments are related to.
     * @return Returns a list of every comment that has a FK to given post.
     */
    public List<Comment> findByPostId(int id){
        //business logic
        return commentRepository.findByPostId(id);
    }

    /**
     * A delete method to delete a specific Comment given commentId.
     * @param id Comment id of comment intended to delete.
     */
    public void deleteById(int id){
        commentRepository.deleteById(id);
    }
}
