package com.projectdb;


//JavaFx Basics
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

//JDBC - Mariadb Imports
import java.sql.PreparedStatement;

//JavaFx Modules Import
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmployeeEditSeriesCatController {

    /* Modules */
    @FXML 
    private Button SubmitButton;
    @FXML 
    private Button CancelButton;
    @FXML
    private Label IDLabel;
 
    @FXML
    private Label ErrorLabel;
     
    @FXML
    private Label LeftLabel1;
    @FXML
    private Label LeftLabel2;
    
    @FXML
    private TextField RightTextField1;
    @FXML
    private TextField RightTextField2;

    String[] current_id = EmployeeController.editID.split(",");

    @FXML
    private void initialize() throws IOException{
        IDLabel.setText( String.valueOf(EmployeeController.editID));
        initializeLeftLabels();
    }
    
    private static String returnValue( TextField newValue , Label curValue ){
        String value = "";
        
        if ( newValue.getText().isBlank() ){
            value = curValue.getText();
        }
        else value = newValue.getText();
        return value;
    }
    
    private void thirdStageClose(){
        Stage currentStage = (Stage) IDLabel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void CancelButtonOnAction(ActionEvent e) {
        thirdStageClose();
    }

    @FXML
    private void SubmitButtonOnACtion(ActionEvent e){
    
        String value1 = returnValue( RightTextField1 , LeftLabel1); 
        String value2 = returnValue( RightTextField2 , LeftLabel2);
    
        try {
            /* Update Lang */
            String updateLang = "update series_category set series_id=?, category_id=? where series_id=? and category_id=?";
            PreparedStatement pstm = App.connectDB.prepareStatement(updateLang);
            pstm.setString(1,value1);
            pstm.setString(2,value2);
            pstm.setString(3,current_id[0]);
            pstm.setString(4,current_id[1]);
            
            pstm.executeUpdate();
            thirdStageClose();
        } catch (Exception s) {
            ErrorLabel.setText("Error: Query Failed");
            s.printStackTrace();

            try {
                App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"UPDATE\", 0, \"series_category\")");
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    private void initializeLeftLabels(){
        String[] current_id = EmployeeController.editID.split(",");

        LeftLabel1.setText(current_id[0]);
        LeftLabel2.setText(current_id[1]);
    }

}
