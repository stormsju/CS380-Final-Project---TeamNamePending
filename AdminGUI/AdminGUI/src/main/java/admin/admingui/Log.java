package admin.admingui;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/**
 * @author justin Storms
 * @version 1.0 - Log.java
 * @since 5/26/2023
 * Class which enforces user accountability on Admin database access through AdminUI. Logs built here are output to an
 * activity file.
 */
public class Log {
    //private data members
    private String userName, personID, postID, commentID, activity, date;
    private int logID;

    //Class base constructor

    /**
     * Class base constructor. Holds data to log events captured during admin processes
     * @param userName Admin username accessing data via UI.
     * @param personID PicMe UserID being accessed.
     */
    Log(String userName, int personID){
        this.userName = userName;
        this.personID = String.valueOf(personID);
        this.postID = "";
        this.commentID = "";
        this.activity = "";
        this.date = "";
        this.logID = 0; //base log
    }

    /**
     * Class copy-constructor, which takes the details form a base Log object and captures the current activity.
     * Handles Pull requests for PicMe user details and contact IDs.
     * @param log Base constructor generated by admin user activity.
     * @param activity Activity type being processed.
     */
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

    /**
     * Class copy-constructor, which takes the details form a base Log object and captures the current activity.
     * Handles Pull requests for PicMe user pictures and posts.
     * @param log Base constructor generated by admin user activity.
     * @param ID ID of the post or picture queried.
     * @param activity Activity type being processed.
     */
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

    /**
     * Class copy-constructor, which takes the details form a base Log object and captures the current activity.
     * Handles Pull requests for PicMe user picture comments, post comments, and query exports.
     * @param log Base constructor generated by admin user activity.
     * @param ID ID of the post or picture queried.
     * @param cID ID of the comment queried.
     * @param activity Activity type being processed.
     */
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

    /**
     * Method which formats output used when writing logs to file.
     * @return String of log details.
     */
    String toString(){
        return this.date +
                "\nLogID: " + this.getLogID() +
                "\nUsername: " + this.getUserName() +
                "\nRequest type: " + this.getActivity() +
                "\n\tRequest made on PMUserID: " + this.getPersonID() +
                "\n\tPostID: " + this.getPostID() +
                "\n\tCommentID: " + this.getCommentID() + "\n********************\n\n";
    }

    /**
     * Getter method which returns the Admin username.
     * @return String of userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter method which returns the PMUserID.
     * @return String of personID.
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Getter method which returns the PicMe postID.
     * @return String of postID.
     */
    public String getPostID() {
        return postID;
    }

    /**
     * Getter method which returns the PicMe commentID.
     * @return String of postID.
     */
    public String getCommentID() {
        return commentID;
    }

    /**
     * Getter method which returns the Admin activity.
     * @return String of activity.
     */
    public String getActivity() {
        return activity;
    }

    /**
     * Getter method which returns the date of Admin activity
     * occurrence.
     * @return String of date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter method which returns the current log ID.
     * @return int of logID.
     */
    public int getLogID() {
        return logID;
    }
}
