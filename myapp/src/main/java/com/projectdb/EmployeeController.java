package com.projectdb;

//Java Imports
import java.io.IOException;

//JavaFx Basics
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.stage.StageStyle;
import javafx.scene.Scene;

//javaFX Animation
import javafx.util.Duration;
import javafx.animation.PauseTransition;

//JavaFx Modules Import
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

//JDBC - Mariadb Imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeController {

    public static String editID = "";
       
    //Left Pane modules
    @FXML
    private Label EmployeeLabelEmail;
    @FXML
    private Button EmployeeButtonBestSellers;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Button RefreshButton;

    /* ---------- ViewTables ---------- */

    //Customer
    @FXML
    private TableView<ModelCustomer> CustomersTable;
    @FXML
    private TableColumn<ModelCustomer,Integer> CustomersID;
    @FXML
    private TableColumn<ModelCustomer,String> CustomersType;
    @FXML
    private TableColumn<ModelCustomer,String> CustomersAdd;
    @FXML
    private TableColumn<ModelCustomer,String> CustomersFName;
    @FXML
    private TableColumn<ModelCustomer,String> CustomersLName;
    @FXML
    private TableColumn<ModelCustomer,String> CustomersEmail;
    @FXML
    private TableColumn<ModelCustomer,String> CustomersYear;
    @FXML
    private TableColumn<ModelCustomer,String> CustomersActive;
    
    @FXML
    private TableColumn<ModelCustomer,Integer> CustomersPending;
    

    @FXML
    private TextField CustomerRecordID;
    @FXML
    private Button CustomerEdit;
   
    //Rental
    @FXML
    private TableView<ModelRentals> RentalsTable;
    @FXML
    private TableColumn<ModelRentals,Integer> RentalsID;
    @FXML
    private TableColumn<ModelRentals,Integer> RentalsFilmID;
    @FXML
    private TableColumn<ModelRentals,Integer> RentalsEpID;
    @FXML
    private TableColumn<ModelRentals,Integer> RentalsCustomerID;
    @FXML
    private TableColumn<ModelRentals,String> RentalsDate;
    @FXML
    private TableColumn<ModelRentals,String> RentalsEmail;
    @FXML
    private TableColumn<ModelRentals,String> RentalsTitle;
    @FXML
    private Button RentalSales;
    @FXML
    private TextField RentalNumber;
    @FXML
    private TextField RentalStartingDate;
    @FXML
    private TextField RentalEndDate;
    @FXML
    private Label RentalErrorLabel;


    //Films
    @FXML
    private TableView<ModelFilms> FilmsTable;
    @FXML
    private TableColumn<ModelFilms,Integer> FilmsID;
    @FXML
    private TableColumn<ModelFilms,String> FilmsTitle;
    @FXML
    private TableColumn<ModelFilms,String> FilmsDesc;
    @FXML
    private TableColumn<ModelFilms,Integer> FilmsYear;
    @FXML
    private TableColumn<ModelFilms,Integer> FilmsSubs;
    @FXML
    private TableColumn<ModelFilms,Integer> FilmsLang;
    @FXML
    private TableColumn<ModelFilms,Integer> FilmsLength;
    @FXML
    private TableColumn<ModelFilms,String> FilmsRating;
    @FXML
    private TableColumn<ModelFilms,String> FilmsSP;
    @FXML
    private Label FilmsErrorLabel;
    @FXML
    private TextField FilmsTextField;
    
    //Series
    @FXML
    private TableView<ModelSeries> SeriesTable;
    @FXML
    private TableColumn<ModelSeries,Integer> SeriesID;
    @FXML
    private TableColumn<ModelSeries,String> SeriesTitle;
    @FXML
    private TableColumn<ModelSeries,String> SeriesDesc;
    @FXML
    private TableColumn<ModelSeries,Integer> SeriesYear;
    @FXML
    private TableColumn<ModelSeries,Integer> SeriesSubs;
    @FXML
    private TableColumn<ModelSeries,Integer> SeriesLang;
    @FXML
    private TableColumn<ModelSeries,String> SeriesRating;
    @FXML
    private Label SeriesErrorLabel;
    @FXML
    private TextField SeriesTextField;

    //Seasons
    @FXML
    private TableView<ModelSeasons> SeasonsTable;
    @FXML
    private TableColumn<ModelSeasons,Integer> SeasonsID;
    @FXML
    private TableColumn<ModelSeasons,Integer> SeasonsSeriesID;
    @FXML
    private TableColumn<ModelSeasons,Integer> SeasonsNum;
    @FXML
    private TableColumn<ModelSeasons,Integer> SeasonsEpNum;
    @FXML
    private TableColumn<ModelSeasons,Integer> SeasonsYear;
    @FXML
    private TableColumn<ModelSeasons,String> SeasonsTitle;
    @FXML
    private Label SeasonsErrorLabel;
    @FXML
    private TextField SeasonsTextField;

    //Episodes
    @FXML
    private TableView<ModelEpisodes> EpisodesTable;
    @FXML
    private TableColumn<ModelEpisodes,Integer> EpisodesID;
    @FXML
    private TableColumn<ModelEpisodes,String> EpisodesTitle;
    @FXML
    private TableColumn<ModelEpisodes,Integer> EpisodesSeasonID;
    @FXML
    private TableColumn<ModelEpisodes,Integer> EpisodesNum;
    @FXML
    private TableColumn<ModelEpisodes,Integer> EpisodesLength;
    @FXML
    private TableColumn<ModelEpisodes,String> EpisodesRating;
    @FXML
    private TableColumn<ModelEpisodes,Integer> EpisodesSeriesID;
    @FXML
    private TableColumn<ModelEpisodes,String> EpisodesSeriesTitle;
    @FXML
    private Label EpisodeErrorLabel;
    @FXML
    private TextField EpisodeTextField;

    //Categories
    @FXML
    private TableView<ModelCategories> CategoriesTable;
    @FXML
    private TableColumn<ModelCategories,Integer> CategoriesID;
    @FXML
    private TableColumn<ModelCategories,String> CategoriesName;
    @FXML
    private Label CatErrorLabel;
    @FXML
    private TextField CatTextField;

    //Films Film-Categories
    @FXML
    private TableView<ModelFilmsCategories> FilmsCatTable;
    @FXML
    private TableColumn<ModelFilmsCategories,Integer> FilmsCatFilmID;
    @FXML
    private TableColumn<ModelFilmsCategories,Integer> FilmsCatCatID;
    @FXML
    private TableColumn<ModelFilmsCategories,String> FilmsCatFilmTitle;
    @FXML
    private TableColumn<ModelFilmsCategories,String> FilmsCatCAtegoryName;
    @FXML
    private Label FilmCatErrorLabel;
    @FXML
    private TextField FilmFCatTextField;
    @FXML
    private TextField FilmCCatTextField;
    
    
    //Series Series-Categories
    @FXML
    private TableView<ModelSeriesCategories> SeriesCatTable;
    @FXML
    private TableColumn<ModelSeriesCategories,Integer> SeriesCatFilmID;
    @FXML
    private TableColumn<ModelSeriesCategories,Integer> SeriesCatCatID;
    @FXML
    private TableColumn<ModelSeriesCategories,String> SeriesCatSeriesTitle;
    @FXML
    private TableColumn<ModelSeriesCategories,String> SeriesCatCategoryName;
    @FXML
    private Label SeriesCatErrorLabel;
    @FXML
    private TextField SeriesSCatTextField;
    @FXML
    private TextField SeriesCCatTextField;
    
    
    //Actors
    @FXML
    private TableView<ModelActors> ActorTable;
    @FXML
    private TableColumn<ModelActors,Integer> ActorID;
    @FXML
    private TableColumn<ModelActors,String> ActorFName;
    @FXML
    private TableColumn<ModelActors,String> ActorLName;
    @FXML
    private Label ActorErrorLabel;
    @FXML
    private TextField ActorTextField;

    //Films Actors
    @FXML
    private TableView<ModelFilmsActors> FilmsActorsTable;
    @FXML
    private TableColumn<ModelFilmsActors,Integer> FilmsActorsActorID;
    @FXML
    private TableColumn<ModelFilmsActors,Integer> FilmsActorsFilmID;
    @FXML
    private TableColumn<ModelFilmsActors,String> FilmsActorsFilmTitle;
    @FXML
    private TableColumn<ModelFilmsActors,String> FilmsActorsActorsName;
    @FXML
    private Label FilmActorErrorLabel;
    @FXML
    private TextField FilmFTextField;
    @FXML
    private TextField FilmATextField;


    //Series Actors
    @FXML
    private TableView<ModelSeriesActors> SeriesActorsTable;
    @FXML
    private TableColumn<ModelSeriesActors,Integer> SeriesActorsActorID;
    @FXML
    private TableColumn<ModelSeriesActors,String> SeriesActorsActorName;
    @FXML
    private TableColumn<ModelSeriesActors,Integer> SeriesActorsSeasonID;
    @FXML
    private TableColumn<ModelSeriesActors,Integer> SeriesActorsSeasonNum;
    @FXML
    private TableColumn<ModelSeriesActors,Integer> SeriesActorsSeriesID;
    @FXML
    private TableColumn<ModelSeriesActors,String> SeriesActorsSeriesTitle;
    @FXML
    private Label SeriesActorErrorLabel;
    @FXML
    private TextField SeriesSTextField;
    @FXML
    private TextField SeriesATextField;


    //Countries
    @FXML
    private TableView<ModelCountries> CountriesTable;
    @FXML
    private TableColumn<ModelCountries,Integer> CountriesID;
    @FXML
    private TableColumn<ModelCountries,String> CountriesName;
    @FXML
    private TextField CountryTextField;
    @FXML
    private Label CountryErrorLabel;

    //Cities
    @FXML
    private TableView<ModelCities> CitiesTable;
    @FXML
    private TableColumn<ModelCities,Integer> CitiesID;
    @FXML
    private TableColumn<ModelCities,String> CitiesName;
    @FXML
    private TableColumn<ModelCities,Integer> CitiesCountryID;
    @FXML
    private TableColumn<ModelCities,String> CountryName;
    @FXML
    private TextField CitiesTextField;
    @FXML
    private Label CitiesErrorLabel;
    
    //Addresses
    @FXML
    private TableView<ModelAddresses> AddressTable;
    @FXML
    private TableColumn<ModelAddresses,Integer> AddressID;
    @FXML
    private TableColumn<ModelAddresses,String> AddressName;
    @FXML
    private TableColumn<ModelAddresses,String> AddressDistrict;
    @FXML
    private TableColumn<ModelAddresses,Integer> AddressCityID;
    @FXML
    private TableColumn<ModelAddresses,String> AddressCityName;
    @FXML
    private TableColumn<ModelAddresses,String> AddressPostal;
    @FXML
    private TableColumn<ModelAddresses,String> AddressPhone;
    @FXML
    private Label AddressErrorLabel;
    @FXML
    private TextField AddressTextField;
    
    //Languages
    @FXML
    private TableView<ModelLangs> LangsTable;
    @FXML
    private TableColumn<ModelLangs,Integer> LangsID;
    @FXML
    private TableColumn<ModelLangs,String> LangsName;
    @FXML
    private Label LangErrorLabel;
    @FXML
    private TextField LangTextField;
    @FXML
    private Button LangEdit;
    @FXML
    private Button LangDel;
    @FXML
    private Button LangNew;

    //Price
    @FXML
    private Label EmployeeFilmPriceO;
    @FXML
    private Label EmployeeSeriesPriceO;
    @FXML
    private Label EmployeeFilmPriceB;
    @FXML
    private Label EmployeeSeriesPriceB;


    //ObservableLists
    private ObservableList<ModelLangs> LangDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelCustomer> CustDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelRentals> RentalDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelAddresses> AddressDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelFilms> FilmDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelSeries> SeriesDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelSeasons> SeasonsDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelEpisodes> EpisodesDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelCategories> CategDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelCountries> CountryDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelFilmsCategories> FilmCategDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelSeriesCategories> SeriesCategDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelActors> ActorDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelFilmsActors> FilmActorDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelSeriesActors> SeriesActorDataObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelCities> CityDataObservableList = FXCollections.observableArrayList();


    /* ---------- Methods and EventHandlers ---------- */
    @FXML
    private void initialize(){
        // Set the Welcome label with the correct user
        EmployeeLabelEmail.setText(App.email);
        initializeCustTable();
        initializeRentalsTable();
        initializeFilmsTable();
        initializeSeriesTable();
        initializeSeasonsTable();
        initializeEpisodesTable();
        initializeCategoriesTable();
        initializeFilmCatTable();
        initializeLangTable();
        initializeSeriesCatTable();
        initializeActorTable();
        initializeFilmActorsTable();
        initializeSeriesActorsTable();
        initializeCitiesTable();
        initializeCountriesTable();
        initializeAddressTable();

        //set the current prices
        EmployeeFilmPriceO.setText(getPrice("Select @films_only"));
        EmployeeFilmPriceB.setText(getPrice( "Select @films_series"));
        EmployeeSeriesPriceB.setText(getPrice( "Select @series_films"));
        EmployeeSeriesPriceO.setText(getPrice( "Select @series_only"));

    }

    /* --------- General Methods --------- */
    private void secondaryStageClose(){
        Stage currentStage = (Stage) EmployeeLabelEmail.getScene().getWindow();
        currentStage.close();
    }

    private String getPrice( String query ){
        
        String result = "";
        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery(query);
            
            if ( Data.next() ) {
                result = Data.getString(1);
            }

            Data.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @FXML
    private void LogoutOnAction(ActionEvent e) throws IOException{
        App.scene = new Scene(App.loadFXML("LoginPage"), 600, 400);
        Stage primaryStage = new Stage(StageStyle.UNDECORATED);
        primaryStage.setScene(App.scene);
        primaryStage.show();
        secondaryStageClose();
    }

    @FXML
    private void RefreshOnAction(){

        refreshCustTable();
        refreshRentalTable();
        refreshLangTable();
        LangErrorLabel.setText("");
        refreshCountryTable();
        CountryErrorLabel.setText("");
        refreshActorTable();
        ActorErrorLabel.setText("");
        refreshCitiesTable();
        CitiesErrorLabel.setText("");
        refreshCatTable();
        CatErrorLabel.setText("");
        refreshAddressTable();
        AddressErrorLabel.setText("");
        refreshFilmCatTable();
        FilmCatErrorLabel.setText("");
        refreshSeriesCatTable();
        SeriesCatErrorLabel.setText("");
        refreshFilmActorTable();
        FilmActorErrorLabel.setText("");
        refreshSeriesActorTable();
        SeriesActorErrorLabel.setText("");
        refreshFilmsTable();
        FilmsErrorLabel.setText("");
        refreshSeriesTable();
        SeriesErrorLabel.setText("");
        refreshEpisodeTable();
        EpisodeErrorLabel.setText("");
        refreshSeasonsTable();
        SeasonsErrorLabel.setText("");
        RentalErrorLabel.setText("");

    }
  
    @FXML
    private void ExitOnAction(ActionEvent e) {
        secondaryStageClose();
    }

    private void EmptyLabelTransition ( Label label ){
        PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(5)
        );
        visiblePause.setOnFinished(
                event -> label.setText("")
        );
        visiblePause.play();
    }

    /* --------- Customer --------- */
    @FXML
    private void CustomerTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( CustomersTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelCustomer selected = CustomersTable.getSelectionModel().getSelectedItem();
            CustomerRecordID.setText(String.valueOf(selected.getCustomerID()));
        }
    }
   
    public void refreshCustTable(){
        CustDataObservableList.clear();
        initializeCustTable();
    }

    @FXML
    private void customerEditOnAction(ActionEvent e) throws IOException{
        if( !CustomerRecordID.getText().isBlank() ){
            editID = CustomerRecordID.getText();
            Scene editScene = new Scene(App.loadFXML("editCustomer"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();

            refreshCustTable();
            refreshAddressTable();
            refreshCitiesTable();
            refreshCountryTable();
        }
    }
    
    /* --------- Rental --------- */
    private void refreshRentalTable(){
        RentalDataObservableList.clear();
        initializeRentalsTable();
    }

    @FXML
    private void RentalSalesOnAction(){
        String StartDate, EndDate, NumberOfRecords;
        
        //initializing the procedure arguments
        if ( RentalNumber.getText().isBlank() ) NumberOfRecords = "5";
        else NumberOfRecords = RentalNumber.getText();

        if ( RentalEndDate.getText().isBlank() ) EndDate = String.valueOf(java.time.LocalDate.now());
        else EndDate = RentalEndDate.getText();

        if ( RentalStartingDate.getText().isBlank() ) StartDate = String.valueOf(java.time.LocalDate.now().minusMonths(1));
        else StartDate = RentalStartingDate.getText();

        try {
            //delete the previous values of temp tables and call the needed procedure

            PreparedStatement pstm;
            String delfilms = "delete from z3_1films";
            String delseries = "delete from z3_1series";
            App.connectDB.createStatement().executeUpdate(delfilms);
            App.connectDB.createStatement().executeUpdate(delseries);

            String call3_1f = "call zhtoumeno3_1('m',?,?,?);";
            pstm = App.connectDB.prepareStatement(call3_1f);
            pstm.setString(1,NumberOfRecords);
            pstm.setString(2,StartDate);
            pstm.setString(3,EndDate);
            pstm.executeUpdate();

            String call3_1s = "call zhtoumeno3_1('s',?,?,?);";
            pstm = App.connectDB.prepareStatement(call3_1s);
            pstm.setString(1,NumberOfRecords);
            pstm.setString(2,StartDate);
            pstm.setString(3,EndDate);
            pstm.executeUpdate();

            //Create the new stage
            Scene grossing = new Scene(App.loadFXML("highestGrossing"), 400, 600);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(grossing);
            thirdStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            RentalErrorLabel.setText("Error: Query returned with errors. Ensure the correct date format is used.");
            EmptyLabelTransition(RentalErrorLabel);
        }
    }

    /* --------- Films --------- */

    @FXML
    private void FilmsTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( FilmsTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelFilms selected = FilmsTable.getSelectionModel().getSelectedItem();
            FilmsTextField.setText(String.valueOf(selected.getFilmId()));
        }
    }

    public void refreshFilmsTable(){
        FilmDataObservableList.clear();
        initializeFilmsTable();
    }

    @FXML 
    private void FilmsDelOnAction(ActionEvent e) {
        if( !FilmsTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from film where film_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(FilmsTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshFilmsTable();

            } catch (Exception s) {
                s.printStackTrace();
                FilmsErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(FilmsErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"film\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    @FXML 
    private void FilmsEditOnAction(ActionEvent e) throws IOException {
        if( !FilmsTextField.getText().isBlank() ){
            editID = FilmsTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editFilm"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshFilmsTable();
        }
    }

    @FXML 
    private void FilmsNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newFilm"), 400, 600);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshFilmsTable();
    }

    /* --------- Series --------- */
    @FXML
    private void SeriesTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( SeriesTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelSeries selected = SeriesTable.getSelectionModel().getSelectedItem();
            SeriesTextField.setText(String.valueOf(selected.getSeriesID()));
        }
    }

    public void refreshSeriesTable(){
        SeriesDataObservableList.clear();
        initializeSeriesTable();
    }

    @FXML 
    private void SeriesDelOnAction(ActionEvent e) {
        if( !SeriesTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from series where series_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(SeriesTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshSeriesTable();

            } catch (Exception s) {
                s.printStackTrace();
                SeriesErrorLabel.setText("Error: Delete action is not Permitted");
                
                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"series\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }

                EmptyLabelTransition(SeriesErrorLabel);
            }
        }
    }

    @FXML 
    private void SeriesEditOnAction(ActionEvent e) throws IOException {
        if( !SeriesTextField.getText().isBlank() ){
            editID = SeriesTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editSeries"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshSeriesTable();
        }
    }

    @FXML 
    private void SeriesNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newSeries"), 400, 600);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshSeriesTable();
    }

    /* --------- Categories --------- */
    @FXML
    private void CatTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( CategoriesTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelCategories selected = CategoriesTable.getSelectionModel().getSelectedItem();
            CatTextField.setText(String.valueOf(selected.getCategoriesID()));
        }
    }

    public void refreshCatTable(){
        CategDataObservableList.clear();
        initializeCategoriesTable();
    }

    @FXML 
    private void CatDelOnAction(ActionEvent e) {
        if( !CatTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from category where category_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(CatTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshCatTable();

            } catch (Exception s) {
                s.printStackTrace();
                CatErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(CatErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"category\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    @FXML 
    private void CatEditOnAction(ActionEvent e) throws IOException {
        if( !CatTextField.getText().isBlank() ){
            editID = CatTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editCategory"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshCatTable();
        }
    }

    @FXML 
    private void CatnewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newCategory"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshCatTable();
    }

    /* --------- Episode --------- */
    @FXML
    private void EpisodeTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( EpisodesTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelEpisodes selected = EpisodesTable.getSelectionModel().getSelectedItem();
            EpisodeTextField.setText(String.valueOf(selected.getEpisodesID()));
        }
    }

    public void refreshEpisodeTable(){
        EpisodesDataObservableList.clear();
        initializeEpisodesTable();
    }

    @FXML 
    private void EpisodeDelOnAction(ActionEvent e) {
        if( !EpisodeTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from episode where episode_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(EpisodeTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshEpisodeTable();

            } catch (Exception s) {
                s.printStackTrace();
                EpisodeErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(EpisodeErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"episode\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    @FXML 
    private void EpisodeEditOnAction(ActionEvent e) throws IOException {
        if( !EpisodeTextField.getText().isBlank() ){
            editID = EpisodeTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editEpisode"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshEpisodeTable();
        }
    }

    @FXML 
    private void EpisodeNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newEpisode"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshEpisodeTable();
    }

    /* --------- Season --------- */
    @FXML
    private void SeasonTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( SeasonsTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelSeasons selected = SeasonsTable.getSelectionModel().getSelectedItem();
            SeasonsTextField.setText(String.valueOf(selected.getSeasonsID()));
        }
    }

    public void refreshSeasonsTable(){
        SeasonsDataObservableList.clear();
        initializeSeasonsTable();
    }

    @FXML 
    private void SeasonsDelOnAction(ActionEvent e) {
        if( !SeasonsTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from season where season_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(SeasonsTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshSeasonsTable();

            } catch (Exception s) {
                s.printStackTrace();
                SeasonsErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(SeasonsErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"season\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    @FXML 
    private void SeasonsEditOnAction(ActionEvent e) throws IOException {
        if( !SeasonsTextField.getText().isBlank() ){
            editID = SeasonsTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editSeason"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshSeasonsTable();
        }
    }

    @FXML 
    private void SeasonsNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newSeason"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshSeasonsTable();
    }

    /* --------- Film Categories --------- */
    @FXML
    private void FCatTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( FilmsCatTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelFilmsCategories selected = FilmsCatTable.getSelectionModel().getSelectedItem();
            FilmCCatTextField.setText(String.valueOf(selected.getFilmsCatCatID()));
            FilmFCatTextField.setText(String.valueOf(selected.getFilmsCatFilmID()));
        }
    }

    public void refreshFilmCatTable(){
        FilmCategDataObservableList.clear();
        initializeFilmCatTable();
    }

    @FXML 
    private void FCatDelOnAction(ActionEvent e) {
        if( !FilmFCatTextField.getText().isBlank() && !FilmCCatTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from film_category where film_id=? and category_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(FilmFCatTextField.getText()));
                pstm.setInt(2, Integer.valueOf(FilmCCatTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshFilmCatTable();

            } catch (Exception s) {
                s.printStackTrace();
                FilmCatErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(FilmCatErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"film_category\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    @FXML 
    private void FCatEditOnAction(ActionEvent e) throws IOException {
        if( !FilmFCatTextField.getText().isBlank() && !FilmCCatTextField.getText().isBlank() ){
            editID = FilmFCatTextField.getText()+","+FilmCCatTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editFilmCat"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshFilmCatTable();
        }
    }

    @FXML 
    private void FCatnewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newFilmCat"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshFilmCatTable();
    }

    

    /* --------- Series Categories --------- */
    @FXML
    private void SeriesCatTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( SeriesCatTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelSeriesCategories selected = SeriesCatTable.getSelectionModel().getSelectedItem();
            SeriesSCatTextField.setText(String.valueOf(selected.getSeriesCatFilmID()));
            SeriesCCatTextField.setText(String.valueOf(selected.getSeriesCatCatID()));
        }
    }

    public void refreshSeriesCatTable(){
        SeriesCategDataObservableList.clear();
        initializeSeriesCatTable();
    }

    @FXML 
    private void SCatDelOnAction(ActionEvent e) {
        if( !SeriesSCatTextField.getText().isBlank() && !SeriesCCatTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from series_category where series_id=? and category_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(SeriesSCatTextField.getText()));
                pstm.setInt(2, Integer.valueOf(SeriesCCatTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshSeriesCatTable();

            } catch (Exception s) {
                s.printStackTrace();
                SeriesCatErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(SeriesCatErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"series_category\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    @FXML 
    private void SCatEditOnAction(ActionEvent e) throws IOException {
        editID = SeriesSCatTextField.getText()+","+SeriesCCatTextField.getText();
        Scene editScene = new Scene(App.loadFXML("editSeriesCat"), 500, 740);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshSeriesCatTable();
    }

    @FXML 
    private void SCatnewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newSeriesCat"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshSeriesCatTable();
    }

    /* --------- Film Actors --------- */

    @FXML
    private void FilmActorTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( FilmsActorsTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelFilmsActors selected = FilmsActorsTable.getSelectionModel().getSelectedItem();
            FilmFTextField.setText(String.valueOf(selected.getFilmsActorsFilmID() ));
            FilmATextField.setText(String.valueOf(selected.getFilmsActorsActorID() ));
        }
    }

    @FXML 
    private void FilmActorEditOnAction(ActionEvent e) throws IOException {
        if( !FilmATextField.getText().isBlank() && !FilmFTextField.getText().isBlank() ){
            editID =  FilmFTextField.getText() +","+ FilmATextField.getText();
            Scene editScene = new Scene(App.loadFXML("editFilmActor"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshFilmActorTable();
        }
    }

    @FXML 
    private void FilmActorNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newFilmActor"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshFilmActorTable();
    }    
    public void refreshFilmActorTable() {
        FilmActorDataObservableList.clear();
        initializeFilmActorsTable();
    }

    @FXML 
    private void FilmActorDelOnAction(ActionEvent e) {
        if( !FilmATextField.getText().isBlank() && !FilmFTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from film_actor where film_id=? and actor_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(FilmFTextField.getText()));
                pstm.setInt(2, Integer.valueOf(FilmATextField.getText()));

                pstm.executeUpdate();
                pstm.close();

                refreshFilmActorTable();

            } catch (Exception s) {
                s.printStackTrace();
                FilmActorErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(FilmActorErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"film_actor\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }


    /* --------- Series Actors --------- */

    @FXML
    private void SeriesActorTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( SeriesActorsTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelSeriesActors selected = SeriesActorsTable.getSelectionModel().getSelectedItem();
            SeriesATextField.setText(String.valueOf(selected.getSeriesActorsActorID()));
            SeriesSTextField.setText(String.valueOf(selected.getSeriesActorsSeasonID()));
        }
    }

    @FXML 
    private void SeriesActorEditOnAction(ActionEvent e) throws IOException {
        if( !SeriesSTextField.getText().isBlank() && !SeriesATextField.getText().isBlank() ){
            editID =  SeriesSTextField.getText() + "," + SeriesATextField.getText();
            Scene editScene = new Scene(App.loadFXML("editSeasonActor"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshSeriesActorTable();
         }
    }

    @FXML 
    private void SeriesActorNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newSeasonActor"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshSeriesActorTable();
    }    
    public void refreshSeriesActorTable() {
        SeriesActorDataObservableList.clear();
        initializeSeriesActorsTable();
    }

    @FXML 
    private void SeriesActorDelOnAction(ActionEvent e) {
        if( !SeriesSTextField.getText().isBlank() && !SeriesATextField.getText().isBlank() ){
            try {
                String delQuery = "delete from season_actor where in_season=? and actor_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                pstm.setInt(1, Integer.valueOf(SeriesSTextField.getText()));
                pstm.setInt(2, Integer.valueOf(SeriesATextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshSeriesActorTable();

            } catch (Exception s) {
                s.printStackTrace();
                SeriesActorErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(SeriesActorErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"season_actor\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    
    /* --------- Language --------- */
    @FXML
    private void LangTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( LangsTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelLangs selected = LangsTable.getSelectionModel().getSelectedItem();
            LangTextField.setText(String.valueOf(selected.getLangsID()));
        }
    }

    public void refreshLangTable(){
        LangDataObservableList.clear();
        initializeLangTable();
    }

    @FXML 
    private void langDelOnAction(ActionEvent e) {
        if( !LangTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from lang where lang_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                pstm.setInt(1, Integer.valueOf(LangTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshLangTable();

            } catch (Exception s) {
                s.printStackTrace();
                LangErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(LangErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"lang\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    @FXML 
    private void langEditOnAction(ActionEvent e) throws IOException {
        if( !LangTextField.getText().isBlank() ){
            editID = LangTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editLang"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshLangTable();
        }
    }

    @FXML 
    private void langNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newLang"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshLangTable();
    }
    
    /* --------- Country --------- */
    @FXML
    private void CountryTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( CountriesTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelCountries selected = CountriesTable.getSelectionModel().getSelectedItem();
            CountryTextField.setText(String.valueOf(selected.getCountriesID()));
        }
    }

    @FXML 
    private void CountryEditOnAction(ActionEvent e) throws IOException {
        if( !CountryTextField.getText().isBlank() ){
            editID = CountryTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editCountry"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshCountryTable();
        }
    }

    @FXML 
    private void CountryNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newCountry"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshCountryTable();
    }    
    public void refreshCountryTable() {
        CountryDataObservableList.clear();
        initializeCountriesTable();
    }

    @FXML 
    private void CountryDelOnAction(ActionEvent e) {
        if( !CountryTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from country where country_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(CountryTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshCountryTable();

            } catch (Exception s) {
                s.printStackTrace();
                CountryErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(CountryErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"country\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }
   
    /* --------- Address --------- */
    @FXML
    private void AddressTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( AddressTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelAddresses selected = AddressTable.getSelectionModel().getSelectedItem();
            AddressTextField.setText(String.valueOf(selected.getAddressID() ));
        }
    }

    @FXML 
    private void AddressEditOnAction(ActionEvent e) throws IOException {
        if( !AddressTextField.getText().isBlank() ){
            editID = AddressTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editAddress"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshAddressTable();
        }
    }

    @FXML 
    private void AddressNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newAddress"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshAddressTable();
    }    
    public void refreshAddressTable() {
        AddressDataObservableList.clear();
        initializeAddressTable();
    }

    @FXML 
    private void AddressDelOnAction(ActionEvent e) {
        if( !AddressTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from address where address_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(AddressTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshAddressTable();

            } catch (Exception s) {
                s.printStackTrace();
                AddressErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(AddressErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"address\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    /* --------- Actor --------- */
    @FXML
    private void ActorTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( ActorTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelActors selected = ActorTable.getSelectionModel().getSelectedItem();
            ActorTextField.setText(String.valueOf(selected.getActorID()));
        }
    }

    @FXML 
    private void ActorEditOnAction(ActionEvent e) throws IOException {
        if( !ActorTextField.getText().isBlank() ){
            editID = ActorTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editActor"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshActorTable();
        }
    }

    @FXML 
    private void ActorNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newActor"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshActorTable();
    }    
    public void refreshActorTable() {
        ActorDataObservableList.clear();
        initializeActorTable();
    }

    @FXML 
    private void ActorDelOnAction(ActionEvent e) {
        if( !ActorTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from actor where actor_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(ActorTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshActorTable();

            } catch (Exception s) {
                s.printStackTrace();
                ActorErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(ActorErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"actor\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }

    /* --------- Cities --------- */
    @FXML
    private void CityTableOnClickPressed( MouseEvent e ){

        if ( e.getClickCount() >= 1 && ( CitiesTable.getSelectionModel().getSelectedItem() != null ) ){
            ModelCities selected = CitiesTable.getSelectionModel().getSelectedItem();
            CitiesTextField.setText(String.valueOf(selected.getCitiesID()));
        }
    }

    @FXML 
    private void CitiesEditOnAction(ActionEvent e) throws IOException {
        if( !CitiesTextField.getText().isBlank() ){
            editID =  CitiesTextField.getText();
            Scene editScene = new Scene(App.loadFXML("editCity"), 500, 740);
            Stage thirdStage = new Stage(StageStyle.UNDECORATED);
            thirdStage.setScene(editScene);
            thirdStage.showAndWait();
            refreshCitiesTable();
        }
    }

    @FXML 
    private void CitiesNewOnAction(ActionEvent e) throws IOException {
        Scene editScene = new Scene(App.loadFXML("newCity"), 400, 500);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
        refreshCitiesTable();
    }    
    public void refreshCitiesTable() {
        CityDataObservableList.clear();
        initializeCitiesTable();
    }

    @FXML 
    private void CitiesDelOnAction(ActionEvent e) {
        if( !CitiesTextField.getText().isBlank() ){
            try {
                String delQuery = "delete from city where city_id=?";
                PreparedStatement pstm = App.connectDB.prepareStatement(delQuery);
                
                //pstm.setString(1, "lang_id");
                pstm.setInt(1, Integer.valueOf(CitiesTextField.getText()));
                
                pstm.executeUpdate();
                pstm.close();

                refreshCitiesTable();

            } catch (Exception s) {
                s.printStackTrace();
                CitiesErrorLabel.setText("Error: Delete action is not Permitted");
                EmptyLabelTransition(CitiesErrorLabel);

                try {
                    App.connectDB.createStatement().executeUpdate( "insert into log values(null,\""+App.email+"\",now(), \"DELETE\", 0, \"city\")");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
    }
   
    /*  --------- Initialize TableViews Methods --------- */

    private void initializeRentalsTable(){
        
        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery(" select  rental_id,rental_date,rental.film_id,rental.episode_id,rental.customer_id,email,film.title,episode.title from rental left join user on customer_id = user_id left join film on rental.film_id = film.film_id left join episode on rental.episode_id = episode.episode_id;");
        
            String title = "";
            while(Data.next()){

                if ( Data.getInt(3) == 0 ){
                    title = Data.getString(8);
                }
                else title = Data.getString(7);

                RentalDataObservableList.add(new ModelRentals( Data.getInt(1), Data.getString(2), Data.getInt(3), Data.getInt(4), Data.getInt(5), Data.getString(6) , title ));
            }
            Data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RentalsID.setCellValueFactory(new PropertyValueFactory<>("rentalID"));
        RentalsFilmID.setCellValueFactory(new PropertyValueFactory<>("filmID"));
        RentalsEpID.setCellValueFactory(new PropertyValueFactory<>("episodeID"));
        RentalsCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        RentalsDate.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        RentalsEmail.setCellValueFactory(new PropertyValueFactory<>("rentalEmail"));
        RentalsTitle.setCellValueFactory(new PropertyValueFactory<>("rentalTitle"));
        RentalsTable.setItems(RentalDataObservableList);

    }

    private void initializeFilmsTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from film");
        
            while(Data.next()){
                FilmDataObservableList.add(new ModelFilms( Data.getInt(1), Data.getString(2), Data.getString(3), Data.getInt(4), Data.getInt(5), Data.getInt(6), Data.getInt(7), Data.getString(8), Data.getString(9)));
            }
            Data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FilmsID.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        FilmsTitle.setCellValueFactory(new PropertyValueFactory<>("filmTitle"));
        FilmsDesc.setCellValueFactory(new PropertyValueFactory<>("filmDesc"));
        FilmsYear.setCellValueFactory(new PropertyValueFactory<>("filmYear"));
        FilmsSubs.setCellValueFactory(new PropertyValueFactory<>("filmSubs"));
        FilmsLang.setCellValueFactory(new PropertyValueFactory<>("filmLang"));
        FilmsLength.setCellValueFactory(new PropertyValueFactory<>("filmLength"));
        FilmsRating.setCellValueFactory(new PropertyValueFactory<>("filmRating"));
        FilmsSP.setCellValueFactory(new PropertyValueFactory<>("filmSp"));
        FilmsTable.setItems(FilmDataObservableList);

    }

    private void initializeSeriesTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from series");
        
            while(Data.next()){
                SeriesDataObservableList.add(new ModelSeries( Data.getInt(1), Data.getString(2), Data.getString(3), Data.getInt(4), Data.getInt(5), Data.getInt(6), Data.getString(7)));
            }
            Data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SeriesID.setCellValueFactory(new PropertyValueFactory<>("seriesID"));
        SeriesTitle.setCellValueFactory(new PropertyValueFactory<>("seriesTitle"));
        SeriesDesc.setCellValueFactory(new PropertyValueFactory<>("seriesDesc"));
        SeriesYear.setCellValueFactory(new PropertyValueFactory<>("seriesYear"));
        SeriesSubs.setCellValueFactory(new PropertyValueFactory<>("seriesSubs"));
        SeriesLang.setCellValueFactory(new PropertyValueFactory<>("seriesLang"));
        SeriesRating.setCellValueFactory(new PropertyValueFactory<>("seriesRating"));
        SeriesTable.setItems(SeriesDataObservableList);
    }

    private void initializeSeasonsTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select season_id,season.series_id, season_num, num_of_episodes,season.release_year, title from season left join series on season.series_id = series.series_id ;");
        
            while(Data.next()){
                //getInt getString
                SeasonsDataObservableList.add(new ModelSeasons ( Data.getInt(1), Data.getInt(2), Data.getInt(3), Data.getInt(4), Data.getInt(5), Data.getString(6)));
            }
            Data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SeasonsID.setCellValueFactory(new PropertyValueFactory<>("seasonsID"));
        SeasonsSeriesID.setCellValueFactory(new PropertyValueFactory<>("seasonsSeriesID"));
        SeasonsNum.setCellValueFactory(new PropertyValueFactory<>("seasonsNum"));
        SeasonsEpNum.setCellValueFactory(new PropertyValueFactory<>("seasonsEpNum"));
        SeasonsYear.setCellValueFactory(new PropertyValueFactory<>("seasonYear"));
        SeasonsTitle.setCellValueFactory(new PropertyValueFactory<>("seasonTitle"));
        SeasonsTable.setItems(SeasonsDataObservableList);
    }

    private void initializeEpisodesTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select episode_id,episode.title,episode.season_id,episode_num,episode_length,episode.rating,season.series_id,series.title from episode natural join season left join series on season.series_id = series.series_id;");
        
            while(Data.next()){
                EpisodesDataObservableList.add(new ModelEpisodes( Data.getInt(1), Data.getString(2), Data.getInt(3), Data.getInt(4), Data.getInt(5), Data.getString(6), Data.getInt(7), Data.getString(8) ));
            }
            Data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EpisodesID.setCellValueFactory(new PropertyValueFactory<>("episodesID"));
        EpisodesTitle.setCellValueFactory(new PropertyValueFactory<>("episodesTitle"));
        EpisodesSeasonID.setCellValueFactory(new PropertyValueFactory<>("episodesSeasonID"));
        EpisodesNum.setCellValueFactory(new PropertyValueFactory<>("episodesNum"));
        EpisodesLength.setCellValueFactory(new PropertyValueFactory<>("episodesLength"));
        EpisodesRating.setCellValueFactory(new PropertyValueFactory<>("episodesRating"));
        EpisodesSeriesID.setCellValueFactory(new PropertyValueFactory<>("episodesSeriesID"));
        EpisodesSeriesTitle.setCellValueFactory(new PropertyValueFactory<>("episodesSeriesTitle"));
        EpisodesTable.setItems(EpisodesDataObservableList);
    }

    private void initializeCategoriesTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from category");
        
            while(Data.next()){
                //getInt
                //getString
                CategDataObservableList.add(new ModelCategories( Data.getInt(1), Data.getString(2) ));
            }
            Data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CategoriesID.setCellValueFactory(new PropertyValueFactory<>("categoriesID"));
        CategoriesName.setCellValueFactory(new PropertyValueFactory<>("categoriesName"));
        CategoriesTable.setItems(CategDataObservableList);
    }

    private void initializeFilmCatTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select category_id,catname,film_id,title from film_category natural join category natural join film;");
        
            while(Data.next()){
                //getInt
                //getString
                FilmCategDataObservableList.add(new ModelFilmsCategories( Data.getInt(3), Data.getInt(1) , Data.getString(4), Data.getString(2) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FilmsCatFilmID.setCellValueFactory(new PropertyValueFactory<>("filmsCatFilmID"));
        FilmsCatCatID.setCellValueFactory(new PropertyValueFactory<>("filmsCatCatID"));
        FilmsCatCAtegoryName.setCellValueFactory(new PropertyValueFactory<>("filmsCatCategoryName"));
        FilmsCatFilmTitle.setCellValueFactory(new PropertyValueFactory<>("filmsCatFilmTitle"));
        FilmsCatTable.setItems(FilmCategDataObservableList);
    }

    private void initializeSeriesCatTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select series_id,category_id,catname,title from series_category natural join category natural join series");
        
            while(Data.next()){
                //getInt
                //getString
                SeriesCategDataObservableList.add(new ModelSeriesCategories( Data.getInt(1), Data.getInt(2) ,Data.getString(3), Data.getString(4)));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SeriesCatFilmID.setCellValueFactory(new PropertyValueFactory<>("seriesCatFilmID"));
        SeriesCatCatID.setCellValueFactory(new PropertyValueFactory<>("seriesCatCatID"));
        SeriesCatCategoryName.setCellValueFactory(new PropertyValueFactory<>("seriesCatCategoryName"));
        SeriesCatSeriesTitle.setCellValueFactory(new PropertyValueFactory<>("seriesCatSeriesTitle"));
        SeriesCatTable.setItems(SeriesCategDataObservableList);
    }

    private void initializeActorTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from actor");
        
            while(Data.next()){
                //getInt
                //getString
                ActorDataObservableList.add(new ModelActors( Data.getInt(1), Data.getString(2), Data.getString(3) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ActorID.setCellValueFactory(new PropertyValueFactory<>("actorID"));
        ActorFName.setCellValueFactory(new PropertyValueFactory<>("actorFName"));
        ActorLName.setCellValueFactory(new PropertyValueFactory<>("actorLName"));
        ActorTable.setItems(ActorDataObservableList);
    }

    private void initializeFilmActorsTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select actor_id, film_id, title ,first_name, last_name from film_actor natural join film natural join actor");
        
            while(Data.next()){
                //getInt
                //getString
                FilmActorDataObservableList.add(new ModelFilmsActors( Data.getInt(1), Data.getInt(2), Data.getString(3) , Data.getString(4)+" "+Data.getString(5) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FilmsActorsActorID.setCellValueFactory(new PropertyValueFactory<>("filmsActorsActorID"));
        FilmsActorsFilmID.setCellValueFactory(new PropertyValueFactory<>("filmsActorsFilmID"));
        FilmsActorsActorsName.setCellValueFactory(new PropertyValueFactory<>("filmsActorsActorName"));
        FilmsActorsFilmTitle.setCellValueFactory(new PropertyValueFactory<>("filmsActorsFilmTitle"));
        FilmsActorsTable.setItems(FilmActorDataObservableList);
    }

    private void initializeSeriesActorsTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select actor_id,first_name,last_name,in_season,season_num,series.series_id,title  from season_actor natural join actor inner join season on in_season=season_id left join series on season.series_id = series.series_id ;");
        
            while(Data.next()){
                //getInt
                //getString
                SeriesActorDataObservableList.add(new ModelSeriesActors( Data.getInt(1), Data.getString(2)+" "+Data.getString(3), Data.getInt(4), Data.getInt(5) , Data.getInt(6), Data.getString(7) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SeriesActorsActorID.setCellValueFactory(new PropertyValueFactory<>("seriesActorsActorID"));
        SeriesActorsActorName.setCellValueFactory(new PropertyValueFactory<>("seriesACtorsActorName"));
        SeriesActorsSeasonID.setCellValueFactory(new PropertyValueFactory<>("seriesActorsSeasonID"));
        SeriesActorsSeasonNum.setCellValueFactory(new PropertyValueFactory<>("seriesActorsSeasonNum"));
        SeriesActorsSeriesID.setCellValueFactory(new PropertyValueFactory<>("seriesActorsSeriesID"));
        SeriesActorsSeriesTitle.setCellValueFactory(new PropertyValueFactory<>("seriesActorsSeriesTitle"));
        SeriesActorsTable.setItems(SeriesActorDataObservableList);
    }

    private void initializeCitiesTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select city_id,city,country_id,country from city natural join country");
        
            while(Data.next()){
                //getInt
                //getString
                CityDataObservableList.add(new ModelCities( Data.getInt(1), Data.getString(2), Data.getInt(3), Data.getString(4) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CitiesID.setCellValueFactory(new PropertyValueFactory<>("citiesID"));
        CitiesName.setCellValueFactory(new PropertyValueFactory<>("citiesName"));
        CitiesCountryID.setCellValueFactory(new PropertyValueFactory<>("citiesCountryID"));
        CountryName.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        CitiesTable.setItems(CityDataObservableList);
    }

    private void initializeCountriesTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from country");
        
            while(Data.next()){
                //getInt
                //getString
                CountryDataObservableList.add(new ModelCountries( Data.getInt(1), Data.getString(2) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CountriesID.setCellValueFactory(new PropertyValueFactory<>("countriesID"));
        CountriesName.setCellValueFactory(new PropertyValueFactory<>("countriesName"));
        CountriesTable.setItems(CountryDataObservableList);
    }

    private void initializeAddressTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from address natural join city");
        
            while(Data.next()){
                //getInt
                //getString
                AddressDataObservableList.add(new ModelAddresses( Data.getInt(2), Data.getString(3), Data.getString(4), Data.getInt(1), Data.getString(5), Data.getString(6), Data.getString("city") ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AddressID.setCellValueFactory(new PropertyValueFactory<>("addressID"));
        AddressName.setCellValueFactory(new PropertyValueFactory<>("addressName"));
        AddressDistrict.setCellValueFactory(new PropertyValueFactory<>("addressDistrict"));
        AddressCityID.setCellValueFactory(new PropertyValueFactory<>("addressCityID"));
        AddressPostal.setCellValueFactory(new PropertyValueFactory<>("addressPostal"));
        AddressPhone.setCellValueFactory(new PropertyValueFactory<>("addressPhone"));
        AddressCityName.setCellValueFactory(new PropertyValueFactory<>("addressCityName"));
        AddressTable.setItems(AddressDataObservableList);
    }

    private void initializeLangTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select * from lang");
        
            while(Data.next()){
                //getInt
                //getString
                LangDataObservableList.add(new ModelLangs( Data.getInt(1), Data.getString(2) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LangsID.setCellValueFactory(new PropertyValueFactory<>("langsID"));
        LangsName.setCellValueFactory(new PropertyValueFactory<>("langsName"));
        LangsTable.setItems(LangDataObservableList);
    }

    private void initializeCustTable(){

        try {
            Statement statement = App.connectDB.createStatement();
            ResultSet Data = statement.executeQuery("select customer.customer_id,address_id,cust_type,first_name,last_name,email,create_date,active,pending from customer inner join user on user_id=customer_id;");
        
            while(Data.next()){
                
                CustDataObservableList.add(new ModelCustomer( Data.getInt(1), Data.getInt(2), Data.getString(3), Data.getString(4), Data.getString(5), Data.getString(6), Data.getString(7), Data.getInt(8), Data.getInt(9) ));
            }
            Data.close();
            if ( statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomersID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        CustomersAdd.setCellValueFactory(new PropertyValueFactory<>("addressID"));
        CustomersType.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        CustomersFName.setCellValueFactory(new PropertyValueFactory<>("customerFName"));
        CustomersLName.setCellValueFactory(new PropertyValueFactory<>("customerLName"));
        CustomersEmail.setCellValueFactory(new PropertyValueFactory<>("custEmail"));
        CustomersYear.setCellValueFactory(new PropertyValueFactory<>("customerYear"));
        CustomersActive.setCellValueFactory(new PropertyValueFactory<>("custActive"));
        CustomersPending.setCellValueFactory(new PropertyValueFactory<>("pending"));
        CustomersTable.setItems(CustDataObservableList);
    }
 
//--- The end at last ---//
}
