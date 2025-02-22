package com.projectdb;

import java.io.IOException;

//JavaFx Basics
import javafx.stage.Stage;
import javafx.fxml.FXML;

//JavaFx Modules Import
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

//JDBC - Mariadb Imports
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.util.Duration;


public class CustomerLogActions {
    
    @FXML
    private Label logCustomerLabel;
    @FXML
    private Button logCancelButton;
    @FXML
    private TableView<ModelLog> CustomerActionLog;
    @FXML
    private TableColumn<ModelLog,Integer> logLogEntry;
    @FXML
    private TableColumn<ModelLog,String> logTable;
    @FXML
    private TableColumn<ModelLog,String> logType;
    @FXML
    private TableColumn<ModelLog,String> logDatetimeOFEvent;
    @FXML
    private TableColumn<ModelLog,Integer> logSuccess;
    @FXML
    private TableColumn<ModelLog, String> logemail;

    private String customer_email = AdminHomeController.email;
    private String startDate = AdminHomeController.startdate;
    private String endDate = AdminHomeController.enddate;

    @FXML
    private void initialize() throws IOException{
        initializeTable();
        logCustomerLabel.setText("Actions log:     "+customer_email);
    }
    
    private void thirdStageClose(){
        Stage currentStage = (Stage) logCustomerLabel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void CancelButtonOnAction(ActionEvent e) {
        thirdStageClose();
    }

    private void initializeTable(){
        ObservableList<ModelLog> DataObservableList = FXCollections.observableArrayList();
        try {
            String query = "select * from log;";

            if ((!AdminHomeController.email.isEmpty()) && (AdminHomeController.tablename.equals("All Tables")))
                query = "select * from log where email=\""+ customer_email +"\"and date_of_event BETWEEN \""+ startDate +"\" and \""+ endDate +"\"";
            else if (AdminHomeController.email.isEmpty() && (!AdminHomeController.tablename.equals("Table Name")))
                query = "select * from log where table_name=\""+ AdminHomeController.tablename +"\"and date_of_event BETWEEN \""+ startDate +"\" and \""+ endDate +"\"";
            else 
                query = "select * from log where email=\""+ customer_email +"\" and table_name=\""+ AdminHomeController.tablename +"\"and date_of_event BETWEEN \""+ startDate +"\" and \""+ endDate +"\"";
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery(query);
        
            while(Data.next()){
                DataObservableList.add(new ModelLog( Data.getInt(1), Data.getString(2), Data.getString(3), Data.getString(4),Data.getInt(5), Data.getString(6)));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            logCustomerLabel.setText("Query Failed");
            e.printStackTrace();
            PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
            );
            visiblePause.setOnFinished(
                    event2 ->  logCustomerLabel.setText("Actions log:     "+customer_email)
            );
            visiblePause.play();
        }

        logLogEntry.setCellValueFactory(new PropertyValueFactory<>("log_id"));
        logTable.setCellValueFactory(new PropertyValueFactory<>("table_name"));
        logemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        logDatetimeOFEvent.setCellValueFactory(new PropertyValueFactory<>("datetime"));
        logType.setCellValueFactory(new PropertyValueFactory<>("type"));
        logSuccess.setCellValueFactory(new PropertyValueFactory<>("success"));
        CustomerActionLog.setItems(DataObservableList);
    }
}
