package http;

import entity.Person;
import entity.Picture;
import entity.Post;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @Author Justin Storms
 * @version 1.0 - HttpController.java
 * @since 6/1/2023
 * Class which controls database requests to be pulled from REST API.
 */
public class HttpController {
    /**
     * Static class variables
     */
    private static String
            pictureFilePath = System.getProperty("user.dir") + "src/main/Pictures/",
            picExtension = ".png";

    /**
     * Method which submits a query to REST API via PersonHttp class. Builds a Person object, and returns the toString()
     * call from the Person class.
     * @param id int Person ID from Person table in database.
     * @return String of Person toString() method, containing user details.
     */
    public static String personController(int id){
        Person p = null;
        String str = "";
        PersonHttp personHttp = new PersonHttp();
        try{
            p = personHttp.getById(id);

            str = p.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return str;
    }

    /**
     * Method which submits a query to REST API via PictureHttp class. Builds a Picture object, then extracts Blob
     * object and writes to file as .jpg. Filepath to the picture is passed as a return.
     * @param id int of Picture ID key in Picture table of database.
     * @return String of filepath to picture location.
     */
    public static String pictureController(int id){
        Picture p = null;
        Blob b = null;
        String picPath = "";

        try {
            p = (Picture) PictureHttp.getPictureWithId(id);

            try {
                //currently only supports .png
                picPath = pictureFilePath + id + picExtension;

                //write image to file as byte data
                b = p.getPictureData();
                byte[] byteArr = b.getBytes(1, (int) b.length());

                //output
                FileOutputStream fileOut = new FileOutputStream(picPath);
                fileOut.write(byteArr);

                fileOut.close();
            } catch(IOException e1){
                System.out.println(e1.getMessage());
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return picPath;
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
            p = (Post) PostHttp.getPostWithId(id);
            post = p.getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return post;
    }
}
