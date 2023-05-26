package admin.admingui;
/**
 * @author justin Storms
 * @version 1.0 - Log.java
 * @since 5/26/2023
 * Class which builds the basic information of the current admin user.
 */
public class AdminUser {
    //private class variables
    private String userName, password, userID, postID, pictureID;
    private Person p;

    /**
     * Class constructor which builds an object to hold user Admin credentials for AdminUI interactions.
     * @param userName String of Admin userName.
     * @param password String of Admin password;
     */
    AdminUser(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    /**
     * Setter method which updates the current userID with the passed String.
     * @param str String of userID.
     */
    void setUserID(String str){
        this.userID = str;
    }

    /**
     * Setter method which updates the current postID with the passed String.
     * @param str String of postID.
     */
    void setPostID(String str){
        this.postID = str;
    }

    /**
     * Setter method which updates the current pictureID with the passed String.
     * @param str String of pictureID.
     */
    void setPictureID(String str){
        this.pictureID = str;
    }

    /**
     * Setter method which builds a person object from a passed Person query result.
     * @param Person Object returned form query.
     */
    void setPerson(Person person){
        this.person = new Person(person);
    }

    /**
     * Getter method which returns the String of userID.
     * @return String of userID.
     */
    String getUserID(){
        return this.userID;
    }

    /**
     * Getter method which returns the String of postID.
     * @return String of postID.
     */
    String getPostID(){
        return this.postID;
    }

    /**
     * Getter method which returns the String of pictureID.
     * @return String of pictureID.
     */
    String getPictureID(){
        return this.pictureID;
    }

    /**
     * Getter method which returns the String of userName.
     * @return String of userName.
     */
    String getUserName(){
        return this.userName;
    }

    /**
     * Getter method which returns the stored Person object.
     * @return Person object of PicMe user information.
     */
    Person getPerson(){
        return this.person;
    }
}
