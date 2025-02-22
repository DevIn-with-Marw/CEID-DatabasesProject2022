package com.projectdb;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.projectdb.util.DBUtil;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminAddEmployeeController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField AdminUI_AddEmp_activity;

    @FXML
    private TextField AdminUI_AddEmp_email;

    @FXML
    private TextField AdminUI_AddEmp_fname;

    @FXML
    private TextField AdminUI_AddEmp_id;

    @FXML
    private TextField AdminUI_AddEmp_lname;

    @FXML
    private TextField AdminUI_AddEmp_phone;

    @FXML
    private TextField AdminUI_AddEmp_regdate;

    @FXML
    private Button AdminUI_AddEmp_CancelButton;

    @FXML
    private Button AdminUI_AddEmp_SubmitButton;

    @FXML
    private Label AdminUI_AddEmp_IndicationLabel;

    @FXML
    private Label AdminUI_AddEmp_ErrorReportLabel;

    @FXML
    private Label AdminUI_AddEmp_ErrorReportLabel2;

    @FXML
    private Label AdminUI_AddEmp_ErrorReportLabel3;

    @FXML
    private Label AdminUI_AddEmp_ErrorReportLabel4;

    @FXML
    private Label AdminUI_AddEmp_ErrorReportLabel5;

    @FXML
    void cancel(ActionEvent event) throws Exception{
        //int uidlast =-1;
        Stage currentstage = (Stage) AdminUI_AddEmp_ErrorReportLabel.getScene().getWindow();
 
        AdminUI_AddEmp_ErrorReportLabel.setText("Action cancelled!");
        PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(3)
        );
        visiblePause.setOnFinished(
            some_event -> currentstage.close()
        );
        visiblePause.play();

    }

    @FXML
    private void confirmEmployee(ActionEvent event) throws Exception{
        //stage and transition initializations//
        Stage currentstage = (Stage) AdminUI_AddEmp_ErrorReportLabel.getScene().getWindow();
  
        PauseTransition AfterMessageReturn = new PauseTransition( Duration.seconds(4));
        AfterMessageReturn.setOnFinished(someevent2 -> currentstage.close());
        
        
        //field initializations//
        int id=-1;             //NULL
        int phone=-1;          //NULL
        String email = "";
        String name="";
        String surname="";
        String regdate="";     //NOW()
        int active=-1;      

  
        try {
            App.connectDB.setAutoCommit(false);
            /*all fields that use the ErrorIndicationLabel except for the first listen on the Label 
             * first. If the Label is not empty, the function returns, to avoid too fast Text changes 
             * on the Label. The first field (id) is excluded, because the Label is empty for sure. 
             * If the given id is invalid, the field gets disabled, so it will never use the Label 
             * again. 
             * 
             * If one of the necessary fields is missing, the function returns, making sure that 
             * no insertions will take  place in the DB.
            */

            /* necessary fields */
            if ( AdminUI_AddEmp_email.getText().isBlank() || AdminUI_AddEmp_fname.getText().isBlank() || AdminUI_AddEmp_lname.getText().isBlank())
            {
                AdminUI_AddEmp_ErrorReportLabel.setText("Missing fields. Please complete all fields with the *.");
                notextLabel(AdminUI_AddEmp_ErrorReportLabel);
                //log update: manual
                try {
                    String logfailinsert1 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"user\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logfailinsert1);
                    String logfailinsert2 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"employee\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logfailinsert2);
                }catch (Exception s){ /* if this query fails, we don't care */
                    s.printStackTrace();
                }
                App.connectDB.commit();
                App.connectDB.setAutoCommit(true);
                return;
            }

            if ((!AdminUI_AddEmp_id.getText().isBlank()) && (!AdminUI_AddEmp_id.isDisabled()))
            {
                id = Integer.parseInt(AdminUI_AddEmp_id.getText());
                ResultSet rs = DBUtil.DBexecute(App.connectDB, "select user_id from user;");
                while (rs.next())
                {
                    if (id == rs.getInt("user_id")) 
                    {  
                        AdminUI_AddEmp_ErrorReportLabel2.setText("Invalid Id: Id will change automatically. Please proceed.");
                        AdminUI_AddEmp_id.setDisable(true);
                        notextLabel(AdminUI_AddEmp_ErrorReportLabel2);
                        id = -1;
                        //user insertion failed: log update: manual
                        try {
                            String logfailinsert1 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"user\");";
                            DBUtil.DBexecuteQuery(App.connectDB, logfailinsert1);
                            String logfailinsert2 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"employee\");";
                            DBUtil.DBexecuteQuery(App.connectDB, logfailinsert2);
                        }catch (Exception s){ /* if this query fails, we don't care */
                            s.printStackTrace();
                        }
                        
                        break;
                        //since id (not necessary field) was invalid, you can procced. 
                        //the value will be fixed by the database
                    }
                }
                rs.close();
            }

            if (!AdminUI_AddEmp_email.getText().isBlank()) 
            {
                email = AdminUI_AddEmp_email.getText();
                ResultSet rs = DBUtil.DBexecute(App.connectDB, "select email from user;");
                while (rs.next())
                {
                    if (email.equals(rs.getString("email")))
                    {   AdminUI_AddEmp_ErrorReportLabel3.setText("This email is already being used. Please change your email.");
                        notextLabel(AdminUI_AddEmp_ErrorReportLabel3);
                        //user insertion failed: log update: manual
                        try {
                            String logfailinsert1 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"user\");";
                            DBUtil.DBexecuteQuery(App.connectDB, logfailinsert1);
                            String logfailinsert2 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"employee\");";
                            DBUtil.DBexecuteQuery(App.connectDB, logfailinsert2);
                        }catch (Exception s){ /* if this query fails, we don't care */
                            s.printStackTrace();
                        }
                        //since email (necessary field), was invalid, do not procced
                        App.connectDB.commit();
                        App.connectDB.setAutoCommit(true);
                        return;
                    }
                }
                rs.close();
            }

            if (!AdminUI_AddEmp_fname.getText().isBlank())
            {
                name = AdminUI_AddEmp_fname.getText();
            }

            if (!AdminUI_AddEmp_lname.getText().isBlank())
            {
                surname = AdminUI_AddEmp_lname.getText();
            }
            
            /*unecessary fields*/
            if (!AdminUI_AddEmp_activity.getText().isBlank())
            {
                try  {
                    active = Integer.parseInt(AdminUI_AddEmp_activity.getText());
                }catch (Exception e)
                {
                    active=-1;
                }
                
            }

            if (!AdminUI_AddEmp_regdate.getText().isBlank())
            {
                regdate = AdminUI_AddEmp_regdate.getText();
            }
            
            if (!AdminUI_AddEmp_phone.getText().isBlank())
            {
                phone = Integer.parseInt(AdminUI_AddEmp_phone.getText());
            }
            
            /* USER INSERTION */
            String userinsertion = "insert into user values (";
            if (id == -1) userinsertion = userinsertion.concat("NULL, ");
            else userinsertion = userinsertion.concat(String.valueOf(id)+", ");

            userinsertion = userinsertion.concat("\""+name+"\", \""+surname+"\", \""+email+"\", ");
            
            if (active == -1) userinsertion = userinsertion.concat("1, ");
            else userinsertion = userinsertion.concat(active+", ");

            if (regdate.isEmpty()) userinsertion = userinsertion.concat("NOW(), \"EMPLOYEE\", 0);");
            else userinsertion = userinsertion.concat("\""+regdate+"\", \"EMPLOYEE\", 0);");

            try {
                DBUtil.DBexecuteQuery(App.connectDB, userinsertion);
                //user insertion successful: db trigger will update the log
            }
            catch (Exception e) //user insertion failed
            {
                AdminUI_AddEmp_ErrorReportLabel4.setText("Something went wrong. Trying again with today's date as Register Date...");
                notextLabel(AdminUI_AddEmp_ErrorReportLabel4); 
                try {
                    userinsertion ="insert into user values (";
                    if (id == -1) userinsertion = userinsertion.concat("NULL, ");
                    else userinsertion = userinsertion.concat(String.valueOf(id)+", ");
            
                    userinsertion = userinsertion.concat("\""+name+"\", \""+surname+"\", \""+email+"\", ");
                    
                    if (active==-1) userinsertion = userinsertion.concat("DEFAULT, NOW(), \"EMPLOYEE\", 0);");
                    else userinsertion = userinsertion.concat(active+", NOW(), \"EMPLOYEE\", 0);");
                    DBUtil.DBexecuteQuery(App.connectDB, userinsertion);

                    //user insertion was successful: db trigger will update log
                    //get the id 
                    ResultSet rs = DBUtil.DBexecute(App.connectDB, "select user_id from user order by user_id desc limit 0,1;");
                    if (rs.next()) id = rs.getInt("user_id");
                    rs.close();
                    
                }catch (Exception e1)   //user insertion failed twice
                {
                    AdminUI_AddEmp_ErrorReportLabel4.setText("Could not register new user. Returning to HomePage...");  
                    //log update: manual
                   
                    String logfailinsert1 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"user\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logfailinsert1);
                    String logfailinsert2 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"employee\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logfailinsert2);
                   
                    //return to Home Page
                    AfterMessageReturn.play();  
                    App.connectDB.commit();
                    App.connectDB.setAutoCommit(true);
                    return;
                }
            }
            //User inserted successfully
            /* INSERT NEW EMPLOYEE */
            String emplinsertion="insert into employee values ("+id+", ";
            if (phone == -1)  emplinsertion=emplinsertion.concat("NULL);");
            else  emplinsertion=emplinsertion.concat(String.valueOf(phone)+");");
            
            try {
                DBUtil.DBexecuteQuery(App.connectDB, emplinsertion);
                AdminUI_AddEmp_ErrorReportLabel5.setText("Employee registered!");
                //employee insertion was successful:db trigger will update log
                //return to Home Page
                AfterMessageReturn.play();
            }catch (Exception e)    //employee insertion failed
            {
                App.connectDB.rollback();
                //log update : manual
                String logfailinsert1 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"user\");";
                DBUtil.DBexecuteQuery(App.connectDB, logfailinsert1);
                String logfailinsert2 = "insert into log values (NULL,\""+App.email+"\", NOW(), \"INSERT\", 0, \"employee\");";
                DBUtil.DBexecuteQuery(App.connectDB, logfailinsert2);
             
                //label indication 
                AdminUI_AddEmp_ErrorReportLabel5.setText("Could not register employee.\n User was deleted! Returning to Home Page...");
                AfterMessageReturn.play();  

                e.printStackTrace();
            } 
        }catch (Exception a)
        {
            a.printStackTrace();
        }

        App.connectDB.commit();
        App.connectDB.setAutoCommit(true);
    }

    void notextLabel (Label  l)
    {
        PauseTransition ntL = new PauseTransition(Duration.seconds(3));
        ntL.setOnFinished(someevent -> l.setText(""));
        ntL.play();
    }

    @FXML
    void initialize() {
        AdminUI_AddEmp_ErrorReportLabel.setText("");    
        AdminUI_AddEmp_ErrorReportLabel2.setText("");   
        AdminUI_AddEmp_ErrorReportLabel3.setText(""); 
        AdminUI_AddEmp_ErrorReportLabel4.setText(""); 
        AdminUI_AddEmp_ErrorReportLabel5.setText(""); 

    }

}
