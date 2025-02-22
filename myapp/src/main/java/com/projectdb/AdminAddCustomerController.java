package com.projectdb;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.projectdb.util.DBUtil;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AdminAddCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> AdminUI_AddCust_CustomerTypeBox;

    @FXML
    private TextField AdminUI_AddCust_IdField;

    @FXML
    private TextField AdminUI_AddCust_PhoneField;

    @FXML
    private TextField AdminUI_AddCust_FirstNameField;

    @FXML
    private TextField AdminUI_AddCust_LastNameField;

    @FXML
    private TextField adminUI_AddCust_EmailField;

    @FXML
    private TextField AdminUI_AddCust_ActiveField;

    @FXML
    private TextField AdminUI_AddCust_CountryField;

    @FXML
    private TextField AdminUI_AddCust_CityField;

    @FXML
    private TextField AdminUI_AddCust_DistrictField;

    @FXML
    private TextField AdminUI_AddCust_AddressField;

    @FXML
    private TextField AdminUI_AddCust_PostCodeField;

    @FXML
    private Button AdminUI_AddCust_SubmitButton;

    @FXML 
    private Button AdminUI_AddCust_CancelButton;

    @FXML
    private Label AdminUI_AddCust_NewCustomerLabel;

    @FXML
    private Label AdminUI_AddCust_NewAddressLabel;

    @FXML
    private Label AdminUI_AddCust_TypeLabel;

    @FXML
    private Label AdminUI_AddCust_ErrorReportLabel1;

    @FXML
    private Label AdminUI_AddCust_ErrorReportLabel2;

    @FXML
    void initialize() throws Exception{
        AdminUI_AddCust_TypeLabel.setVisible(true);
        AdminUI_AddCust_ErrorReportLabel1.setText("");
        AdminUI_AddCust_ErrorReportLabel2.setText("");

        AdminUI_AddCust_CustomerTypeBox.setValue("");
        AdminUI_AddCust_CustomerTypeBox.getItems().addAll("FILMS ONLY", "SERIES ONLY", "FILMS AND SERIES");

    }

    @FXML
    void submitCustomer(ActionEvent event) throws Exception{
        //STEP 1: initializations
        //for user
        int id =-1;
        String type = "";
        String fname= "";
        String lname= "";
        String email= "";
        int active = 1;
        
        //for address
        int country_id =-1;
        int city_id=-1;
        int address_id =-1;
        String country = "";
        String city = "";
        String district = "";
        String address = "";
        String pscode = "";
        String phone = "";

        PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(3)
        );
        visiblePause.setOnFinished(
            some_event -> AdminUI_AddCust_ErrorReportLabel1.setText("")
        );

        PauseTransition visiblePause2 = new PauseTransition(
            Duration.seconds(3)
        );
        visiblePause2.setOnFinished(
            some_event -> AdminUI_AddCust_ErrorReportLabel2.setText("")
        );

        //STEP 2: get the fields
        if ((!AdminUI_AddCust_IdField.getText().isBlank()) && (!AdminUI_AddCust_IdField.isDisabled())) 
        {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            id = Integer.parseInt(AdminUI_AddCust_IdField.getText());
            
            ResultSet idsinbase = DBUtil.DBexecute(App.connectDB, "select user_id from user;");
            while (idsinbase.next())
            {
                if (id == idsinbase.getInt("user_id"))
                {
                    AdminUI_AddCust_ErrorReportLabel1.setText("Invalid Id: Id will be changed automatically.");
                    visiblePause.play();
                    AdminUI_AddCust_IdField.setDisable(true);
                    break;              
                }
            }
            idsinbase.close();
        }
        
        if (!AdminUI_AddCust_CustomerTypeBox.getValue().isBlank())
        {
            hideTypeLabel();
            type = AdminUI_AddCust_CustomerTypeBox.getValue();
        }
        else{
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!AdminUI_AddCust_FirstNameField.getText().isBlank())
        {
            fname = AdminUI_AddCust_FirstNameField.getText();
        }
        else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!AdminUI_AddCust_LastNameField.getText().isBlank())
        {
            lname = AdminUI_AddCust_LastNameField.getText();
        }
        else{
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!adminUI_AddCust_EmailField.getText().isBlank())
        {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            email = adminUI_AddCust_EmailField.getText();
            ResultSet emailsindb = DBUtil.DBexecute(App.connectDB, "select email from user;");
            while(emailsindb.next())
            {
                if (email.equals(emailsindb.getString("email")))
                {
                    AdminUI_AddCust_ErrorReportLabel1.setText("Email is already being used. Please change your email.");
                    visiblePause.play();
                    return;
                }
            }
            emailsindb.close();
        }else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }
    
        if (AdminUI_AddCust_ActiveField.getText().equals("0"))
        {
            active = Integer.parseInt(AdminUI_AddCust_ActiveField.getText());
        }

        if (!AdminUI_AddCust_CountryField.getText().isBlank())
        {
            country = AdminUI_AddCust_CountryField.getText();
        }
        else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!AdminUI_AddCust_CityField.getText().isBlank())
        {
            city = AdminUI_AddCust_CityField.getText();
        }
        else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!AdminUI_AddCust_DistrictField.getText().isBlank())
        {
            district = AdminUI_AddCust_DistrictField.getText();
        }
        else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!AdminUI_AddCust_AddressField.getText().isBlank())
        {
            address = AdminUI_AddCust_AddressField.getText();
        }
        else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!AdminUI_AddCust_PostCodeField.getText().isBlank())
        {
            pscode = AdminUI_AddCust_PostCodeField.getText();
        }
        else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }

        if (!AdminUI_AddCust_PhoneField.getText().isBlank())
        {
            phone = AdminUI_AddCust_PhoneField.getText();
        }
        else {
            if (!AdminUI_AddCust_ErrorReportLabel1.getText().isEmpty()) return;
            AdminUI_AddCust_ErrorReportLabel1.setText("Missing Values. Please fill all the fields with the * symbol.");
            visiblePause.play();
            return;
        }
     
        try {
            App.connectDB.setAutoCommit(false);
            //STEP 3: create address
            //check if country exists in DB
            PreparedStatement pstm;
            
            String countryFound = "select country_id, count(country_id) from country where country=\""+ country+"\"";
            ResultSet rscountryFound = App.connectDB.createStatement().executeQuery(countryFound);
            
            if ( rscountryFound.next() && rscountryFound.getInt(2) >= 1){
                //new country exists in the database
                country_id = rscountryFound.getInt(1);

                //check if the newCity exists in the database
                String cityFound = "select city_id, count(city_id) from city natural join country where city=\""+ city +"\" and country=\""+country+"\"";
                ResultSet rscityFound = App.connectDB.createStatement().executeQuery(cityFound);

                if ( rscityFound.next() && rscityFound.getInt(2) >= 1 ){
                    //city was found in the databse
                    city_id = rscityFound.getInt(1);
                }
                else {
                    //city was not found and needs to be created with the newCountryID

                    String createCity = "insert into city values(null,?,?)";
                    pstm = App.connectDB.prepareStatement(createCity);

                    pstm.setString(1,city);
                    pstm.setInt(2,country_id);

                    pstm.executeUpdate();

                    //city created but we dont have the new id ->
                    //String newCityIDQuery = "select city_id from city order by city_id desc limit 1";
                    String newCityIDQuery = "select last_insert_id()";
                    ResultSet rscityID = pstm.executeQuery(newCityIDQuery);

                    if (rscityID.next()) {
                        city_id = rscityID.getInt(1);
                    }
                    rscityID.close();
                }
                rscityFound.close();
            }   
            else {
                //country does not exist in the database so neither will the city 

                String createCountry = "insert into country values(null,?)";
                pstm = App.connectDB.prepareStatement(createCountry);

                pstm.setString(1, country );
                pstm.executeUpdate();

                String newCountryIDQuery = "select last_insert_id()";
                ResultSet rscountryID = pstm.executeQuery(newCountryIDQuery);

                if ( rscountryID.next() ) {
                    country_id = rscountryID.getInt(1);
                }
                rscountryID.close();

                //lets create the new city 
                String createCity = "insert into city values(null,?,?)";
                pstm = App.connectDB.prepareStatement(createCity);

                pstm.setString(1,city);
                pstm.setInt(2,country_id);

                pstm.executeUpdate();

                String newCityIDQuery = "select last_insert_id()";
                ResultSet rscityID = pstm.executeQuery(newCityIDQuery);

                if (rscityID.next()) {
                    city_id = rscityID.getInt(1);
                }
                rscityID.close();

            }
            rscountryFound.close();

            /* Update USER */
            String insertUser = "insert into user values(";

            if (id == -1) insertUser = insertUser.concat("NULL,");
            else insertUser = insertUser.concat(String.valueOf(id)+",");

            insertUser = insertUser.concat("?,?,?,");

            if (active == 0) insertUser = insertUser.concat("0,NOW(),\"CUSTOMER\",false)");
            else insertUser = insertUser.concat("1,NOW(),\"CUSTOMER\",false)");

            pstm = App.connectDB.prepareStatement(insertUser);
            pstm.setString(1,fname);
            pstm.setString(2,lname);
            pstm.setString(3,email);

            pstm.executeUpdate();

            if (id == -1)
            {
                //But we dont have to new User ID
                String newUserIDQuery = "select last_insert_id()";
                ResultSet rsUserID = pstm.executeQuery(newUserIDQuery);

                if (rsUserID.next()) {
                    id = rsUserID.getInt(1);
                }
                rsUserID.close();
            }

            /* Update  address*/
            String updateAddress = "insert into address values(null,?,?,?,?,?)";
            pstm = App.connectDB.prepareStatement(updateAddress);
            pstm.setString(1,address);
            pstm.setString(2,district);
            pstm.setInt(3, city_id);
            pstm.setString(4,pscode);
            pstm.setString(5,phone);

            pstm.executeUpdate();

            //But we dont have to new Address ID
            String newAddressIDQuery = "select last_insert_id()";
            ResultSet rsAddressID = pstm.executeQuery(newAddressIDQuery);

            if (rsAddressID.next()) {
                address_id = rsAddressID.getInt(1);
            }
            rsAddressID.close();

            /* Update Customer */
            String insertCustomer = "insert into customer values(?,?,?)";
            pstm = App.connectDB.prepareStatement(insertCustomer);
            pstm.setInt(1,id);
            pstm.setString(2,type);
            pstm.setInt(3, address_id);

            pstm.executeUpdate();

            pstm.close();

            App.connectDB.commit();
            AdminUI_AddCust_ErrorReportLabel2.setText("Customer Registered!");
            visiblePause2.play();
        }catch (Exception e){
            //cancel all changes
            try {
                App.connectDB.rollback();
            }catch (Exception s){
                s.printStackTrace();
            }

            //print message
            AdminUI_AddCust_ErrorReportLabel2.setText("Error Occured! Customer not registered.");
            visiblePause2.play();

            //log update manual (no matter thw query that failed, the whole transaction is considered failed)
            String logupdate1 = "inset into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"user\");";
            String logupdate2 = "inset into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"customer\");";
            String logupdate3 = "inset into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"address\");";

            //any exceptions caused here will be thrown
            DBUtil.DBexecuteQuery(App.connectDB, logupdate1);
            DBUtil.DBexecuteQuery(App.connectDB, logupdate2);
            DBUtil.DBexecuteQuery(App.connectDB, logupdate3);

        }
        
        //everything went according to plan
        App.connectDB.setAutoCommit(true);
        Stage currentstage = (Stage) AdminUI_AddCust_ErrorReportLabel1.getScene().getWindow();
        currentstage.close();
        
    }

    @FXML
    void cancel(ActionEvent event) {
    Stage currentstage = (Stage) AdminUI_AddCust_ErrorReportLabel1.getScene().getWindow();
        AdminUI_AddCust_ErrorReportLabel1.setText("Action cancelled!");
        PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(3)
        );
        visiblePause.setOnFinished(
            some_event -> currentstage.close()
        );
        visiblePause.play();

    }

    
    @FXML
    void hideTypeLabel()
    {
        AdminUI_AddCust_TypeLabel.setVisible(false);
    }


}
