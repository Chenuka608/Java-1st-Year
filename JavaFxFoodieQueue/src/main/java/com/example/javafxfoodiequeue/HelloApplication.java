package com.example.javafxfoodiequeue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 643, 610);   //height and width of the application
        stage.setTitle("Welcome to FoodieQueue Management System !"); //title of the application
        stage.setScene(scene);
        stage.show();
    }

}