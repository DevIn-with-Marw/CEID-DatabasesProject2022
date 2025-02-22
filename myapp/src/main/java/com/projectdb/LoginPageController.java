package com.projectdb;

//Java Imports
import java.io.IOException;

//JavaFx basics
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ChoiceBox;
import java.sql.PreparedStatement;

//javaFX Animation
import javafx.util.Duration;
import javafx.animation.PauseTransition;

//JDBC - Mariadb Imports
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPageController {

    //JavaFX modules
    @FXML
    private Button loginButtonExit;
    @FXML
    private TextField loginTextFieldEmail;
    @FXML
    private Button loginButtonLogin;
    @FXML
    private Label loginLabelMessage;
    @FXML
    private Label loginInfoLabel;
    @FXML
    private Button signButton;

    @FXML 
    private Button SubmitButton;
    @FXML 
    private Button CancelButton;

    @FXML
    private Label ErrorLabel;

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
    private TextField RightTextField8;
    @FXML
    private TextField RightTextField9;
    @FXML
    private TextField RightTextField10;
    @FXML
    private TextField RightTextField11;
    @FXML
    private AnchorPane Primary;
    @FXML
    private AnchorPane Secondary;
    
    @FXML
    private ChoiceBox<String> CustomerTypeBox;

    public static float filmsonly = 0;
    public static float seriesonly = 0;
    public static float filmsboth = 0;
    public static float seriesboth = 0;

    //EventHandlers And Methods
    @FXML
    private void initialize() throws IOException{
        CustomerTypeBox.setValue("");
        CustomerTypeBox.getItems().addAll("FILMS ONLY", "SERIES ONLY", "FILMS AND SERIES");
    }

    private void primaryStageClose(){
        Stage currentStage = (Stage) loginButtonExit.getScene().getWindow();
        currentStage.close();
    }
   
    @FXML
    private void loginButtonExitOnAction(ActionEvent e) {
        primaryStageClose();
    }

    @FXML
    private void signButtonOnAction() throws IOException {
        Primary.setVisible(false);
        Secondary.setVisible(true);
    }

    @FXML
    private void CancelButtonOnAction() throws IOException {
        Secondary.setVisible(false);
        Primary.setVisible(true);
    }

    @FXML
    private void loginButtonLoginOnAction(ActionEvent e) {
        if ( loginTextFieldEmail.getText().isBlank() == false ) {
            loginLabelMessage.setText("Trying to login!");
            validateLogin();
        }
        else {
            loginLabelMessage.setText("Please enter your email.");
            EmptyLabelTransition(loginLabelMessage);
        }
    }
    
    private void switchToEmployeeHomePage() throws IOException{
        App.scene = new Scene(App.loadFXML("employeeHomePage"), 1360, 760);
        Stage secondaryStage = new Stage(StageStyle.UNDECORATED);
        secondaryStage.setScene(App.scene);
        secondaryStage.show();
        primaryStageClose();
    }

    private void switchToCustomerHomePage() throws IOException{
        App.scene = new Scene(App.loadFXML("CustomerHome"), 1280, 760);
        Stage secondaryStage = new Stage(StageStyle.UNDECORATED);
        secondaryStage.setScene(App.scene);
        secondaryStage.show();
        primaryStageClose();
    }

    private void switchToAdminHomePage() throws IOException{
        App.scene = new Scene(App.loadFXML("AdminHome"), 1280, 760);
        Stage secondaryStage = new Stage(StageStyle.UNDECORATED);
        secondaryStage.setScene(App.scene);
        secondaryStage.show();
        primaryStageClose();
    }

    private void validateLogin() {
        // create a new instance of our databaseConnection class and use the get connection method for it.
        //App.connectDB is the database link of the current connection
        String validateQuery = "select count(email),usertype,user_id,pending,active from user where email = \"" + loginTextFieldEmail.getText() + "\"";

        //check weather the email given exists in our database
        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet validateQueryResultSet = statement.executeQuery(validateQuery);
    
            while(validateQueryResultSet.next()) {
                if(validateQueryResultSet.getInt(1) == 1 && validateQueryResultSet.getInt(4) == 0 && validateQueryResultSet.getInt("active") == 1 ){
                    
                    //set @user_now database variable - Not Scalable
                    statement = App.connectDB.createStatement();
                    statement.executeUpdate("set @user_now =\""+String.valueOf(validateQueryResultSet.getInt(3))+"\"");
                    
                    //initialize all or just the right ones (base on @user_now's customer type) after log in
                    if (filmsonly == 0) {
                        statement.executeUpdate("SET @films_only=0.4");
                        filmsonly = (float) 0.4;
                    }
                        
                    if (seriesonly == 0) {
                        statement.executeUpdate("SET @series_only = 0.2;");
                        seriesonly = (float) 0.2;
                    }
               
                    if (filmsboth==0) {
                        statement.executeUpdate("SET @films_series = 0.3;");
                        filmsboth = (float) 0.3;
                    }
                       
                    if (seriesboth==0) {
                        statement.executeUpdate("SET @series_films = 0.1;");
                        seriesboth = (float) 0.1;
                    }
                        

                    statement.close();

                    App.email = loginTextFieldEmail.getText();
                                                       
                    //check the usertype and set the according scene
                    if (validateQueryResultSet.getString(2).equals("CUSTOMER")) {
                        loginLabelMessage.setText("Welcome Customer!");
                        switchToCustomerHomePage();
                    }
                    else if (validateQueryResultSet.getString(2).equals("EMPLOYEE")) {
                        loginLabelMessage.setText("Welcome Employee!");
                        switchToEmployeeHomePage();
                    }
                    else {
                        loginLabelMessage.setText("Welcome Admin!");
                        switchToAdminHomePage();
                    }
                }
                else {
                    if ( validateQueryResultSet.getInt(1) == 1 && validateQueryResultSet.getInt("active") == 0 ) {
                        loginLabelMessage.setText("Your account has been suspended.");
                        EmptyLabelTransition(loginLabelMessage);
                    }
                    else loginLabelMessage.setText("Invalid Email ...");
                    EmptyLabelTransition(loginLabelMessage);
                }
            }
            validateQueryResultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void EmptyLabelTransition ( Label label ){
        PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(3)
        );
        visiblePause.setOnFinished(
                event -> label.setText("")
        );
        visiblePause.play();
    }

    @FXML
    private void SubmitButtonOnACtion(ActionEvent e) {
        
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher( RightTextField1.getText());

        if ( RightTextField1.getText().isBlank() || RightTextField2.getText().isBlank() || RightTextField3.getText().isBlank() || RightTextField4.getText().isBlank() || RightTextField5.getText().isBlank() || RightTextField6.getText().isBlank() || RightTextField7.getText().isBlank() || RightTextField8.getText().isBlank() || RightTextField9.getText().isBlank() || CustomerTypeBox.getValue().isBlank() ){
            ErrorLabel.setText("Please fill all the necessary fields.");
            EmptyLabelTransition(ErrorLabel);
        }
        else if(! mat.matches() ) {
            ErrorLabel.setText("Please insert a valid Email.");
            EmptyLabelTransition(ErrorLabel);
            RightTextField1.setText("");
        }
        else 
        {
            try 
            {
                App.connectDB.createStatement().executeUpdate("set @user_now = 1");
                App.connectDB.setAutoCommit(false);

                int newCityID = -1;
                int newCountryID = -1;
                int newUserID = -1;
                int newAddressID = -1;
                PreparedStatement pstm;

                //check if the new country already exists
                String countryFound = "select country_id, count(country_id) from country where country=\""+ RightTextField5.getText()+"\"";
                ResultSet rscountryFound = App.connectDB.createStatement().executeQuery(countryFound);
                
                if ( rscountryFound.next() && rscountryFound.getInt(2) >= 1){
                    //new country exists in the database
                    newCountryID = rscountryFound.getInt(1);

                    //check if the newCity exists in the database
                    String cityFound = "select city_id, count(city_id) from city natural join country where city=\""+ RightTextField6.getText() +"\" and country=\""+RightTextField5.getText()+"\"";
                    ResultSet rscityFound = App.connectDB.createStatement().executeQuery(cityFound);

                    if ( rscityFound.next() && rscityFound.getInt(2) >= 1 ){
                        //city was found in the databse
                        newCityID = rscityFound.getInt(1);
                    }
                    else {
                        //city was not found and needs to be created with the newCountryID

                        String createCity = "insert into city values(null,?,?)";
                        pstm = App.connectDB.prepareStatement(createCity);

                        pstm.setString(1,RightTextField6.getText());
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

                    pstm.setString(1, RightTextField5.getText() );
                    pstm.executeUpdate();

                    String newCountryIDQuery = "select last_insert_id()";
                    ResultSet rscountryID = pstm.executeQuery(newCountryIDQuery);
                    
                    if ( rscountryID.next() ) {
                        newCountryID = rscountryID.getInt(1);
                    }
                    rscountryID.close();

                    //lets create the new city 
                    String createCity = "insert into city values(null,?,?)";
                    pstm = App.connectDB.prepareStatement(createCity);

                    pstm.setString(1,RightTextField6.getText());
                    pstm.setInt(2,newCountryID);

                    pstm.executeUpdate();

                    String newCityIDQuery = "select last_insert_id()";
                    ResultSet rscityID = pstm.executeQuery(newCityIDQuery);
                        
                    if (rscityID.next()) {
                        newCityID = rscityID.getInt(1);
                    }
                    rscityID.close();
                    
                }
                rscountryFound.close();

                /* Update USER */
                String insertUser = "insert into user values(null,?,?,?,1,NOW(),\"CUSTOMER\",true)";
                pstm = App.connectDB.prepareStatement(insertUser);

                pstm.setString(1,RightTextField2.getText());
                pstm.setString(2,RightTextField3.getText());
                pstm.setString(3,RightTextField1.getText());
                pstm.executeUpdate();

                //But we dont have to new User ID
                String newUserIDQuery = "select last_insert_id()";
                ResultSet rsUserID = pstm.executeQuery(newUserIDQuery);
                        
                if (rsUserID.next()) {
                    newUserID = rsUserID.getInt(1);
                }
                rsUserID.close();

                /* Update  address*/
                String updateAddress = "insert into address values(null,?,?,?,?,?)";
                pstm = App.connectDB.prepareStatement(updateAddress);
                pstm.setString(1,RightTextField8.getText());
                pstm.setString(2,RightTextField7.getText());
                pstm.setInt(3, newCityID);
                pstm.setString(4,RightTextField9.getText());
                pstm.setString(5,RightTextField4.getText());
                
                pstm.executeUpdate();
                
                //But we dont have to new Address ID
                String newAddressIDQuery = "select last_insert_id()";
                ResultSet rsAddressID = pstm.executeQuery(newAddressIDQuery);
                        
                if (rsAddressID.next()) {
                    newAddressID = rsAddressID.getInt(1);
                }
                rsAddressID.close();

                /* Update Customer */
                String insertCustomer = "insert into customer values(?,?,?)";
                pstm = App.connectDB.prepareStatement(insertCustomer);
                pstm.setInt(1,newUserID);
                pstm.setString(2,CustomerTypeBox.getValue());
                pstm.setInt(3, newAddressID);

                pstm.executeUpdate();

                pstm.close();
                if ( newCityID == -1 || newCountryID == -1 || newUserID == -1 || newAddressID == -1){
                    throw new ArithmeticException(" Error: Invalid Arguments");
                }

                App.connectDB.commit();

                //everything went according to plan
                App.connectDB.setAutoCommit(true);
                
                Secondary.setVisible(false);
                Primary.setVisible(true);
                loginInfoLabel.setText("Account Registered - Wait for Admin approval!");
                PauseTransition visiblePause = new PauseTransition(  Duration.seconds(5) );
                visiblePause.setOnFinished( event -> loginInfoLabel.setText( "Please login to continue") );
                visiblePause.play();
                
            }
            catch (Exception s) {
                
                try {
                    App.connectDB.rollback();    
                } catch (Exception a) {
                    a.printStackTrace();
                }
                ErrorLabel.setText("Database Error - Query Declined");
                EmptyLabelTransition(ErrorLabel);
                s.printStackTrace();
            }
        }
    }
}
