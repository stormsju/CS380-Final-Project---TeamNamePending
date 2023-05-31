package com.Project.PicMe.service;

import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    /**
     * A saving method to save a post to the database.
     * @param post Post to save to the database.
     * @return Returns a status message of save.
     */
    public String savePost(Post post){
        //complete business logic
        postRepository.save(post);
        return "Post was saved.";
    }

    /**
     * Returns the post with given id if found, else null.
     * @param id Id of the post to search the database for.
     * @return Post if found, otherwise returns null.
     */
    public Post getPostById(int id){
        return postRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a Post from database given an id.
     * @param id id of Post indended to delete.
     * @return Returns a message for the status of deletion.
     */
    public String deletePostById(int id){
        Optional<Post> optionalPost = Optional.ofNullable(postRepository.findById(id).orElse(null));
        if(!optionalPost.isPresent()) return "No post found with id: "+id;

        postRepository.deleteById(id);
        return "Deleted post with id: "+id;
    }

    /**
     * Getter method to find all posts in the database.
     * @return Returns a list of all the Posts in the database.
     */
    public List<Post> getPosts(){
        return postRepository.findAll();
    }


}
