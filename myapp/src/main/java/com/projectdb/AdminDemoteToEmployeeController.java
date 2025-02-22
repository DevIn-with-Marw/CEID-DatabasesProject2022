package com.projectdb;

import java.net.URL;
import java.util.ResourceBundle;

import com.projectdb.model.AdminUser;
import com.projectdb.util.DBUtil;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminDemoteToEmployeeController {
    
    public static AdminUser convert = new AdminUser();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AdminUI_MEmp_Base;

    @FXML
    private Label AdminUI_MEmp_IndicationLabel;

    @FXML
    private Button AdminUI_MEmp_NextButton;

    @FXML 
    private Button AdminUI_Memp_CancelButton;

    @FXML
    private TextField AdminUI_MEmp_PhoneField;

    @FXML 
    private void initialize()
    {
        AdminUI_MEmp_IndicationLabel.setText("Please insert Employee's phone number (phone number can be empty):");
    }

    @FXML
    private void proceed(ActionEvent event) throws Exception{
        Stage currentstage = (Stage) AdminUI_MEmp_IndicationLabel.getScene().getWindow();
        int adid = convert.getAdminUserId();

        try {   
            //atomic action
            App.connectDB.setAutoCommit(false);
            
            //part 1: delete the user from admin table: db trigger will update log
            String movequery_part1 = "delete from admin where admin_id="+adid+";";
            DBUtil.DBexecuteQuery(App.connectDB, movequery_part1);
            //part 2: insert the user into the employee table
            //ask for phone number
            int phone = -1;
            if (!AdminUI_MEmp_PhoneField.getText().isBlank()) 
            {
                phone = Integer.parseInt(AdminUI_MEmp_PhoneField.getText());
                String movequery_part2 = "insert into employee values ("+adid+", "+phone+");";
                DBUtil.DBexecuteQuery(App.connectDB, movequery_part2);
            }
            else {
                String movequery_part2 = "insert into employee values ("+adid+", NULL );";
                DBUtil.DBexecuteQuery(App.connectDB, movequery_part2);
            }
            //employee insertion successful: db trigger will update log

            //part 3: update the user's type: db trigger will update the log
            String movequery_part3 = "update user set usertype=\"EMPLOYEE\" where user_id="+adid;
            DBUtil.DBexecuteQuery(App.connectDB, movequery_part3);

            AdminUI_MEmp_IndicationLabel.setText("Action completed!");
            PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
            );
            visiblePause.setOnFinished(
                someevent -> currentstage.close()
            );
            visiblePause.play();
        }catch (Exception e) {//action failed
           
            try{
                //undo changes
                App.connectDB.rollback();
            }
            catch (Exception s) {s.printStackTrace();}

            //log update: manual (no matter the query that failed, the whole action is considered failed)
            //any exceptions cause here will be thrown
            String logupdate1 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"admin\");";
            DBUtil.DBexecuteQuery(App.connectDB, logupdate1);
            String logupdate2 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"employee\");";
            DBUtil.DBexecuteQuery(App.connectDB, logupdate2);
            String logupdate3 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"UPDATE\", 0, \"user\");";
            DBUtil.DBexecuteQuery(App.connectDB, logupdate3);
        }

        App.connectDB.commit();
        App.connectDB.setAutoCommit(true); 

    }

    @FXML
    private void cancel(ActionEvent event)
    {
        Stage currentstage = (Stage) AdminUI_MEmp_IndicationLabel.getScene().getWindow();
        AdminUI_MEmp_IndicationLabel.setText("Action cancelled!");
        PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(3)
        );
        visiblePause.setOnFinished(
            some_event -> currentstage.close()
        );
        visiblePause.play();
        
    }


 
}
