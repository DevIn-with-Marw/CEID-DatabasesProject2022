package com.projectdb;

//Java Imports
import java.io.IOException;

//JavaFx Modules
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//JDBC - Mariadb Imports
import java.sql.Connection;
/*
    JavaFX App
*/

public class App extends Application {

    public static Scene scene;
    public static String email;

    public static DatabaseConnection currentConnection = new DatabaseConnection();
    public static Connection connectDB = currentConnection.getConnection();
    
    @Override
    public void start(Stage primarystage) throws IOException {
        scene = new Scene(loadFXML("LoginPage"), 600, 400);
        //scene = new Scene(loadFXML("editCustomerProfile"), 500 , 740);
        primarystage.initStyle(StageStyle.UNDECORATED);
        primarystage.setScene(scene);
        primarystage.show();
    }
    //Methods

    //change the scene
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // return a new parent scene 
    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Connection getConnection()
    {
        return connectDB;
    }

    // launch the application
    public static void main(String[] args) {
        launch();
    }

}