package com.example.gui;

public enum Views {
    HOME("HomePage.fxml"),
    PICTURESELECT("PictureSelectPage.fxml"),
    PROFILE("ProfilePage.fxml");

    private String fileName;

    Views(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
