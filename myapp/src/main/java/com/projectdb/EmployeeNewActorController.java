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

public class EmployeeNewActorController {
    
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
        if ( RightTextField2.getText().isBlank() || RightTextField3.getText().isBlank()  ){
            ErrorLabel.setText("Error: Only the ID field can be left Empty");
        }
        else{
            try {
                //Create the database Connection
                PreparedStatement pstmt;
                String insertRecord = "";
                
                if ( RightTextField1.getText().isBlank() ){
                    insertRecord = "insert into actor values(null,?,?)";
                    pstmt = App.connectDB.prepareStatement(insertRecord);

                    pstmt.setString(1, RightTextField2.getText());
                    pstmt.setString(2, RightTextField3.getText());
                }
                else {
                    insertRecord = "insert into actor values(?,?,?)";
                    pstmt = App.connectDB.prepareStatement(insertRecord);
                    
                    pstmt.setString(1, RightTextField1.getText());
                    pstmt.setString(2, RightTextField2.getText());
                    pstmt.setString(3, RightTextField3.getText());
                }
                
                pstmt.executeUpdate();
                
                pstmt.close();
                thirdStageClose();

            } catch (Exception s) {
                s.printStackTrace();
                ErrorLabel.setText("Error: Query Failed");
                
                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"INSERT\", 0, \"actor\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }
}
