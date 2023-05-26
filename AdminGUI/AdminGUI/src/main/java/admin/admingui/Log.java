package admin.admingui;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Log {
    private String userName, personID, postID, commentID, activity, date;
    int logID;

    //Class base constructor
    Log(String userName, int personID){
        this.userName = userName;
        this.personID = String.valueOf(personID);
        this.postID = "";
        this.commentID = "";
        this.activity = "";
        this.date = "";
        this.logID = 0; //base log
    }


    Log(Log log, String activity){
        if(activity.equals("PUD")) { //Constructor for AdminController.pullUserDetails
            this.userName = log.getUserName();
            this.personID = log.getPersonID();
            this.postID = "";
            this.commentID = "";
            this.activity = "Pull User Details.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.date = dtf.format(now);
            this.logID = log.getLogID() + 1;
        } else if (activity.equals("PCID")){ //Constructor for AdminController.pullContactIDs
            this.userName = log.getUserName();
            this.personID = log.getPersonID();
            this.postID = "";
            this.commentID = "";
            this.activity = "Pull Contact IDs from PMUser.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.date = dtf.format(now);
            this.logID = log.getLogID() + 1;
        }
    }

    Log(Log log, String ID, String activity){
        if(activity.equals("PPic")) { //Constructor for AdminController.pullPicture
            this.userName = log.getUserName();
            this.personID = log.getPersonID();
            this.postID = ID;
            this.commentID = "";
            this.activity = "Pull Picture from PMUser.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.date = dtf.format(now);
            this.logID = log.getLogID() + 1;
        } else if (activity.equals("PPost")){ //Constructor for AdminController.pullPost
            this.userName = log.getUserName();
            this.personID = log.getPersonID();
            this.postID = ID;
            this.commentID = "";
            this.activity = "Pull Post from PMUser.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.date = dtf.format(now);
            this.logID = log.getLogID() + 1;
        }
    }

    Log(Log log, String ID, String cID, String activity){
        if(activity.equals("PPic")) { //Constructor for AdminController.pullPictureComments
            this.userName = log.getUserName();
            this.personID = log.getPersonID();
            this.postID = ID;
            this.commentID = cID;
            this.activity = "Pull Picture Comments from PMUser.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.date = dtf.format(now);
            this.logID = log.getLogID() + 1;
        } else if (activity.equals("PPost")){ //Constructor for AdminController.pullPostComments
            this.userName = log.getUserName();
            this.personID = log.getPersonID();
            this.postID = ID;
            this.commentID = cID;
            this.activity = "Pull Post Comments from PMUser.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.date = dtf.format(now);
            this.logID = log.getLogID() + 1;
        } else if(activity.equals("QRY")){ //Constructor for AdminController.exportQueryAsFile
            this.userName = log.getUserName();
            this.personID = log.getPersonID();
            this.postID = ID;
            this.commentID = cID;
            this.activity = "Query Export requested for PMUser.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.date = dtf.format(now);
            this.logID = log.getLogID() + 1;
        }
    }

    String toString(){
        return this.date +
                "\nLogID: " + this.getLogID() +
                "\nUsername: " + this.getUserName() +
                "\nRequest type: " + this.getActivity() +
                "\n\tRequest made on PMUserID: " + this.getPersonID() +
                "\n\tPostID: " + this.getPostID() +
                "\n\tCommentID: " + this.getCommentID() + "\n********************\n\n";
    }

    public String getUserName() {
        return userName;
    }

    public String getPersonID() {
        return personID;
    }

    public String getPostID() {
        return postID;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getActivity() {
        return activity;
    }

    public String getDate() {
        return date;
    }

    public int getLogID() {
        return logID;
    }
}
