package http;

import entity.Person;
import entity.Picture;
import entity.Post;

import java.util.List;

/**
 * @Author Justin Storms
 * @version 1.0 - HttpController.java
 * @since 6/1/2023
 * Class which controls database requests to be pulled from REST API.
 */
public class HttpController {
    /**
     * Method which submits a query to REST API via PersonHttp class. Builds a Person object, and returns the toString()
     * call from the Person class.
     * @param id int Person ID from Person table in database.
     * @return String of Person toString() method, containing user details.
     */
    public static Person personController(int id){
        Person p = null;
        //String str = "";
        PersonHttp personHttp = new PersonHttp();
        try{
            p = personHttp.getById(id);

            //str = p.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return p;
    }

    /**
     * Method which submits a query to REST API via PictureHttp class. Builds a Picture object, then extracts Blob
     * object and writes to file as .jpg. Filepath to the picture is passed as a return.
     * @param id int of Picture ID key in Picture table of database.
     * @return String of filepath to picture location.
     */
    public static List<Picture> pictureController(int id){
        String picPath = "";
        PictureHttp pictureHttp = new PictureHttp();
        List<Picture> picture = null;
        try {
            picture = pictureHttp.getByPerson(id);
            for(Picture p : picture){
                System.out.println(p.toString());

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return picture;
    }

    /**
     * Method which submits a query to REST API via PostHttp class. Builds a Post object and extracts the "post" text.
     * @param id int of Post ID key in Post Table of database.
     * @return String of text body as a "post".
     */
    public static String postController(int id){
        Post p = null;
        String post = "";

        try{
            //p = (Post) PostHttp.getById(id);
            post = p.getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return post;
    }
}
