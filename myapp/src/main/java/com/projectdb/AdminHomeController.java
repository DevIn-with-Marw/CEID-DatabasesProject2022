package com.projectdb;

import com.projectdb.model.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import com.projectdb.util.DBUtil;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminHomeController {
    public static String email = "";
    public static String startdate = "";
    public static String enddate = "";
    public static String tablename = "";

    //----- GENERAL COMPONENTS -----//
    @FXML
    private AnchorPane AdminUIBase;

    @FXML
    private BorderPane AdminUIBorderPane;

    @FXML
    private AnchorPane AdminUI_welcome_pane;

    @FXML
    private Label AdminUI_hellouser_label;

    @FXML
    private AnchorPane AdminUI_ButtonGroup1_Pane;

    @FXML
    private Button AdminUI_LogoutButton;

    @FXML
    private Button AdminUI_ExitButton;

    @FXML
    private AnchorPane AdminUI_main_Pane;

    @FXML
    private TabPane AdminUI_OuterTabPane;

    @FXML
    private ChoiceBox<String> AdminUI_TableChoice;

    @FXML
    public TextField AdminUI_EmailForLog;

    @FXML
    private TextField AdminUI_StartDate;

    @FXML
    private TextField AdminUI_EndDate;

    @FXML
    private Button AdminUI_showLogInfo;
    
    @FXML 
    private Label AdminUI_LogIndicationLabel;

    //----- ACCOUNT REGISTRY -----//
    @FXML
    private Tab AdminUI_AccountRegistryTab;

    @FXML
    private AnchorPane AdminUI_AccountRegistry_Pane;

    @FXML
    private TabPane AdminUI_AccountRegistry_TabPane;


        //CUSTOMERS//
    @FXML
    private Tab AdminUI_AccountRegistry_CustomersTab;

    @FXML
    private AnchorPane AdminUI_AccountRegistry_CustomersPane;

    @FXML
    private ScrollPane AdminUI_AccountRegistry_CustomersScrollPane;

    @FXML
    private TableView<CustUser> AdminUI_AccountRegistry_CustomersTable;

    @FXML
    private TableColumn<CustUser, Integer> AdminUI_AccountRegistry_Customers_ActiveCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Customers_AddressCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Customers_EmailCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Customers_FnameCol;

    @FXML
    private TableColumn<CustUser, Integer> AdminUI_AccountRegistry_Customers_IdCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Customers_TypeCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Customers_LnameCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Customers_RegDateCol;

    @FXML
    private ToolBar AdminUI_AccountRegistry_Customers_Toolbar;

    @FXML
    private Button AdminUI_AccountRegistry_Customers_RemoveCustomerButton;

    @FXML
    private Button AdminUI_AccountRegistry_Customers_AddCustomerButton;

        //EMPLOYEES//   
    @FXML
    private Tab AdminUI_AccountRegistry_Employees_Tab;

    @FXML
    private AnchorPane AdminUI_AccountRegistry_EmployeesPane;

    @FXML
    private ScrollPane AdminUI_AccountRegistry_EmployeesScrollPane;

    @FXML
    private TableView<EmpUser> AdminUI_AccountRegistry_EmployeesTable;

    @FXML
    private TableColumn<EmpUser, Integer> AdminUI_AccountRegistry_Employees_IdCol;

    @FXML
    private TableColumn<EmpUser, String> AdminUI_AccountRegistry_Employees_FnameCol;
    
    @FXML
    private TableColumn<EmpUser, String> AdminUI_AccountRegistry_Employees_LnameCol;

    @FXML
    private TableColumn<EmpUser, String> AdminUI_AccountRegistry_Employees_EmailCol;

    @FXML
    private TableColumn<EmpUser, String> AdminUI_AccountRegistry_Employees_RegDateCol;

    @FXML
    private TableColumn<EmpUser, Integer> AdminUI_AccountRegistry_Employees_ActiveCol;

    @FXML
    private TableColumn<EmpUser, String> AdminUI_AccountRegistry_Employees_PhoneCol;

    @FXML
    private ToolBar AdminUI_AccountRegistry_Employees_Toolbar;

    @FXML
    private Button AdminUI_AccountRegistry_Employees_RemoveEmployeeButton;

    @FXML
    private Button AdminUI_AccountRegistry_Employees_AddEmployeeButton;

    @FXML
    private Button AdminUI_AccountRegistry_Employees_MakeAdminButton;

        //ADMINS//
    @FXML
    private Tab AdminUI_AccountRegistry_Admins_Tab;

    @FXML
    private AnchorPane AdminUI_AccountRegistry_AdminsPane;

    @FXML
    private ScrollPane AdminUI_AccountRegistry_AdminsScrollPane;

    @FXML
    private TableView<AdminUser> AdminUI_AccountRegistry_AdminsTable;

    @FXML
    private TableColumn<AdminUser, Integer> AdminUI_AccountRegistry_Admins_IdCol;

    @FXML
    private TableColumn<AdminUser, String> AdminUI_AccountRegistry_Admins_FnameCol;
    
    @FXML
    private TableColumn<AdminUser, String> AdminUI_AccountRegistry_Admins_LnameCol;

    @FXML
    private TableColumn<AdminUser, String> AdminUI_AccountRegistry_Admins_EmailCol;

    @FXML
    private TableColumn<AdminUser, String> AdminUI_AccountRegistry_Admins_RegDateCol;

    @FXML
    private TableColumn<AdminUser, Integer> AdminUI_AccountRegistry_Admins_ActiveCol;

    @FXML
    private ToolBar AdminUI_AccountRegistry_Admins_Toolbar;

    @FXML
    private Button AdminUI_AccountRegistry_Admins_RemoveAdminButton;

    @FXML
    private Button AdminUI_AccountRegistry_Admins_MakeEmployeeButton;

        //PENDING REQUESTS
    @FXML
    private Tab AdminUI_AccountRegistry_RequestsTab;

    @FXML
    private AnchorPane AdminUI_AccountRegistry_RequestsPane;

    @FXML
    private ScrollPane AdminUI_AccountRegistry_RequestsScrollPane;

    @FXML
    private TableView<CustUser> AdminUI_AccountRegistry_RequestsTable;

    @FXML
    private TableColumn<CustUser, Integer> AdminUI_AccountRegistry_Requests_ActiveCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Requests_AddressCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Requests_EmailCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Requests_FnameCol;

    @FXML
    private TableColumn<CustUser, Integer> AdminUI_AccountRegistry_Requests_IdCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Requests_TypeCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Requests_LnameCol;

    @FXML
    private TableColumn<CustUser, String> AdminUI_AccountRegistry_Requests_RegDateCol;

    @FXML
    private ToolBar AdminUI_AccountRegistry_Requests_Toolbar;

    @FXML
    private Button AdminUI_AccountRegistry_Requests_AcceptButton;

    @FXML
    private Button AdminUI_AccountRegistry_Requests_DeclineButton;

    
    @FXML
    private Label AdminUI_AccountRegistry_ErrorIndicationLabel;
 
    //----- INCOME HISTORY -----//
    @FXML
    private Tab AdminUI_IncomeHistoryTab;

    @FXML
    private AnchorPane AdminUI_IncomeHistoryPane;

    @FXML
    private TableView<TotalPayment> AdminUI_IncomeHistoryTable;

    @FXML
    private TableColumn<TotalPayment, String> AdminUI_IncomeHistoryTable_MonthCol;

    @FXML
    private TableColumn<TotalPayment, Float> AdminUI_IncomeHistoryTable_TotalFilmsIncomeCol;

    @FXML
    private TableColumn<TotalPayment, Float> AdminUI_IncomeHistoryTable_TotalEpisodesIncomeCol;

    //----- PRICING LIST -----//
    @FXML
    private Tab AdminUI_PricingListTab;

    @FXML 
    private AnchorPane AdminUI_PricingListPane;

    @FXML
    private Label AdminUI_PricingList_IntroLabel;

    @FXML
    private Label AdminUI_PricingList_FilmsOnlyLabel;

    @FXML
    private Label AdminUI_PricingList_SeriesOnlyLabel;

    @FXML
    private Label AdminUI_PricingList_FilmsSeriesLabel;

    @FXML
    private Label AdminUI_PricingList_SeriesFilmsLabel;

    @FXML
    private Label AdminUI_PricingList_ErrorIndicationLabel;

    @FXML
    private TextField AdminUI_PricingList_FilmsOnlyPrice;

    @FXML
    private TextField AdminUI_PricingList_SeriesOnlyPrice;

    @FXML
    private TextField AdminUI_PricingList_FilmsSeriesPrice;

    @FXML
    private TextField AdminUI_PricingList_SeriesFilmsPrice;


    @FXML 
    private void initialize() throws Exception
    {
        AdminUI_hellouser_label.setText("Hello "+App.email+"!");

        initializeARCustomers();
        initializeAREmployees();
        initializeARAdmins();
        initializeARRequests();
        initializeIncomeHistory();
        initializePricingList();

        AdminUI_LogIndicationLabel.setText("Search in Log based on:");
        AdminUI_TableChoice.setValue("All Tables");
        AdminUI_TableChoice.getItems().addAll("actor", "address", "admin", "category", "city", "country", "customer", "employee", "episode", "film", "film_actor", "film_category", "lang", "payment", "rental", "season", "season_actor", "series", "series_category", "user", "All Tables");

    }

    private void initializeARCustomers() throws Exception
    {
        AdminUI_AccountRegistry_Customers_IdCol.setCellValueFactory(cellData -> cellData.getValue().getCUid_Property().asObject());
        AdminUI_AccountRegistry_Customers_FnameCol.setCellValueFactory(cellData -> cellData.getValue().getCUfname_Property());
        AdminUI_AccountRegistry_Customers_LnameCol.setCellValueFactory(cellData -> cellData.getValue().getCUlname_Property());
        AdminUI_AccountRegistry_Customers_TypeCol.setCellValueFactory(cellData -> cellData.getValue().getCUtype_Property());
        AdminUI_AccountRegistry_Customers_EmailCol.setCellValueFactory(cellData -> cellData.getValue().getCUemail_Property());
        AdminUI_AccountRegistry_Customers_RegDateCol.setCellValueFactory(cellData -> cellData.getValue().getCUregdate_Property());
        AdminUI_AccountRegistry_Customers_ActiveCol.setCellValueFactory(cellData -> cellData.getValue().getCUactive_Property().asObject());
        AdminUI_AccountRegistry_Customers_AddressCol.setCellValueFactory(cellData -> cellData.getValue().getCustUserAddress().getAddname_Property());
        
        String query = "select * from user join customer on customer_id = user_id inner join address on customer.address_id = address.address_id where pending=0;";
        ObservableList<CustUser> cuList = CustUserDAO.getAllRecords(query);
        AdminUI_AccountRegistry_CustomersTable.setItems(cuList);
    }
    
    private void initializeAREmployees() throws Exception
    {
        AdminUI_AccountRegistry_Employees_IdCol.setCellValueFactory(cellData -> cellData.getValue().getEUid_Property().asObject());
        AdminUI_AccountRegistry_Employees_FnameCol.setCellValueFactory(cellData -> cellData.getValue().getEUfname_Property());
        AdminUI_AccountRegistry_Employees_LnameCol.setCellValueFactory(cellData -> cellData.getValue().getEUlname_Property());
        AdminUI_AccountRegistry_Employees_EmailCol.setCellValueFactory(cellData -> cellData.getValue().getEUemail_Property());
        AdminUI_AccountRegistry_Employees_RegDateCol.setCellValueFactory(cellData -> cellData.getValue().getEUregdate_Property());
        AdminUI_AccountRegistry_Employees_ActiveCol.setCellValueFactory(cellData -> cellData.getValue().getEUactive_Property().asObject());
        AdminUI_AccountRegistry_Employees_PhoneCol.setCellValueFactory(cellData -> cellData.getValue().getEUphone_Property());

        String query = "select * from user inner join employee on employee_id = user_id;";
        ObservableList<EmpUser> eulist = EmpUserDAO.getAllRecords(query);

        AdminUI_AccountRegistry_EmployeesTable.setItems(eulist);
    }
    
    private void initializeARAdmins() throws Exception
    {
        AdminUI_AccountRegistry_Admins_IdCol.setCellValueFactory(cellData -> cellData.getValue().getAUid_Property().asObject());
        AdminUI_AccountRegistry_Admins_FnameCol.setCellValueFactory(cellData -> cellData.getValue().getAUfname_Property());
        AdminUI_AccountRegistry_Admins_LnameCol.setCellValueFactory(cellData -> cellData.getValue().getAUlname_Property());
        AdminUI_AccountRegistry_Admins_EmailCol.setCellValueFactory(cellData -> cellData.getValue().getAUemail_Property());
        AdminUI_AccountRegistry_Admins_RegDateCol.setCellValueFactory(cellData -> cellData.getValue().getAUregdate_Property());
        AdminUI_AccountRegistry_Admins_ActiveCol.setCellValueFactory(cellData -> cellData.getValue().getAUactive_Property().asObject());

        String query = "select * from user inner join admin on admin_id=user_id;";
        ObservableList<AdminUser> aulist = AdminUserDAO.getAllRecords(query);

        AdminUI_AccountRegistry_AdminsTable.setItems(aulist);
    }

    private void initializeARRequests() throws Exception
    {
        AdminUI_AccountRegistry_Requests_IdCol.setCellValueFactory(cellData -> cellData.getValue().getCUid_Property().asObject());
        AdminUI_AccountRegistry_Requests_FnameCol.setCellValueFactory(cellData -> cellData.getValue().getCUfname_Property());
        AdminUI_AccountRegistry_Requests_LnameCol.setCellValueFactory(cellData -> cellData.getValue().getCUlname_Property());
        AdminUI_AccountRegistry_Requests_TypeCol.setCellValueFactory(cellData -> cellData.getValue().getCUtype_Property());
        AdminUI_AccountRegistry_Requests_EmailCol.setCellValueFactory(cellData -> cellData.getValue().getCUemail_Property());
        AdminUI_AccountRegistry_Requests_RegDateCol.setCellValueFactory(cellData -> cellData.getValue().getCUregdate_Property());
        AdminUI_AccountRegistry_Requests_ActiveCol.setCellValueFactory(cellData -> cellData.getValue().getCUactive_Property().asObject());
        AdminUI_AccountRegistry_Requests_AddressCol.setCellValueFactory(cellData -> cellData.getValue().getCustUserAddress().getAddname_Property());
        
        String query = "select * from user join customer on customer_id = user_id inner join address on customer.address_id = address.address_id where pending=1;";
        ObservableList<CustUser> cuList = CustUserDAO.getAllRecords(query);
        AdminUI_AccountRegistry_RequestsTable.setItems(cuList);
    }
    
    private void initializeIncomeHistory() throws Exception
    {
        DBUtil.DBexecuteQuery(App.connectDB, "call zhtoumeno3_3;");
        ResultSet rs_film = DBUtil.DBexecute(App.connectDB, "select * from z3_3films");
        ResultSet rs_series = DBUtil.DBexecute(App.connectDB, "select * from z3_3series");

        Boolean cont = rs_film.next() && rs_series.next();
        
        while (cont)
        {
            TotalPayment tempTP = new TotalPayment();
            int monthfromfilm = rs_film.getInt("dates");
            int monthfromseries = rs_series.getInt("dates");

            if (monthfromfilm < monthfromseries)
            {
                String month = String.valueOf(monthfromfilm);
                String monthtoprint = month.substring(0, 4)+"-"+month.substring(4);
                
                tempTP.setTotalPayments_month(monthtoprint);
                tempTP.setTotalPayments_filmpayments(rs_film.getFloat("film_payments"));
                tempTP.setTotalPayments_episodepayments((float) 0);
                
                rs_film.next();
            }
            else if (monthfromfilm > monthfromseries)
            {
                String month = String.valueOf(monthfromseries);
                String monthtoprint = month.substring(0, 4)+"-"+month.substring(4);
                
                tempTP.setTotalPayments_month(monthtoprint);
                tempTP.setTotalPayments_filmpayments((float) 0);
                tempTP.setTotalPayments_episodepayments(rs_series.getFloat("episode_payments"));
                
                rs_series.next();
            }
            else 
            {
                String month = String.valueOf(monthfromfilm);
                String monthtoprint = month.substring(0, 4)+"-"+month.substring(4);
               
                tempTP.setTotalPayments_month(monthtoprint);
                tempTP.setTotalPayments_filmpayments(rs_film.getFloat("film_payments"));
                tempTP.setTotalPayments_episodepayments(rs_series.getFloat("episode_payments"));
               
                rs_film.next(); rs_series.next();
            }
            
            AdminUI_IncomeHistoryTable_MonthCol.setCellValueFactory(cellData -> cellData.getValue().getTPmonth_Property());
            AdminUI_IncomeHistoryTable_TotalFilmsIncomeCol.setCellValueFactory(cellData -> cellData.getValue().getTPfilmpayment_Property().asObject());
            AdminUI_IncomeHistoryTable_TotalEpisodesIncomeCol.setCellValueFactory(cellData -> cellData.getValue().getTPepisodepayment_Property().asObject());

            AdminUI_IncomeHistoryTable.getItems().add(tempTP);

            if (rs_series.isAfterLast() || rs_film.isAfterLast()) cont=false;

            tempTP = null;
        }

        //bring the values of the table that still has rows
        if (!rs_film.isAfterLast())
        {
            do {
                int monthfromfilm = rs_film.getInt("dates");
                String month = String.valueOf(monthfromfilm);
                String monthtoprint = month.substring(0, 4)+"-"+month.substring(4);
                
                TotalPayment tempTP = new TotalPayment();
                tempTP.setTotalPayments_month(monthtoprint);
                tempTP.setTotalPayments_filmpayments(rs_film.getFloat("film_payments"));
                tempTP.setTotalPayments_episodepayments((float) 0);

                AdminUI_IncomeHistoryTable_MonthCol.setCellValueFactory(cellData -> cellData.getValue().getTPmonth_Property());
                AdminUI_IncomeHistoryTable_TotalFilmsIncomeCol.setCellValueFactory(cellData -> cellData.getValue().getTPfilmpayment_Property().asObject());
                AdminUI_IncomeHistoryTable_TotalEpisodesIncomeCol.setCellValueFactory(cellData -> cellData.getValue().getTPepisodepayment_Property().asObject());

                AdminUI_IncomeHistoryTable.getItems().add(tempTP);

                tempTP = null;

            }while (rs_film.next());
        }
        else if (!rs_series.isAfterLast())
        {
            do {
                int monthfromseries = rs_series.getInt("dates");
                String month = String.valueOf(monthfromseries);
                String monthtoprint = month.substring(0, 4)+"-"+month.substring(4);
                
                TotalPayment tempTP = new TotalPayment();
                tempTP.setTotalPayments_month(monthtoprint);
                tempTP.setTotalPayments_filmpayments((float) 0);
                tempTP.setTotalPayments_episodepayments(rs_series.getFloat("episode_payments"));

                AdminUI_IncomeHistoryTable_MonthCol.setCellValueFactory(cellData -> cellData.getValue().getTPmonth_Property());
                AdminUI_IncomeHistoryTable_TotalFilmsIncomeCol.setCellValueFactory(cellData -> cellData.getValue().getTPfilmpayment_Property().asObject());
                AdminUI_IncomeHistoryTable_TotalEpisodesIncomeCol.setCellValueFactory(cellData -> cellData.getValue().getTPepisodepayment_Property().asObject());

                AdminUI_IncomeHistoryTable.getItems().add(tempTP);
               
            }while(rs_series.next());
        }

        rs_film.close();
        rs_series.close();
    }

    private void initializePricingList() throws Exception
    {
        AdminUI_PricingList_FilmsOnlyPrice.setText(String.valueOf(LoginPageController.filmsonly));
        AdminUI_PricingList_SeriesOnlyPrice.setText(String.valueOf(LoginPageController.seriesonly));
        AdminUI_PricingList_FilmsSeriesPrice.setText(String.valueOf(LoginPageController.filmsboth));
        AdminUI_PricingList_SeriesFilmsPrice.setText(String.valueOf(LoginPageController.seriesboth));
    }
    
    @FXML
    private void getemailselectionCust(MouseEvent e)
    {
        if ( e.getClickCount() >= 1 && ( AdminUI_AccountRegistry_CustomersTable.getSelectionModel().getSelectedItem() != null ) ){
            CustUser selected = AdminUI_AccountRegistry_CustomersTable.getSelectionModel().getSelectedItem();
            AdminUI_EmailForLog.setText(selected.getCustUserEmail());
        }
    }

    @FXML
    private void getemailselectionEmpl(MouseEvent e)
    {
        if ( e.getClickCount() >= 1 && ( AdminUI_AccountRegistry_EmployeesTable.getSelectionModel().getSelectedItem() != null ) ){
            EmpUser selected = AdminUI_AccountRegistry_EmployeesTable.getSelectionModel().getSelectedItem();
            AdminUI_EmailForLog.setText(selected.getEmpUserEmail());
        }
    }

    @FXML
    private void getemailselectionAdmin(MouseEvent e)
    {
        if ( e.getClickCount() >= 1 && ( AdminUI_AccountRegistry_AdminsTable.getSelectionModel().getSelectedItem() != null ) ){
            AdminUser selected = AdminUI_AccountRegistry_AdminsTable.getSelectionModel().getSelectedItem();
            AdminUI_EmailForLog.setText(selected.getAdminUserEmail());
        }
    }

    @FXML
    private void showLogInfo(ActionEvent event) throws Exception
    {
        email = AdminUI_EmailForLog.getText();
        startdate = AdminUI_StartDate.getText();
        enddate = AdminUI_EndDate.getText();
        tablename = AdminUI_TableChoice.getValue();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(startdate.trim());
                dateFormat.parse(enddate.trim());
            } catch (Exception pe)
            {
                AdminUI_LogIndicationLabel.setText("Wrong Date Format");
                PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(3)
                );
                visiblePause.setOnFinished(
                        event2 -> AdminUI_LogIndicationLabel.setText("Search in Log based on:")
                );
                visiblePause.play();
                return;
            }

        App.scene = new Scene(App.loadFXML("CustomerLogActions"), 752, 400);
        Stage secondaryStage = new Stage(StageStyle.UNDECORATED);
        secondaryStage.setScene(App.scene);
        secondaryStage.show();
    }

    @FXML
    private void addCustomer(ActionEvent event) throws Exception{
        Scene phoneScene = new Scene(App.loadFXML("addCustomer"), 500, 740);
        Stage phoneStage = new Stage(StageStyle.UNDECORATED);
        phoneStage.setScene(phoneScene);
        phoneStage.showAndWait();

        initializeARCustomers();
    }

    @FXML
    private void removeCustomer(ActionEvent event) throws Exception{
        CustUser selected = AdminUI_AccountRegistry_CustomersTable.getSelectionModel().getSelectedItem();
        if (selected!= null)
        {
            try {
                App.connectDB.setAutoCommit(false);
                //user deletion: db trigger will update log
                String query = "delete from user where user_id ="+selected.getCustUserId();
                DBUtil.DBexecuteQuery(App.connectDB, query); 

                //customer is being deleted due to cascade: no trigger called, log entry inserted manually
                try {
                    String logdetail = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 1, \"customer\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logdetail); 
                } catch (Exception s){ /*if this query fails, we don't care */
                    s.printStackTrace();      
                }
                
                //address deletion: db trigger will update log
                String query_part2 = "delete from address where address_id ="+selected.getCustUserAddress().getAddressID();
                DBUtil.DBexecuteQuery(App.connectDB, query_part2);
                
                AdminUI_AccountRegistry_CustomersTable.getItems().remove(selected);
            }catch (Exception e)    //failed delete
            {
                //cancel any changes
                try {
                    App.connectDB.rollback();
                }
                catch (Exception k){
                    k.printStackTrace();
                }
                
                //print error message
                AdminUI_AccountRegistry_ErrorIndicationLabel.setText("Error Occured. Customer was not deleted.");
                PauseTransition visiblepause = new PauseTransition(Duration.seconds(3));
                visiblepause.setOnFinished(someevent -> AdminUI_AccountRegistry_ErrorIndicationLabel.setText(""));
                visiblepause.play();

                /*
                * log updates: manual 
                * no matter the query that failed, all queries are considered failed because the action is atomic 
                */

                String logfailedelete1 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"user\");";
                String logfailedelete2 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"customer\");";
                String logfailedelete3 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"address\");";
                
                //any exceptions cause here will be thrown
                DBUtil.DBexecuteQuery(App.connectDB, logfailedelete1);
                DBUtil.DBexecute(App.connectDB, logfailedelete2);
                DBUtil.DBexecuteQuery(App.connectDB, logfailedelete3);
                
            }
        
            App.connectDB.commit();
            App.connectDB.setAutoCommit(true);
        }

    }

    @FXML
    private void addEmployee(ActionEvent event) throws Exception{
        Scene phoneScene = new Scene(App.loadFXML("addEmployee"), 500, 740);
        Stage phoneStage = new Stage(StageStyle.UNDECORATED);
        phoneStage.setScene(phoneScene);
        phoneStage.showAndWait();

        initializeARAdmins();
        initializeAREmployees();
    }

    @FXML
    private void removeEmployee(ActionEvent event) throws Exception{
        EmpUser selected = AdminUI_AccountRegistry_EmployeesTable.getSelectionModel().getSelectedItem();
        if (selected!= null)
        {
            try {
                //user deletion: db trigger will update log
                String query = "delete from user where user_id ="+selected.getEmpUserId();
                DBUtil.DBexecuteQuery(App.connectDB, query); 
                
                //employee is being deleted by cascade, log updated manually
                try {
                    String logdetail = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 1, \"employee\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logdetail); 
                }
                catch (Exception s){ /*if this query fails, we don't care */
                    s.printStackTrace();
                }
                
                //employee has no address
                AdminUI_AccountRegistry_EmployeesTable.getItems().remove(selected);

            }catch (Exception e) {  //user deletion failed
                //print error message
                AdminUI_AccountRegistry_ErrorIndicationLabel.setText("Error Occured. Employee was not deleted.");
                PauseTransition visiblepause = new PauseTransition(Duration.seconds(3));
                visiblepause.setOnFinished(someevent -> AdminUI_AccountRegistry_ErrorIndicationLabel.setText(""));
                visiblepause.play();

                //log updates: manual 
                String logfailedelete1 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"user\");";
                DBUtil.DBexecuteQuery(App.connectDB, logfailedelete1);
                String logfailedelete2 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"employee\");";
                DBUtil.DBexecuteQuery(App.connectDB, logfailedelete2);

            }
           
        }
     
    }

    @FXML
    private void promoteEmployeeToAdmin(ActionEvent event) throws Exception{
        EmpUser selected = AdminUI_AccountRegistry_EmployeesTable.getSelectionModel().getSelectedItem();

        if (selected != null)
        {   //change a user from employee to admin
            try 
            {
                //atomic action 
                App.connectDB.setAutoCommit(false);
                
                //part 1: remove the user from employyee table: db trigger will update the log
                String movequery_part1 = "delete from employee where employee_id = "+selected.getEmpUserId();
                DBUtil.DBexecuteQuery(App.connectDB, movequery_part1);
                //part 2: add the user to admin table: db trigger will update the log
                String movequery_part2 = "insert into admin values ("+selected.getEmpUserId()+");";
                DBUtil.DBexecuteQuery(App.connectDB, movequery_part2);
                //part 3: change the user's type: db trigger will update the log
                String movequery_part3 = "update user set usertype=\"ADMIN\" where user_id="+selected.getEmpUserId()+";";
                DBUtil.DBexecuteQuery(App.connectDB, movequery_part3);

            }catch (Exception e)
            {
                try{
                    //undo changes
                    App.connectDB.rollback();
                }
                catch (Exception s) {s.printStackTrace();}

                //print error message
                AdminUI_AccountRegistry_ErrorIndicationLabel.setText("Error Occured. No promotion given.");
                PauseTransition visiblepause = new PauseTransition(Duration.seconds(3));
                visiblepause.setOnFinished(someevent -> AdminUI_AccountRegistry_ErrorIndicationLabel.setText(""));
                visiblepause.play();


                //log update: manual 
                //no matter the query that failed, the whole action is considered failed
                String logupdate1 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", \"employee\");";
                DBUtil.DBexecuteQuery(App.connectDB, logupdate1);                
                String logupdate2 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", \"admin\");";
                DBUtil.DBexecuteQuery(App.connectDB, logupdate2);                
                String logupdate3 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"UPDATE\", \"user\");";
                DBUtil.DBexecuteQuery(App.connectDB, logupdate3);
                  
            }

            App.connectDB.commit();
            App.connectDB.setAutoCommit(true); 

            initializeARAdmins();
            initializeAREmployees();
        }
    }

    @FXML
    private void removeAdmin(ActionEvent event) throws Exception{
        AdminUser selected = AdminUI_AccountRegistry_AdminsTable.getSelectionModel().getSelectedItem();
        if (selected!= null)
        {
            try {
                //user deletion: db trigger will update log
                String query = "delete from user where user_id ="+selected.getAdminUserId();
                DBUtil.DBexecuteQuery(App.connectDB, query); 

                try {   /* admin deleted due to cascade, log updated manually */
                    String logdetail = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 1, \"admin\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logdetail); 
                }
                catch (Exception s){ /*if this query fails, we don't care */
                    s.printStackTrace();
                }

                AdminUI_AccountRegistry_AdminsTable.getItems().remove(selected);
            }catch (Exception e) {  //user deletion failed
                //print error message
                AdminUI_AccountRegistry_ErrorIndicationLabel.setText("Error Occured. Admin was not deleted.");
                PauseTransition visiblepause = new PauseTransition(Duration.seconds(3));
                visiblepause.setOnFinished(someevent -> AdminUI_AccountRegistry_ErrorIndicationLabel.setText(""));
                visiblepause.play();

                //log update: manual
                String logfailedelete1 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"user\");";
                DBUtil.DBexecuteQuery(App.connectDB, logfailedelete1);
                String logfailedelete2 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 0, \"admin\");";
                DBUtil.DBexecuteQuery(App.connectDB, logfailedelete2);


            }
            
        }
    }

    @FXML
    private void DemoteAdminToEmployee(ActionEvent event) throws Exception{
        AdminUser selected = AdminUI_AccountRegistry_AdminsTable.getSelectionModel().getSelectedItem();
        if (selected != null)
        {
            AdminDemoteToEmployeeController.convert = selected;
            /*pop-up for phone-number and then insert (phone = the value of the text box or NULL)*/
            Scene phoneScene = new Scene(App.loadFXML("demoteToEmployee"), 500, 250);
            Stage phoneStage = new Stage(StageStyle.UNDECORATED);
            phoneStage.setScene(phoneScene);
            phoneStage.showAndWait(); 

            initializeARAdmins();
            initializeAREmployees();
        }
               
    }

    @FXML
    private void AcceptCustomer(ActionEvent event)throws Exception{
        CustUser selected = AdminUI_AccountRegistry_RequestsTable.getSelectionModel().getSelectedItem();

        if (selected != null)
        {
            try {
                String query= "update user set pending=0 where user_id="+selected.getCustUserId();
                DBUtil.DBexecuteQuery(App.connectDB, query);
                //update successful: db trigger wil update the log
                initializeARCustomers();
                initializeARRequests();
            } catch (Exception e){
                String query= "insert into log values (NULL, \""+App.email+"\", NOW(), \"UPDATE\", \"user\");";
                DBUtil.DBexecuteQuery(App.connectDB, query);
            }
            
        }

    }

    @FXML
    private void RejectCustomer(ActionEvent event) throws Exception{
        CustUser selected = AdminUI_AccountRegistry_RequestsTable.getSelectionModel().getSelectedItem();
        if (selected!= null)
        {
            try {
                String query = "delete from user where user_id ="+selected.getCustUserId();
                DBUtil.DBexecuteQuery(App.connectDB, query); 
                //user deletion successful: db trigger will update the log

                try {   /* customer deleted due to cascade, log updated manually */
                    String logdetail = "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", 1, \"customer\");";
                    DBUtil.DBexecuteQuery(App.connectDB, logdetail); 
                }
                catch (Exception s){ /*if this query fails, we don't care */
                    s.printStackTrace();
                }
                
                AdminUI_AccountRegistry_RequestsTable.getItems().remove(selected);
            }catch (Exception e){   // user deletion failed

                //print error message
                AdminUI_AccountRegistry_ErrorIndicationLabel.setText("Error Occured. Request still remains.");
                PauseTransition visiblepause = new PauseTransition(Duration.seconds(3));
                visiblepause.setOnFinished(someevent -> AdminUI_AccountRegistry_ErrorIndicationLabel.setText(""));
                visiblepause.play();

                //log update: manual
                String query= "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", \"user\");";
                DBUtil.DBexecuteQuery(App.connectDB, query);
                String query_part2= "insert into log values (NULL, \""+App.email+"\", NOW(), \"DELETE\", \"customer\");";
                DBUtil.DBexecuteQuery(App.connectDB, query_part2);
            }
            
        }

    }

    @FXML
    private void setFonlyPrice(ActionEvent event) throws Exception
    {
        try{
            if (!AdminUI_PricingList_FilmsOnlyPrice.getText().isBlank())
            {
                LoginPageController.filmsonly = Float.parseFloat(AdminUI_PricingList_FilmsOnlyPrice.getText());
                String newprice = "SET @films_only = "+AdminUI_PricingList_FilmsOnlyPrice.getText()+";";
                DBUtil.DBexecuteQuery(App.connectDB, newprice);
            }
            else 
            {
                AdminUI_PricingList_ErrorIndicationLabel.setText("TextField is Empty. No changes made.");
                PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(8)
                );
                visiblePause.setOnFinished(
                        event1 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play(); 
            }
        }catch (Exception e)
        {
            AdminUI_PricingList_ErrorIndicationLabel.setText("Invalid price inserted. No changes made.");
            PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(5)
                );
                visiblePause.setOnFinished(
                        event2 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play();
        }
         
        ResultSet rs = DBUtil.DBexecute(App.connectDB, "SELECT @films_only;");
        if (rs.next()) AdminUI_PricingList_FilmsOnlyPrice.setText(String.valueOf(rs.getFloat("@films_only")));
        rs.close();
    }

    @FXML
    private void setSonlyPrice(ActionEvent event) throws Exception
    {
        try{
            if (!AdminUI_PricingList_SeriesOnlyPrice.getText().isBlank())
            {
                LoginPageController.seriesonly = Float.parseFloat(AdminUI_PricingList_SeriesOnlyPrice.getText());
                String newprice = "SET @series_only = "+AdminUI_PricingList_SeriesOnlyPrice.getText()+";";
                DBUtil.DBexecuteQuery(App.connectDB, newprice);
            }
            else {
                AdminUI_PricingList_ErrorIndicationLabel.setText("TextField is Empty. No changes made.");
                PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(8)
                );
                visiblePause.setOnFinished(
                        event1 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play(); 
            }
        }catch (Exception e)
        {
            AdminUI_PricingList_ErrorIndicationLabel.setText("Invalid price inserted. No changes made.");
            PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(5)
                );
                visiblePause.setOnFinished(
                        event2 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play();
        }
         
        ResultSet rs = DBUtil.DBexecute(App.connectDB, "SELECT @series_only;");
        if (rs.next()) AdminUI_PricingList_SeriesOnlyPrice.setText(String.valueOf(rs.getFloat("@series_only")));
        rs.close();
    }

    @FXML
    private void setFSPrice(ActionEvent event) throws Exception
    {
        try{
            if (!AdminUI_PricingList_FilmsSeriesPrice.getText().isBlank())
            {
                LoginPageController.filmsboth = Float.parseFloat(AdminUI_PricingList_FilmsSeriesPrice.getText());
                String newprice = "SET @films_series = "+AdminUI_PricingList_FilmsSeriesPrice.getText()+";";
                DBUtil.DBexecuteQuery(App.connectDB, newprice);
            }
            else 
            {
                AdminUI_PricingList_ErrorIndicationLabel.setText("TextField is Empty. No changes made.");
                PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(8)
                );
                visiblePause.setOnFinished(
                        event1 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play(); 
            }
        }catch (Exception e)
        {
            AdminUI_PricingList_ErrorIndicationLabel.setText("Invalid price inserted. No changes made.");
            PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(5)
                );
                visiblePause.setOnFinished(
                        event2 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play();
        }
         
        ResultSet rs = DBUtil.DBexecute(App.connectDB, "SELECT @films_series;");
        if (rs.next()) AdminUI_PricingList_FilmsSeriesPrice.setText(String.valueOf(rs.getFloat("@films_series")));
        rs.close();
    }

    @FXML
    private void setSFPrice(ActionEvent event) throws Exception
    {
        try{
            if (!AdminUI_PricingList_SeriesFilmsPrice.getText().isBlank())
            {
                LoginPageController.seriesboth = Float.parseFloat(AdminUI_PricingList_SeriesFilmsPrice.getText());
                String newprice = "SET @series_films = "+AdminUI_PricingList_SeriesFilmsPrice.getText()+";";
                DBUtil.DBexecuteQuery(App.connectDB, newprice);
            }
            else 
            {
                AdminUI_PricingList_ErrorIndicationLabel.setText("TextField is Empty. No changes made.");
                PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(8)
                );
                visiblePause.setOnFinished(
                        event1 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play(); 
            }
        }catch (Exception e)
        {
            AdminUI_PricingList_ErrorIndicationLabel.setText("Invalid price inserted. No changes made.");
            PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(5)
                );
                visiblePause.setOnFinished(
                        event2 -> AdminUI_PricingList_ErrorIndicationLabel.setText("")
                );
                visiblePause.play();
        }
         
        ResultSet rs = DBUtil.DBexecute(App.connectDB, "SELECT @series_films;");
        if (rs.next()) AdminUI_PricingList_SeriesFilmsPrice.setText(String.valueOf(rs.getFloat("@series_films")));
        rs.close();
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage currentstage = (Stage) AdminUI_hellouser_label.getScene().getWindow();
        currentstage.close();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException{
        App.scene = new Scene(App.loadFXML("LoginPage"), 600, 400);
        Stage primaryStage = new Stage(StageStyle.UNDECORATED);
        primaryStage.setScene(App.scene);
        primaryStage.show();

        Stage currentstage = (Stage) AdminUI_hellouser_label.getScene().getWindow();
        currentstage.close();
    }

}
