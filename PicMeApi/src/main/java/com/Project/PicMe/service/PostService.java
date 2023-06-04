package com.Project.PicMe.service;

import com.Project.PicMe.dto.PersonDTO;
import com.Project.PicMe.dto.PictureDTO;
import com.Project.PicMe.dto.PostDTO;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Picture;
import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    /**
     * A saving method to save a post to the database.
     * @param post Post to save to the database.
     * @return Returns a status message of save.
     */
    public String save(Post post){
        //complete business logic
        postRepository.save(post);
        return "Post was saved.";
    }

    /**
     * Returns the post with given id if found, else null.
     * @param id Id of the post to search the database for.
     * @return Post if found, otherwise returns null.
     */
    public PostDTO findById(int id){
        return postAdapter(postRepository.findById(id).orElse(null));
    }

    /**
     * Deletes a Post from database given an id.
     * @param postId id of Post indended to delete.
     */
    public void deleteById(int postId){
        postRepository.deleteById(postId);
    }

    /**
     * Getter method to find all posts in the database.
     * @return Returns a list of all the Posts in the database.
     */
    public List<PostDTO> findAll(){
        return postListAdapter(postRepository.findAll());
    }

    /**
     * Search database to retrieve any post from provided person id.
     * @param personId The id of person to query database.
     * @return A list of every post the user has made.
     */
    public List<PostDTO> findByPersonId(int personId){
        return postListAdapter(postRepository.findByPersonId(personId));
    }

    //turns a list of Post into a list of PostDTO
    //uses another helper function postAdapter and for each loop.
    private List<PostDTO> postListAdapter(List<Post> posts){

        List<PostDTO> outputList = new ArrayList<>();

        if(posts.size() == 0) return outputList;


        for (Post post: posts) {
            outputList.add(postAdapter(post));
        }

        return outputList;
    }

    //Takes in a single post
    //Outputs a PostDTO
    private PostDTO postAdapter(Post post){

        if(post == null) return null;

        Person person = post.getPerson();
        return PostDTO.builder()
                .id(post.getId())
                .date(post.getDate())
                .text(post.getText())
                .person(PersonDTO.builder()
                        .id(person.getId())
                        .fname(person.getFname())
                        .lname(person.getLname())
                        .date(person.getDate())
                        .username(person.getUsername())
                        .email(person.getEmail())
                        .password(person.getPassword())
                        .build())
                .build();

    }
}
