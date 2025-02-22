package com.projectdb;

//Java Imports
import java.io.IOException;

//JavaFx Basics
import javafx.stage.Stage;
import javafx.fxml.FXML;

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

public class CustomerEditCustomerController  {
        
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
    private Label LeftLabel;
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
    private Label LeftLabel8;
    @FXML
    private Label LeftLabel9;
    @FXML
    private Label LeftLabel10;
    @FXML
    private Label LeftLabel11;
    @FXML
    private Label LeftLabel12;
    
    
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
    private TextField RightTextField8;
    @FXML
    private TextField RightTextField9;
    @FXML
    private TextField RightTextField10;
    @FXML
    private TextField RightTextField11;
    @FXML
    private TextField RightTextField12;

    @FXML
    private ChoiceBox<String> CustomerTypeBox;

    @FXML
    private Label RightLabel;
    private ResultSet Data;
    private int current_id;

    /*  Methods */
    @FXML
    private void initialize() throws IOException{
        
        //get the user_id of current user.
        try {
            ResultSet user_idQuery = App.connectDB.createStatement().executeQuery( "select user_id from user where email = \"" + App.email + "\"");
            if ( user_idQuery.next() ) current_id = user_idQuery.getInt(1);
            user_idQuery.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        IDLabel.setText( String.valueOf(current_id));
        CustomerTypeBox.setValue("");
        CustomerTypeBox.getItems().addAll("FILMS ONLY", "SERIES ONLY", "FILMS AND SERIES");
        initializeLeftLabels();
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
    
        String value2 = returnValue( RightTextField2 , LeftLabel2);
        //String value3 = returnValue( RightTextField3 , LeftLabel3);
        
        String value3;
        if ( CustomerTypeBox.getValue().isBlank() ){
            value3 = LeftLabel3.getText();
        }
        else
            value3 = CustomerTypeBox.getValue();

        String value4 = returnValue( RightTextField4 , LeftLabel4);
        String value5 = returnValue( RightTextField5 , LeftLabel5);
        String value6 = returnValue( RightTextField6 , LeftLabel6);
        String value7 = "1";
        String value8 = returnValue( RightTextField8 , LeftLabel8);
        String value9 = returnValue( RightTextField9 , LeftLabel9);
        String value10 = returnValue( RightTextField10 , LeftLabel10);
        String value11 = returnValue( RightTextField11 , LeftLabel11);
        String value12 = returnValue( RightTextField12 , LeftLabel12);

        try {

            App.connectDB.setAutoCommit(false);

            //check weather the new record has changed Country and city 
            if ( LeftLabel8.getText().equals(RightTextField8.getText()) || RightTextField8.getText().isBlank() ) 
            {   //The Country is Left unchainged
                if ( LeftLabel9.getText().equals(RightTextField9.getText()) || RightTextField9.getText().isBlank() ) 
                {   //The City is left unchanged - updating only the User, Customer and address is needed.
                    //The previous checks were made in order to escape the need of more database queries - inserts etc.  
                    
                    /* Update USER */
                    String updateUser = "update user set user_id=?,first_name=?,last_name=?,active=?,create_date=? where user_id=?";
                    PreparedStatement pstm = App.connectDB.prepareStatement(updateUser);
                    pstm.setInt(1,current_id);
                    pstm.setString(2,value4);
                    pstm.setString(3,value5);
                    pstm.setInt(4,Integer.parseInt(value7));
                    pstm.setString(5,value6);
                    pstm.setInt(6,current_id);

                    pstm.executeUpdate();

                    /* Update Customer */
                    String updateCustomer = "update customer set cust_type=? where customer_id=?";
                    pstm = App.connectDB.prepareStatement(updateCustomer);
                    pstm.setString(1,value3);
                    pstm.setInt(2,current_id);

                    pstm.executeUpdate();

                    /* Update  address*/
                    Data.first();

                    String updateAddress = "update address set address_name=?,district=?,postal_code=?,phone=? where address_id=?";                    
                    pstm = App.connectDB.prepareStatement(updateAddress);
                    pstm.setString(1,value11);
                    pstm.setString(2,value10);
                    pstm.setString(3,value12);
                    pstm.setString(4,value2);
                    pstm.setInt(5,Data.getInt("address_id") );

                    pstm.executeUpdate();
                    pstm.close();

                    App.connectDB.commit();
                    App.connectDB.setAutoCommit(true);
                }
                else {
                    //city changed but country remains the same
                    //check if city exists in the database:
                    // SQL INJECTION but we dont mind 

                    String cityQuery = "select count(city_id),city_id from city natural join country where city=\""+value9+"\" and country=\""+value8+"\"";
                    ResultSet cityFound = App.connectDB.createStatement().executeQuery(cityQuery);
                    PreparedStatement pstm;
                    int newCityID = -1;

                    if( cityFound.next() && cityFound.getInt(1) >= 1){
                        newCityID = cityFound.getInt(2);
                    }
                    else {
                        //THE TWO FOLOWWING QUERIES must be atomic ... no insert to city must happen between them
                        
                        //city must be added
                        String createCity = "insert into city values(null,?,?)";
                        pstm = App.connectDB.prepareStatement(createCity);

                        pstm.setString(1,value9);
                        pstm.setInt(2,Data.getInt("country_id"));

                        pstm.executeUpdate();
                        //city created but we dont have the new id ->

                        //String newCityIDQuery = "select city_id from city order by city_id desc limit 1";
                        String newCityIDQuery = "select last_insert_id()";
                        ResultSet rscityID = pstm.executeQuery(newCityIDQuery);
                        if (rscityID.next()){
                            newCityID = rscityID.getInt(1);
                        }
                        rscityID.close();

                    }
                    cityFound.close();

                    /* Update USER */
                    String updateUser = "update user set user_id=?,first_name=?,last_name=?,active=?,create_date=? where user_id=?";
                    pstm = App.connectDB.prepareStatement(updateUser);
                    pstm.setInt(1,current_id);
                    pstm.setString(2,value4);
                    pstm.setString(3,value5);
                    pstm.setInt(4,Integer.parseInt(value7));
                    pstm.setString(5,value6);
                    pstm.setInt(6,current_id);
                    
                    pstm.executeUpdate();

                    /* Update Customer */
                    String updateCustomer = "update customer set cust_type=? where customer_id=?";
                    pstm = App.connectDB.prepareStatement(updateCustomer);
                    pstm.setString(1,value3);
                    pstm.setInt(2,current_id);

                    pstm.executeUpdate();
                        
                    /* Update  address*/
                    Data.first();

                    String updateAddress = "update address set address_name=?,district=?,postal_code=?,phone=?,city_id=? where address_id=?";                    
                    pstm = App.connectDB.prepareStatement(updateAddress);
                    pstm.setString(1,value11);
                    pstm.setString(2,value10);
                    pstm.setString(3,value12);
                    pstm.setString(4,value2);
                    pstm.setInt(5,newCityID);
                    pstm.setInt(6,Data.getInt("address_id") );

                    pstm.executeUpdate();
                    pstm.close();

                    if ( newCityID == -1 ) {
                        throw new ArithmeticException("Error newCityID = -1.");
                    }
                    App.connectDB.commit();
                    App.connectDB.setAutoCommit(true);
                }

            }
            else {
                //Country changed and so did the city.

                int newCityID = -1;
                int newCountryID = -1;
                PreparedStatement pstm;

                //check if the new country already exists
                String countryFound = "select country_id, count(country_id) from country where country=\""+value8+"\"";
                ResultSet rscountryFound = App.connectDB.createStatement().executeQuery(countryFound);
                
                if ( rscountryFound.next() && rscountryFound.getInt(2) >= 1){
                    //new country exists in the database
                    newCountryID = rscountryFound.getInt(1);

                    //check if the newCity exists in the database
                    String cityFound = "select city_id, count(city_id) from city natural join country where city=\""+value9+"\" and country=\""+value8+"\"";
                    ResultSet rscityFound = App.connectDB.createStatement().executeQuery(cityFound);

                    if ( rscityFound.next() && rscityFound.getInt(2) >= 1 ){
                        //city was found in the databse
                        newCityID = rscityFound.getInt(1);
                    }
                    else {
                        //city was not found and needs to be created with the newCountryID

                        String createCity = "insert into city values(null,?,?)";
                        pstm = App.connectDB.prepareStatement(createCity);

                        pstm.setString(1,value9);
                        pstm.setInt(2,newCountryID);

                        pstm.executeUpdate();

                        //city created but we dont have the new id ->
                        //String newCityIDQuery = "select city_id from city order by city_id desc limit 1";
                        String newCityIDQuery = "select last_insert_id()";
                        ResultSet rscityID = pstm.executeQuery(newCityIDQuery);
                        
                        if (rscityID.next()) {
                            newCityID = rscityID.getInt(1);
                        }
                        rscityID.close();
                        
                    }
                    rscityFound.close();
                }
                else {
                    //country does not exist in the database so neither will the city 

                    String createCountry = "insert into country values(null,?)";
                    pstm = App.connectDB.prepareStatement(createCountry);

                    pstm.setString(1, value8);
                    pstm.executeUpdate();

                    //get the new country_id
                    //String newCountryIDQuery = "select country_id from country order by country_id desc limit 1";
                    String newCountryIDQuery = "select last_insert_id()";
                    ResultSet rscountryID = pstm.executeQuery(newCountryIDQuery);
                    
                    if ( rscountryID.next() ) {
                        newCountryID = rscountryID.getInt(1);
                    }
                    rscountryID.close();

                    //lets create the new city 
                    String createCity = "insert into city values(null,?,?)";
                    pstm = App.connectDB.prepareStatement(createCity);

                    pstm.setString(1,value9);
                    pstm.setInt(2,newCountryID);

                    pstm.executeUpdate();

                    //city created but we dont have the new id ->
                    //String newCityIDQuery = "select city_id from city order by city_id desc limit 1";
                    String newCityIDQuery = "select last_insert_id()";
                    ResultSet rscityID = pstm.executeQuery(newCityIDQuery);
                        
                    if (rscityID.next()) {
                        newCityID = rscityID.getInt(1);
                    }
                    rscityID.close();
                    
                }
                rscountryFound.close();

                /* Update USER */
                String updateUser = "update user set user_id=?,first_name=?,last_name=?,active=?,create_date=? where user_id=?";
                pstm = App.connectDB.prepareStatement(updateUser);
                pstm.setInt(1,current_id);
                pstm.setString(2,value4);
                pstm.setString(3,value5);
                pstm.setInt(4,Integer.parseInt(value7));
                pstm.setString(5,value6);
                pstm.setInt(6,current_id);
        
                pstm.executeUpdate();

                /* Update Customer */
                String updateCustomer = "update customer set cust_type=? where customer_id=?";
                pstm = App.connectDB.prepareStatement(updateCustomer);
                pstm.setString(1,value3);
                pstm.setInt(2,current_id);

                pstm.executeUpdate();
                String logupdate2 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"UPDATE\", 1, \"customer\");";
                pstm = App.connectDB.prepareStatement(logupdate2);
                pstm.executeUpdate();
                /* Update  address*/
                Data.first();

                String updateAddress = "update address set address_name=?,district=?,postal_code=?,phone=?,city_id=? where address_id=?";                    
                pstm = App.connectDB.prepareStatement(updateAddress);
                pstm.setString(1,value11);
                pstm.setString(2,value10);
                pstm.setString(3,value12);
                pstm.setString(4,value2);
                pstm.setInt(5,newCityID);
                pstm.setInt(6,Data.getInt("address_id") );
                
                pstm.executeUpdate();

                pstm.close();
                

                if ( newCityID == -1 || newCountryID == -1){
                    throw new ArithmeticException(" Error newCityID or newCountryID = -1");
                }
                App.connectDB.commit();
                
                
                App.connectDB.setAutoCommit(true);
            }
            thirdStageClose();
        }
        catch (Exception s) {
            
            try {
                App.connectDB.rollback();    
            } catch (Exception a) {
                a.printStackTrace();
            }
            ErrorLabel.setText("Database Error - Query Declined");
            s.printStackTrace();

            try {
                App.connectDB.createStatement().executeQuery( "insert into log values(null,\""+App.email+"\",NOW(), \"UPDATE\", 0, \"user\")");
                App.connectDB.createStatement().executeQuery( "insert into log values(null,\""+App.email+"\",NOW(), \"UPDATE\", 0, \"customer\")");
                App.connectDB.createStatement().executeQuery( "insert into log values(null,\""+App.email+"\",NOW(), \"UPDATE\", 0, \"address\")");
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    private void initializeLeftLabels(){

        String query = "select * from customer inner join user on user_id=customer_id natural join address natural join city natural join country where customer.customer_id = \"" + String.valueOf(current_id) + "\";";
        /* Return order: | country_id | city_id | address_id | customer_id | cust_type | user_id | first_name | last_name | email | active | create_date | usertype | pending | address_name | district | postal_code | phone | city | country | */

        try {
            Statement statement = App.connectDB.createStatement();
            Data = statement.executeQuery(query);
        
            if (Data.next()){
                
                LeftLabel.setText(Data.getString("email"));
                LeftLabel2.setText( String.valueOf( Data.getString("phone")) );
                LeftLabel3.setText( String.valueOf( Data.getString("cust_type")) );
                LeftLabel4.setText( String.valueOf( Data.getString("first_name")) );
                LeftLabel5.setText( String.valueOf( Data.getString("last_name")) );
                LeftLabel6.setText( String.valueOf( Data.getString("create_date")) );
                LeftLabel8.setText( String.valueOf( Data.getString("country")) );
                LeftLabel9.setText( String.valueOf( Data.getString("city")) );
                LeftLabel10.setText( String.valueOf( Data.getString("district")) );
                LeftLabel11.setText( String.valueOf( Data.getString("address_name")) );
                LeftLabel12.setText( String.valueOf( Data.getString("postal_code")) );
                
            }
            //Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static String returnValue( TextField newValue , Label curValue ){
        String value = "";
        
        if ( newValue.getText().isBlank() ){
            value = curValue.getText();
        }
        else value = newValue.getText();
        return value;
    }

}
