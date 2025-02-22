package com.projectdb;

//JavaFx Basics
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

//JDBC - Mariadb Imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//JavaFx Modules Import
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmployeeEditCountryController {

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

    private int current_id = Integer.parseInt(EmployeeController.editID);
    private ResultSet Data;


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
            /* Update Country */
            String updateLang = "update country set country_id=?,country=? where country_id=?";
            PreparedStatement pstm = App.connectDB.prepareStatement(updateLang);
            pstm.setInt(1,Integer.parseInt(value1));
            pstm.setString(2,value2);
            pstm.setInt(3,current_id);
            
            pstm.executeUpdate();
            thirdStageClose();
        } catch (Exception s) {
            ErrorLabel.setText("Error: Query Failed");
            s.printStackTrace();

            try {
                App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"UPDATE\", 0, \"country\")");
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    private void initializeLeftLabels(){
        String query = "select * from country where country_id=\"" + String.valueOf(EmployeeController.editID) + "\"";

        try {
            Statement statement = App.connectDB.createStatement();
            Data = statement.executeQuery(query);
        
            if (Data.next()){
                
                LeftLabel1.setText( String.valueOf( Data.getInt("country_id")) );
                LeftLabel2.setText( String.valueOf( Data.getString("country")) );

            }
            //Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
