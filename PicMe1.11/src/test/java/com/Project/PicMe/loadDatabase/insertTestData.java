package com.Project.PicMe.loadDatabase;

import com.Project.PicMe.compositeKey.FriendId;
import com.Project.PicMe.entity.Comment;
import com.Project.PicMe.entity.Friend;
import com.Project.PicMe.entity.Person;
import com.Project.PicMe.entity.Post;
import com.Project.PicMe.repository.CommentRepository;
import com.Project.PicMe.repository.FriendRepository;
import com.Project.PicMe.repository.PersonRepository;
import com.Project.PicMe.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This test class can be used to load
 * the database with made up data. Make sure
 * The there is no data before in the database
 * before running.
 */


@SpringBootTest
public class insertTestData {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void loadInAllTestData(){
        //calls the other methods to load in data
        loadAllPeople();
        loadAllPosts();
        loadAllFriends();
        loadAllComments();
    }

    @Test
    public void loadAllPeople(){
        List<Person> people = new ArrayList<>();
        people.add(
                Person.builder()
                        .fname("Jake")
                        .lname("Emerson")
                        .email("EmersonJ@Icloud.com")
                        .date("05/25/2001")
                        .username("JakeByTheLake")
                        .password("password")
                        .build()
        );
        people.add(
                Person.builder()
                        .fname("Cade")
                        .lname("Duboi")
                        .email("DuboiC@Icloud.com")
                        .date("08/10/1998")
                        .username("CadeCascade")
                        .password("password")
                        .build()
        );
        people.add(
                Person.builder()
                        .fname("Ethan")
                        .lname("Clyde")
                        .email("ClydeE@outlook.com")
                        .date("01/01/2000")
                        .username("EthanStillSleeping")
                        .password("password")
                        .build()
        );
        people.add(
                Person.builder()
                        .fname("Thor")
                        .lname("Randall")
                        .email("RandallT@Icloud.com")
                        .date("09/20/1960")
                        .username("ThorByTheShore")
                        .password("password")
                        .build()
        );
        people.add(
                Person.builder()
                        .fname("Charlotte")
                        .lname("Noel")
                        .email("NoelC@gmail.com")
                        .date("02/17/1987")
                        .username("CharlotteWeb")
                        .password("password")
                        .build()
        );
        personRepository.saveAll(people);
    }


    public void loadAllPosts(){
        Person prsn0 = personRepository.findById(1000).orElse(null);
        Person prsn1 = personRepository.findById(1001).orElse(null);
        Person prsn2 = personRepository.findById(1002).orElse(null);
        Person prsn3 = personRepository.findById(1003).orElse(null);
        Person prsn4 = personRepository.findById(1004).orElse(null);

        if(prsn0 == null|| prsn1 == null||prsn2 == null
                || prsn3 == null || prsn4 == null) return;


        Post p0 = Post.builder()
                .text("First post by Jake.")
                .person(prsn0)
                .date(LocalDate.now())
                .build();

        Post p1 = Post.builder()
                .text("First post by Cade.")
                .person(prsn1)
                .date(LocalDate.now())
                .date(LocalDate.now())
                .build();

        Post p2 = Post.builder()
                .text("First post by Ethan")
                .person(prsn2)
                .date(LocalDate.now())
                .build();

        Post p3 = Post.builder()
                .text("First post by Thor")
                .person(prsn3)
                .date(LocalDate.now())
                .build();

        Post p4 = Post.builder()
                .text("First post by Charlotte")
                .person(prsn4)
                .date(LocalDate.now())
                .build();

        List<Post> posts =  Arrays.asList(p0, p1, p2, p3, p4);
        postRepository.saveAll(posts);
    }

    public void loadAllFriends(){

        //error avoidance
        Person prsn0 = personRepository.findById(1000).orElse(null);
        Person prsn1 = personRepository.findById(1001).orElse(null);
        Person prsn2 = personRepository.findById(1002).orElse(null);
        Person prsn3 = personRepository.findById(1003).orElse(null);
        Person prsn4 = personRepository.findById(1004).orElse(null);

        if(prsn0 == null|| prsn1 == null||prsn2 == null
                || prsn3 == null || prsn4 == null) return;

        //make friend ids
        FriendId fId0 = FriendId.builder()
                .friendToId(1000)
                .friendOfId(1001)
                .build();

        FriendId fId1 = FriendId.builder()
                .friendToId(1001)
                .friendOfId(1002)
                .build();

        FriendId fId2 = FriendId.builder()
                .friendToId(1002)
                .friendOfId(1003)
                .build();

        FriendId fId3 = FriendId.builder()
                .friendToId(1003)
                .friendOfId(1004)
                .build();

        FriendId fId4 = FriendId.builder()
                .friendToId(1001)
                .friendOfId(1000)
                .build();

        //make friends
        Friend f0 = Friend.builder()
                .id(fId0)
                .friendTo(prsn0)
                .friendOf(prsn1)
                .build();

        Friend f1 = Friend.builder()
                .id(fId1)
                .friendTo(prsn1)
                .friendOf(prsn2)
                .build();

        Friend f2 = Friend.builder()
                .id(fId2)
                .friendTo(prsn2)
                .friendOf(prsn3)
                .build();

        Friend f3 = Friend.builder()
                .id(fId3)
                .friendTo(prsn3)
                .friendOf(prsn4)
                .build();

        Friend f4 = Friend.builder()
                .id(fId4)
                .friendTo(prsn1)
                .friendOf(prsn0)
                .build();

        List<Friend> friends = new ArrayList<>();
        friends.add(f0);
        friends.add(f1);
        friends.add(f2);
        friends.add(f3);
        friends.add(f4);

        friendRepository.saveAll(friends);
    }

    public void loadAllComments(){

        Iterator<Post> postIterator = postRepository.findAll().iterator();
        List<Person> people = personRepository.findAll();
        List<Comment> comments = new ArrayList<>();


        for (Person p: people) {
            comments.add(Comment.builder()
                    .post(postIterator.next())
                    .person(p)
                    .comment("Just a basic commment that was made by "+p.getUsername())
                    .build());

        }

        commentRepository.saveAll(comments);

    }

}
