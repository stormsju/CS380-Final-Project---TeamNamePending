package admin.admingui;

public class AdminUser {
    private String userName, password;
    private int userID, postID, pictureID;
    private Person p;

    AdminUser(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    void setUserID(int n){
        this.userID = n;
    }

    void setPostID(int n){
        this.postID = n;
    }

    void setPictureID(int n){
        this.pictureID = n;
    }

    void setPerson(Person person){
        this.person = new Person(person);
    }

    int getUserID(){
        return this.userID;
    }

    int getPostID(){
        return this.postID;
    }

    int getPictureID(){
        return this.pictureID;
    }

    String getUserName(){
        return this.userName;
    }

    Person getPerson(){
        return this.person;
    }
}
