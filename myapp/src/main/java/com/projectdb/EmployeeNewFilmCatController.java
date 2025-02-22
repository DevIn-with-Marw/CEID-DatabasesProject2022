package com.projectdb;

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

public class EmployeeNewFilmCatController {
    
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

    //Methods 

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
        if ( RightTextField2.getText().isBlank() || RightTextField1.getText().isBlank()){
            ErrorLabel.setText("Error: Empty Field");
        }
        else{
            try {
                //Create the database Connection
                PreparedStatement pstmt;
                String insertRecord = "";
                
                insertRecord = "insert into film_category values(?,?)";
                pstmt = App.connectDB.prepareStatement(insertRecord);
                    
                pstmt.setString(1, RightTextField1.getText());
                pstmt.setString(2, RightTextField2.getText());
                
                pstmt.executeUpdate();
                
                pstmt.close();
                thirdStageClose();

            } catch (Exception s) {
                s.printStackTrace();
                ErrorLabel.setText("Error: Query Failed");
                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"INSERT\", 0, \"film_category\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }
}
