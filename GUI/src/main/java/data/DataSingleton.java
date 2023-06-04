package data;

import javafx.scene.image.Image;

public class DataSingleton {

    private static final DataSingleton instance = new DataSingleton();

    private Image selectedImg;
    private String caption;
    private String userInfo;
    private String date;
    private String username;

    public static DataSingleton getInstance() {
        return instance;
    }

    public Image getImg() {
        return selectedImg;
    }

    public void setImg(Image img) {
        selectedImg = img;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String info) {
        this.userInfo = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
