package com.projectdb;

import java.io.IOException;

//JavaFx Basics
import javafx.stage.Stage;
import javafx.fxml.FXML;

//JavaFx Modules Import
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

//JDBC - Mariadb Imports
import java.sql.ResultSet;
import java.sql.Statement;


public class EmployeeGrossingController {

    @FXML 
    private Button CancelButton;
    @FXML
    private Label ErrorLabel;
    @FXML
    private TableView<Modelz3_1> FilmTable;
    @FXML
    private TableView<Modelz3_1> SeriesTable;
    @FXML
    private TableColumn<Modelz3_1,Integer> FilmID;
    @FXML
    private TableColumn<Modelz3_1,String> FilmTitle;
    @FXML
    private TableColumn<Modelz3_1,Integer> SeriesID;
    @FXML
    private TableColumn<Modelz3_1,String> SeriesTitle;


    //Methods 
    @FXML
    private void initialize() throws IOException{
        initializeFilms();
        initializeSeries();
    }

    private void thirdStageClose(){
        Stage currentStage = (Stage) ErrorLabel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void CancelButtonOnAction(ActionEvent e) {
        thirdStageClose();
    }

    private void initializeFilms(){
        ObservableList<Modelz3_1> DataObservableList = FXCollections.observableArrayList();
        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from z3_1films;");
        
            while(Data.next()){
                DataObservableList.add(new Modelz3_1( Data.getInt(1), Data.getString(2)));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FilmID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        FilmTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        FilmTable.setItems(DataObservableList);
    }


    private void initializeSeries(){
        ObservableList<Modelz3_1> DataObservableList = FXCollections.observableArrayList();
        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from z3_1series;");
        
            while(Data.next()){
                DataObservableList.add(new Modelz3_1( Data.getInt(1), Data.getString(2)));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SeriesID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        SeriesTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        SeriesTable.setItems(DataObservableList);
    }

}
