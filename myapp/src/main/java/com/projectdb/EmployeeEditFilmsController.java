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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmployeeEditFilmsController {

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
    private Label LeftLabel3;
    @FXML
    private Label LeftLabel4;
    @FXML
    private Label LeftLabel5;
    @FXML
    private Label LeftLabel6;
    @FXML
    private Label LeftLabel7;
    @FXML
    private Label LeftLabel8;
    @FXML
    private Label LeftLabel9;
    
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
    private TextField RightTextField6;
    @FXML
    private TextField RightTextField7;
    @FXML
    private ChoiceBox<String> RightChoiceBox8;
    @FXML
    private TextField RightTextField9;

    private int current_id = Integer.parseInt(EmployeeController.editID);
    private ResultSet Data;


    @FXML
    private void initialize() throws IOException{
        IDLabel.setText( String.valueOf(EmployeeController.editID));
        initializeLeftLabels();
        RightChoiceBox8.setValue("");
        RightChoiceBox8.getItems().addAll("G", "PG", "PG-13","R","NC-17");
    
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
        String value6 = returnValue( RightTextField6 , LeftLabel6);
        String value7 = returnValue( RightTextField7 , LeftLabel7);
        
        String value8 = "";
        if ( RightChoiceBox8.getValue().isBlank()) {
            value8 = LeftLabel8.getText();
        }
        else value8 = RightChoiceBox8.getValue();

        String value9 = returnValue( RightTextField9 , LeftLabel9);
    
        try {
            /* Update  Films */
            String updateQuery = "update film set film_id=?,title=?,film_description=?,release_year=?,language_id=?,original_language_id=?,film_length=?,rating=?,special_features=? where film_id=?";
            PreparedStatement pstm = App.connectDB.prepareStatement(updateQuery);
            pstm.setInt(1,Integer.parseInt(value1));
            pstm.setString(2,value2);
            pstm.setString(3,value3);
            pstm.setString(4,value4);
            pstm.setString(5,value5);
            pstm.setString(6,value6);
            pstm.setString(7,value7);
            pstm.setString(8,value8);
            pstm.setString(9,value9);
            pstm.setInt(10,current_id);
            
            pstm.executeUpdate();
            thirdStageClose();
        } catch (Exception s) {
            ErrorLabel.setText("Error: Query Failed");
            s.printStackTrace();

            try {
                App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"UPDATE\", 0, \"film\")");
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    private void initializeLeftLabels(){
        String query = "select * from film where film_id=\"" + String.valueOf(EmployeeController.editID) + "\"";

        try {
            Statement statement = App.connectDB.createStatement();
            Data = statement.executeQuery(query);
        
            if (Data.next()){
                
                //LeftLabel.setText( Data.getString("") );
                LeftLabel1.setText( Data.getString("film_id") );
                LeftLabel2.setText( Data.getString("title") );
                LeftLabel3.setText( Data.getString("film_description") );
                LeftLabel4.setText( String.valueOf(Data.getInt("release_year")) );
                LeftLabel5.setText( Data.getString("language_id") );
                LeftLabel6.setText( Data.getString("original_language_id") );
                LeftLabel7.setText( Data.getString("film_length") );
                LeftLabel8.setText( Data.getString("rating") );
                LeftLabel9.setText( Data.getString("special_features") );

            }
            //Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
