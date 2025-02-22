package com.projectdb;

import java.io.IOException;
//JavaFx Basics
import javafx.stage.Stage;
import javafx.fxml.FXML;

//JDBC - Mariadb Imports
import java.sql.PreparedStatement;

//JavaFx Modules Import
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;


public class EmployeeNewEpisodesController {
    /* Modules */
    @FXML 
    private Button SubmitButton;
    @FXML 
    private Button CancelButton;
    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField RightTextField1;
    @FXML
    private TextField RightTextField2;
    @FXML
    private TextField RightTextField3;
    @FXML
    private TextField RightTextField4;
    @FXML
    private TextField RightTextField5;
    @FXML
    private ChoiceBox<String> RightChoiceBox6;
    
    //Methods 
    @FXML
    private void initialize() throws IOException{
        RightChoiceBox6.setValue("");
        RightChoiceBox6.getItems().addAll("G", "PG", "PG-13","R","NC-17");
    }

    private void thirdStageClose(){
        Stage currentStage = (Stage) ErrorLabel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void CancelButtonOnAction(ActionEvent e) {
        thirdStageClose();
    }

    @FXML
    private void SubmitButtonOnACtion(ActionEvent e) {
        if ( RightTextField2.getText().isBlank() || RightTextField3.getText().isBlank() || RightTextField4.getText().isBlank() || RightTextField5.getText().isBlank() || RightChoiceBox6.getValue().isBlank() ){
            ErrorLabel.setText("Error: Only the ID field can be left Empty");
        }
        else{
            try {
                //Create the database Connection
                PreparedStatement pstmt;
                String insertRecord = "";
                
                if ( RightTextField1.getText().isBlank() ){
                    insertRecord = "insert into episode values(null,?,?,?,?,?)";
                    pstmt = App.connectDB.prepareStatement(insertRecord);

                    pstmt.setString(1, RightTextField2.getText());
                    pstmt.setString(2, RightTextField3.getText());
                    pstmt.setString(3, RightTextField4.getText());
                    pstmt.setString(4, RightTextField5.getText());
                    pstmt.setString(5, RightChoiceBox6.getValue());
                    
                }
                else {
                    insertRecord = "insert into episode values(?,?,?,?,?,?)";
                    pstmt = App.connectDB.prepareStatement(insertRecord);
                    
                    pstmt.setString(1, RightTextField1.getText());
                    pstmt.setString(2, RightTextField2.getText());
                    pstmt.setString(3, RightTextField3.getText());
                    pstmt.setString(4, RightTextField4.getText());
                    pstmt.setString(5, RightTextField5.getText());
                    pstmt.setString(6, RightChoiceBox6.getValue());
                    
                }   
                pstmt.executeUpdate();
                
                pstmt.close();
                thirdStageClose();

            } catch (Exception s) {
                s.printStackTrace();
                ErrorLabel.setText("Error: Query Failed");
                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"INSERT\", 0, \"episode\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

}
