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
import javafx.scene.control.ChoiceBox;


public class EmployeeEditEpisodeController {
    /* Modules */
    @FXML 
    private Button SubmitButton;
    @FXML 
    private Button CancelButton;
    @FXML
    private Label ErrorLabel;
    @FXML
    private Label IDLabel;

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
    
    @FXML
    private Label LeftLabel1;
    @FXML
    private Label LeftLabel2;
    @FXML
    private Label LeftLabel3;
    @FXML
    private Label LeftLabel4;
    @FXML
    private Label LeftLabel5;
    @FXML
    private Label LeftLabel6;
    
  
    private int current_id = Integer.parseInt(EmployeeController.editID);
    private ResultSet Data;

    @FXML
    private void initialize() throws IOException{
        IDLabel.setText( String.valueOf(EmployeeController.editID));
        initializeLeftLabels();
        RightChoiceBox6.setValue("");
        RightChoiceBox6.getItems().addAll("G", "PG", "PG-13","R","NC-17");
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
        String value3 = returnValue( RightTextField3 , LeftLabel3);
        String value4 = returnValue( RightTextField4 , LeftLabel4);
        String value5 = returnValue( RightTextField5 , LeftLabel5);
        String value6 = "";
        if (RightChoiceBox6.getValue().isBlank()) value6 = LeftLabel6.getText();
        else value6 = RightChoiceBox6.getValue();
    
        try {
            /* Update Episode */
            String updateLang = "update episode set episode_id=?,title=?,season_id=?,episode_num=?,episode_length=?,rating=? where episode_id=?";
            PreparedStatement pstm = App.connectDB.prepareStatement(updateLang);
            pstm.setInt(1,Integer.parseInt(value1));
            pstm.setString(2,value2);
            pstm.setString(3,value3);
            pstm.setString(4,value4);
            pstm.setString(5,value5);
            pstm.setString(6,value6);
            pstm.setInt(7,current_id);
            
            pstm.executeUpdate();
            thirdStageClose();
        } catch (Exception s) {
            ErrorLabel.setText("Error: Query Failed");
            s.printStackTrace();

            try {
                App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"UPDATE\", 0, \"episode\")");
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    private void initializeLeftLabels(){
        String query = "select * from episode where episode_id=\"" + String.valueOf(EmployeeController.editID) + "\"";

        try {
            Statement statement = App.connectDB.createStatement();
            Data = statement.executeQuery(query);
        
            if (Data.next()){
                
                LeftLabel1.setText( String.valueOf( Data.getInt("episode_id")) );
                LeftLabel2.setText( Data.getString("title") );
                LeftLabel3.setText( Data.getString("season_id") );
                LeftLabel4.setText( Data.getString("episode_num") );
                LeftLabel5.setText( Data.getString("episode_length") );
                LeftLabel6.setText( Data.getString("rating") );

            }
            //Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
