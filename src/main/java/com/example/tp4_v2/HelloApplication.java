package com.example.tp4_v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.tp4_v2.Game.*;
import static java.lang.Math.max;


public class HelloApplication extends Application {
    final int SCREEN_SIZE = 800;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")).load();

        stage.setTitle("RPG !");

        stage.setScene(new Scene(root, SCREEN_SIZE, SCREEN_SIZE));
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}