module com.Project.PicMe.admingui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
            
        //requires org.controlsfx.controls;
                        //requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires jakarta.persistence;
    requires lombok;
    requires spring.beans;
    requires spring.web;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires jakarta.transaction;

    opens admin.admingui to javafx.fxml;
    exports com.Project.PicMe.admingui;
}