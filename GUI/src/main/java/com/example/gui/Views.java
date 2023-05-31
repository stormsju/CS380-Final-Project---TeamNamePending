package com.example.gui;

public enum Views {
    CHANGEPASSWORD("ChangePasswordPage.fxml"),
    CHANGEUSERNAME("ChangeUsernamePage.fxml"),
    FOLLOWERS("FollowersPage.fxml"),
    HOME("HomePage.fxml"),
    PICTURESELECT("PictureSelectPage.fxml"),
    PREPOST("PrePostPage.fxml"),
    PROFILE("ProfilePage.fxml"),
    SEARCH("SearchPage.fxml"),
    SETTINGS("SettingsPage.fxml");

    private String fileName;

    Views(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
