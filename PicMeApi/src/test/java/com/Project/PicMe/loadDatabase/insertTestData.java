package com.Project.PicMe.loadDatabase;

import com.Project.PicMe.compositeKey.FriendId;
import com.Project.PicMe.entity.*;
import com.Project.PicMe.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @Autowired
    private PictureRepository pictureRepository;

    @Test
    public void loadInAllTestData(){
        //calls the other methods to load in data
        loadTestPeople();
        loadTestPosts();
        loadTestFriends();
        loadTestComments();
    }

    @Test
    public void loadTestPeople(){
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

    @Test
    public void loadTestPosts(){
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

    @Test
    public void loadTestFriends(){

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

    @Test
    public void loadTestComments(){

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
    @Test
    public void loadTestPictures(){

        List<Picture> pictures = new ArrayList<>();
        final String directoryPath = "/Users/wyatt/Desktop/picMe/GUI/src/main/Pictures";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
        if (files != null) {
            for (File file : files) {


                pictures.add(Picture.builder()
                        .file(file.getName())
                        .image(jpgToByteArray(file.getAbsolutePath().toString()))
                        .person(personRepository.findById(1000).orElse(null))
                        .build()
                );

            }
        }

        pictureRepository.saveAll(pictures);
    }

    private static byte[] jpgToByteArray(String pathToFile){
        byte[] imageBytes = null;
        try {
            imageBytes = Files.readAllBytes(Path.of(pathToFile));
            System.out.println("Image bytes: " + imageBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }


}
