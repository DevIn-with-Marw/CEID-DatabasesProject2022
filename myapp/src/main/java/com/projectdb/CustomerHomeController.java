package com.projectdb;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ResourceBundle;

import com.projectdb.model.*;
import com.projectdb.util.DBUtil;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;

public class CustomerHomeController {

    private AnchorPane currentview= null;
    public static int usernowid =-1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //BASIC COMPONENTS//
    @FXML 
    private AnchorPane CustomerUIBase;

    @FXML 
    private BorderPane CustomerUIAppPane;

    @FXML
    private ImageView CustomerUIAppLogo;

    @FXML
    private Label CustomerUI_hellouser_label;

    @FXML
    private ImageView CustomerUI_avatar_image;

    @FXML
    private AnchorPane CustomerUI_views_Pane;

    @FXML
    private AnchorPane CustomerUI_ButtonGroup1_Pane;

        //BASIC BUTTONS//
    @FXML
    private Button CustomerUI_Profile_Button;

    @FXML
    private Button CustomerUI_Library_Button;

    @FXML
    private Button CustomerUI_MMS_Button;

    @FXML
    private Button CustomerUI_Rental_Button;

    @FXML
    private AnchorPane CustomerUI_welcome_pane;

    @FXML
    private Button CustomerUI_LogoutButton;

    @FXML
    private Button CustomerUI_ExitButton;

    //LIBRARY VIEW//
    @FXML 
    private AnchorPane CustomerUI_Library_Pane;

    @FXML
    private TabPane CustomerUI_Library_TabPane;

    @FXML
    private Tab CustomerUI_Library_MoviesTab;

    @FXML
    private Tab CustomerUI_Library_SeriesTab;

        //MOVIES TAB//

    @FXML
    private AnchorPane CustomerUI_Library_MoviesPane;

    @FXML
    private ScrollPane CustomerUI_Library_Movies_ScrollPane;

    @FXML
    private TableView<Film> CustomerUI_Library_MoviesTable;

    @FXML
    private TableColumn<Film, String> CustomerUI_Library_Movies_FDescCol;

    @FXML
    private TableColumn<Film, Integer> CustomerUI_Library_Movies_FlengthCol;

    @FXML
    private TableColumn<Film, String> CustomerUI_Library_Movies_LangCol;

    @FXML
    private TableColumn<Film, String> CustomerUI_Library_Movies_OrigLangCol;

    @FXML
    private TableColumn<Film, String> CustomerUI_Library_Movies_RatingCol;

    @FXML
    private TableColumn<Film, Integer> CustomerUI_Library_Movies_RelYearCol;

    @FXML
    private TableColumn<Film, String> CustomerUI_Library_Movies_SpFeatCol;

    @FXML
    private TableColumn<Film, String> CustomerUI_Library_Movies_TitleCol;

    @FXML 
    private TableColumn<Film, String> CustomerUI_Library_Movies_CategoryCol;
    
    @FXML 
    private TableColumn<Film, String> CustomerUI_Library_Movies_ActorsCol;
    
    @FXML
    private Button CustomerUI_Library_Movies_RentButton;

        //SERIES TAB//

    @FXML
    private AnchorPane CustomerUI_Library_SeriesPane;

    @FXML 
    private ScrollPane customerUI_Library_SerieScrollPane;
    
    @FXML 
    private TableView<Series> CustomerUI_Library_SeriesTable;

    @FXML
    private TableColumn<Series,String> CustomerUI_Library_Series_TitleCol;

    @FXML
    private TableColumn<Series,String> CustomerUI_Library_Series_DescCol;

    @FXML
    private TableColumn<Series,Integer> CustomerUI_Library_Series_RelYEarCol;

    @FXML
    private TableColumn<Series,String> CustomerUI_Library_Series_LangCol;

    @FXML
    private TableColumn<Series,String> CustomerUI_Library_Series_OrigLangCol;

    @FXML
    private TableColumn<Series,String> CustomerUI_Library_Series_RatingCol;

    @FXML 
    private TableColumn<Series,String> CustomerUI_Library_Series_CategoryCol;

    @FXML
    private Button CustomerUI_Library_Series_ViewSeasons_Button;

        //SEASON VIEW

    @FXML 
    private AnchorPane CustomerUI_Library_Seasons_Pane;

    @FXML 
    private TableView<Season> CustomerUI_Library_Seasons_Table;

    @FXML
    private TableColumn<Season,Integer> CustomerUI_Library_Season_SeasNoCol;

    @FXML
    private TableColumn<Season,Integer> CustomerUI_Library_Season_SeasEpNumCol;

    @FXML
    private TableColumn<Season,Integer> CustomerUI_Library_Season_SeasRelYearCol;

    @FXML
    private TableColumn<Season,String> CustomerUI_Library_Season_ActorsCol;

    @FXML
    private Button CustomerUI_Library_Seasons_Back_Button;

    @FXML
    private Button CustomerUI_Library_Seasons_ViewEpisodes_Button;

        //EPISODES VIEW
    @FXML
    private AnchorPane CustomerUI_Library_Episodes_Pane;

    @FXML
    private TableView<Episode> CustomerUI_Library_Episodes_Table;

    @FXML
    private TableColumn<Episode, String> CustomerUI_Library_Episode_EpTitleCol; 

    @FXML
    private TableColumn<Episode, Integer> CustomerUI_Library_Episode_EpNoCol;
    
    @FXML
    private TableColumn<Episode, Integer> CustomerUI_Library_Episode_EpLengthCol;

    @FXML
    private TableColumn<Episode, String> CustomerUI_Library_Episode_EpRatingCol;

    @FXML 
    private Button CustomerUI_Library_Episodes_Back_Button;

    @FXML
    private Button CustomerUI_Library_Episodes_Rent_Button;

    //------ MY MOVIES/SERIES VIEW ------//
    @FXML
    private AnchorPane CustomerUI_MMS_Pane;

    @FXML
    private TabPane CustomerUI_MMS_TabPane;

        //MOVIES TAB//
    @FXML 
    private Tab CustomerUI_MMS_MoviesTab;

    @FXML 
    private AnchorPane CustomerUI_MMS_MovieRentals_Pane;

    @FXML
    private ScrollPane CustomerUI_MMS_Movierentals_ScrollPane;

    @FXML
    private TableView<RentalinjFilm> CustomerUI_MMS_MovieRentalsTable;

    @FXML
    private TableColumn<RentalinjFilm, String> CustomerUI_MMS_MovieRentals_RentalDateCol;

    @FXML
    private TableColumn<RentalinjFilm, String> CustomerUI_MMS_MovieRentals_MovieTitleCol;

    @FXML
    private TableColumn<RentalinjFilm, Float> CustomerUI_MMS_MovieRentals_PriceCol;

    @FXML
    private TableColumn<RentalinjFilm, Integer> CustomerUI_MMS_MovieRentals_MovieLengthCol;

        //EPISODES TAB//
    @FXML
    private Tab CustomerUI_MMS_EpisodesTab;

    @FXML
    private AnchorPane CustomerUI_MMS_EpisodeRentals_Pane;

    @FXML
    private ScrollPane CustomerUI_MMS_EpisodeRentals_ScrollPane;

    @FXML
    private TableView<RentalinjEpisodeinjSeason> CustomerUI_MMS_EpisodeRentals_Table;

    @FXML
    private TableColumn<RentalinjEpisodeinjSeason, Float> CustomerUI_MMS_EpisodeRentals_PriceCol;

    @FXML
    private TableColumn<RentalinjEpisodeinjSeason, String> CustomerUI_MMS_EpisodeRentals_RentaldateCol;

    @FXML
    private TableColumn<RentalinjEpisodeinjSeason, String> CustomerUI_MMS_EpisodeRentals_EpisodeTitleCol;

    @FXML
    private TableColumn<RentalinjEpisodeinjSeason, Integer> CustomerUI_MMS_EpisodeRentals_EpisodeLengthCol;

    @FXML
    private TableColumn<RentalinjEpisodeinjSeason, Integer> CustomerUI_MMS_EpisodeRentals_EpisodeNumCol;

    @FXML
    private TableColumn<RentalinjEpisodeinjSeason, Integer> CustomerUI_MMS_EpisodeRentals_SeasonNumCol;

    @FXML
    private TableColumn<RentalinjEpisodeinjSeason, String> CustomerUI_MMS_EpisodeRentals_SeriesTitleCol;
    
    @FXML
    private Button CustomerUI_MMS_getinfoButton;

    //------ CURRENT RENTAL VIEW ------//
    @FXML
    private AnchorPane CustomerUI_CurrRental_Pane;

    @FXML
    private TableView<CheckoutItem> CustomerUI_CurrRental_Checkout_Table;

    @FXML
    private TableColumn<CheckoutItem, String> CustomerUI_CurreRental_Checkout_TypeCol;

    @FXML
    private TableColumn<CheckoutItem, String> CustomerUI_CurrRental_Checkout_TitleCol;

    @FXML
    private TableColumn<CheckoutItem, Float> CustomerUI_CurrRental_Checkout_PriceCol;
    
    @FXML
    private Button customerUI_CurrRental_showInfoButton;

    @FXML 
    private Button CustomerUI_CurrRental_RemoveItemButton;

    @FXML
    private Button CustomerUI_CurrRental_CheckoutButton;

    @FXML 
    private Label CustomerUI_CurrRental_Info_CustTypeLabel;

    @FXML
    private Label CustomerUI_CurrRental_Info_CustTypeValue_Label;

    @FXML
    private Label CustomerUI_CurrRental_Info_PriceLabel;

    @FXML
    private Label CustomerUI_CurrRental_Info_PriceValue_Label;

    @FXML
    private Label CustomerUI_CurrRental_Info_DiscountLabel;
    
    @FXML
    private Label customerUI_Info_ErrorReportLabel;

    @FXML
    private Label CustomerUI_CurrRental_InvalidSub_Label;


    //-------- GENERAL --------//
    @FXML
    private void initialize() throws Exception{
        //resolve label hellouser
        CustomerUI_hellouser_label.setText("Hello "+App.email+"!");

        //save @user_now variable in java
        getcurruser();
        
        //initialize views
        initializeLibrary();
        initializeMMS();

        CustomerUI_Library_Pane.setVisible(true);
        currentview = CustomerUI_Library_Pane;
    
    }

    /* this function is probably irrelevant in this version */
    public void getcurruser() throws Exception
    {
        Statement stm = null;
        ResultSet usernow_var;      //if java uses it, make it global
        try {
            stm = App.getConnection().createStatement();
            usernow_var = stm.executeQuery("SELECT @user_now");
            if (usernow_var.next())
            {
                usernowid = usernow_var.getInt("@user_now");
            }
            usernow_var.close();
        }
        catch(Exception e)
        {
            System.out.println("Error while fetching @user_now variable"+e);
            throw e;
        }
    }

    public void logout() throws IOException
    {
        App.scene = new Scene(App.loadFXML("LoginPage"), 600, 400);
        Stage primaryStage = new Stage(StageStyle.UNDECORATED);
        primaryStage.setScene(App.scene);
        primaryStage.show();
        Stage currentStage = (Stage) CustomerUI_hellouser_label.getScene().getWindow();
        currentStage.close();
    }

    public void exit()
    {
        Stage currentstage = (Stage) CustomerUI_hellouser_label.getScene().getWindow();
        currentstage.close();
    }

    //------ PROFILE SETINGS ------//

    @FXML 
    private void editProfile() throws IOException
    {
        Scene editScene = new Scene(App.loadFXML("editCustomerProfile"), 500, 740);
        Stage thirdStage = new Stage(StageStyle.UNDECORATED);
        thirdStage.setScene(editScene);
        thirdStage.showAndWait();
    }
    
    //------ LIBRARY VIEW ------//
    
    private void initializeLibrary() throws Exception
    {
        CustomerUI_Library_Movies_TitleCol.setCellValueFactory(cellData -> cellData.getValue().getFTitleProperty());
        CustomerUI_Library_Movies_FDescCol.setCellValueFactory(cellData -> cellData.getValue().getFDescriptionProperty());
        CustomerUI_Library_Movies_RelYearCol.setCellValueFactory(cellData ->cellData.getValue().getFReleaseYearProperty().asObject());
        CustomerUI_Library_Movies_LangCol.setCellValueFactory(cellData -> cellData.getValue().getFLanguageProperty());
        CustomerUI_Library_Movies_OrigLangCol.setCellValueFactory(cellData -> cellData.getValue().getFOriginalLanguageProperty());
        CustomerUI_Library_Movies_FlengthCol.setCellValueFactory(cellData -> cellData.getValue().getFLengthProperty().asObject());
        CustomerUI_Library_Movies_RatingCol.setCellValueFactory(cellData -> cellData.getValue().getFRatingProperty());
        CustomerUI_Library_Movies_SpFeatCol.setCellValueFactory(cellData -> cellData.getValue().getFSpecialfeaturesProperty());      
        CustomerUI_Library_Movies_CategoryCol.setCellValueFactory(cellData -> cellData.getValue().getFCategoryProperty());
        CustomerUI_Library_Movies_ActorsCol.setCellValueFactory(cellData -> cellData.getValue().getFActorsProperty());

        CustomerUI_Library_Series_TitleCol.setCellValueFactory(cellData -> cellData.getValue().getSTitleProperty());
        CustomerUI_Library_Series_DescCol.setCellValueFactory(cellData -> cellData.getValue().getSDescriptionProperty());
        CustomerUI_Library_Series_RelYEarCol.setCellValueFactory(cellData -> cellData.getValue().getSReleaseYearProperty().asObject());
        CustomerUI_Library_Series_LangCol.setCellValueFactory(cellData -> cellData.getValue().getSLanguageProperty());
        CustomerUI_Library_Series_OrigLangCol.setCellValueFactory(cellData -> cellData.getValue().getSOriginalLanguageProperty());
        CustomerUI_Library_Series_RatingCol.setCellValueFactory(cellData -> cellData.getValue().getSRatingProperty());
        CustomerUI_Library_Series_CategoryCol.setCellValueFactory(cellData -> cellData.getValue().getSCategoryProperty());

        ObservableList<Film> filmList = FilmDAO.getAllRecords("select * from film inner join lang on lang.lang_id = film.language_id;");
        ObservableList<Series> seriesList = SeriesDAO.getAllRecords();
        fillMTable(filmList);
        fillStable(seriesList);
    }
    
    private void fillStable(ObservableList<Series> seriesList) {
        CustomerUI_Library_SeriesTable.setItems(seriesList);
    }

    private void fillMTable(ObservableList<Film> filmList) {
        CustomerUI_Library_MoviesTable.setItems(filmList);

    }

    @FXML
    public void showLibrary()
    {
        if (currentview != null) currentview.setVisible(false);
        CustomerUI_Library_Pane.setVisible(true);
        currentview = CustomerUI_Library_Pane;
    }

    @FXML 
    private void back2series() throws Exception
    {
        CustomerUI_Library_Seasons_Pane.setVisible(false);
        CustomerUI_Library_Pane.setVisible(true);
        currentview = CustomerUI_Library_Pane;
        //tab selection
        CustomerUI_Library_TabPane.getSelectionModel().select(1);
        //row selection 
        int seridtoselect = CustomerUI_Library_Seasons_Table.getSelectionModel().getSelectedItem().getSeas_seriesidProperty().get();
        ResultSet rs = DBUtil.DBexecute(App.getConnection(), "select series_id from series order by series_id;");
        int index=-1;
        while (rs.next())
        {
            if (rs.getInt("series_id")==seridtoselect)
            {
                index = rs.getRow()-1;
            }
        }
        CustomerUI_Library_SeriesTable.getSelectionModel().select(index);
        rs.close();       

    }
    
    @FXML
    private void viewSeasons() throws Exception
    {
        Series selected = new Series();
       //Boolean default_selection = false;          //@line 499
        int season_to_select_id = -1;

        //RESOLVE SERIES WHOSE SEASONS WILL APPEAR
        if (currentview == CustomerUI_Library_Pane) //series to seasons
        {
            selected = CustomerUI_Library_SeriesTable.getSelectionModel().getSelectedItem();
        }
        else if (currentview == CustomerUI_Library_Episodes_Pane)    //episodes to season
        {
            /*
            * route 1: 
            * MMS -> Episodes -> showinfo() (selection happened automatically) -> Back2Seasons
            * OR
            * route 2: 
            * Library -> Series -> Seasons -> Episodes + user_selected episode 
            * Both routes result in showing seasons and selecting the one of the selected episode
            * route 3 = route 2 but user did not select an episode 
            * route 3 results only in showing the seasons.
            */

            //user selected episode
            //get season id from the episode (applies for routes 1 + 2)
            Episode selected_episode = CustomerUI_Library_Episodes_Table.getSelectionModel().getSelectedItem(); //MMS->Episodes->showinfo() or 
            
            //no selection made by the user
            //system selects pathetically, simply to resolve the seasons to show 
            //(applies for route 3)
            if (/*default_selection = */(selected_episode == null)) 
            {
                CustomerUI_Library_Episodes_Table.getSelectionModel().select(1);
                selected_episode = CustomerUI_Library_Episodes_Table.getSelectionModel().getSelectedItem();
            }
           
            //resolve season to select in season view
            season_to_select_id = selected_episode.getEpisodeSeasonId();          

            //resolve the series whose seasons will appear (new series is created and destroyed later)
                //get series id from the season id
            ResultSet selected_seriesidSet;
            selected_seriesidSet = DBUtil.DBexecute(App.getConnection(), "select series_id from season where season_id ="+selected_episode.getEpisodeSeasonId()+";");
            int selected_seriesid=-1;   
            if (selected_seriesidSet.next())    //defensive programming, we only need ResultSet.rowPointer++;
            {
                selected_seriesid = selected_seriesidSet.getInt("series_id");
            }
            selected_seriesidSet.close();
            //we  need the id to fetch the seasons of the resolved series 
            selected.setSeriesId(selected_seriesid);
            
        }
        
        //SHOW SEASONS OF THE RESOLVED SERIES 
        //if selection is not null (with the initialized values)
        //true only if currentview is one of the above: condition is equal to currentview != null

        if (selected.getSidProperty() != null ) //extra defensive programming: 
                                               //any error leading to the condition being false 
                                               //has been detected during resolving
        {
            currentview.setVisible(false);
            CustomerUI_Library_Seasons_Pane.setVisible(true);
            
            currentview = CustomerUI_Library_Seasons_Pane;

            CustomerUI_Library_Season_SeasNoCol.setCellValueFactory(cellData -> cellData.getValue().getSeas_noProperty().asObject());
            CustomerUI_Library_Season_SeasEpNumCol.setCellValueFactory(cellData -> cellData.getValue().getSeas_episodenumProperty().asObject());
            CustomerUI_Library_Season_SeasRelYearCol.setCellValueFactory(cellData -> cellData.getValue().getSeas_releaseyearProperty().asObject());
            CustomerUI_Library_Season_ActorsCol.setCellValueFactory(cellData -> cellData.getValue().getSeas_actorsProperty());

            ObservableList<Season> seasonsList = SeasonDAO.getAllRecords(selected);
            FillSeasTable(seasonsList);

            //select row
            //if (!default_selection)
            {
                ResultSet rs;
                rs = DBUtil.DBexecute(App.getConnection(), "select season_id from season where series_id="+selected.getSeriesId()+" ORDER BY season_id;");
                int index = -1;

                while(rs.next())
                {
                    if (rs.getInt("season_id")==season_to_select_id)
                    {
                        index = rs.getRow()-1;
                        break;
                    }
                }
                rs.close();
                CustomerUI_Library_Seasons_Table.getSelectionModel().select(index);
                
            }
        }
        else {/*set visible: label indicating that no selection has been made*/}

        selected = null;    //selected will be destroyed by the java garbage collector
    }

    private void FillSeasTable(ObservableList<Season> seasonsList) {
        CustomerUI_Library_Seasons_Table.setItems(seasonsList);
    }

    @FXML 
    private void viewEpisodes() throws Exception
    {
        Season selected = null;

        if (currentview == CustomerUI_Library_Seasons_Pane)
        {
            selected = CustomerUI_Library_Seasons_Table.getSelectionModel().getSelectedItem();
        }
        else if (currentview == CustomerUI_MMS_Pane)
        {
            selected = CustomerUI_MMS_EpisodeRentals_Table.getSelectionModel().getSelectedItem().getSeason();
        }
        else if (currentview == CustomerUI_CurrRental_Pane)
        {
            int idtoselect = CustomerUI_CurrRental_Checkout_Table.getSelectionModel().getSelectedItem().getEpisode().getEpisodeSeasonId();
            
            ResultSet rs;
            rs = DBUtil.DBexecute(App.getConnection(), "select season_id from season ORDER BY season_id;");
            int index = -1;
            while(rs.next())
            {
                if (rs.getInt("season_id")==idtoselect)
                {
                    index = rs.getRow()-1;
                    break;
                }
            }
            rs.close();
            CustomerUI_Library_Seasons_Table.getSelectionModel().select(index);
            selected = CustomerUI_Library_Seasons_Table.getSelectionModel().getSelectedItem();
        }

        if (selected != null)
        {
            currentview.setVisible(false);
            CustomerUI_Library_Episodes_Pane.setVisible(true);
            currentview = CustomerUI_Library_Episodes_Pane;

            CustomerUI_Library_Episode_EpNoCol.setCellValueFactory(cellData -> cellData.getValue().getEp_numProperty().asObject());
            CustomerUI_Library_Episode_EpTitleCol.setCellValueFactory(cellData -> cellData.getValue().getEp_titleProperty());
            CustomerUI_Library_Episode_EpLengthCol.setCellValueFactory(cellData -> cellData.getValue().getEp_lengthProperty().asObject());
            CustomerUI_Library_Episode_EpRatingCol.setCellValueFactory(cellData -> cellData.getValue().getEp_ratingProperty());
        
        ObservableList<Episode> episodesList = EpisodeDAO.getAllRecords(selected);
        FillEpisodesTable(episodesList);
        }
        //else {/*no selection has been made*/}

        

    }
    
    private void FillEpisodesTable(ObservableList<Episode> episodesList) {
        CustomerUI_Library_Episodes_Table.setItems(episodesList);
    }

    @FXML
    private void rent() throws Exception
    {
        //get the customer type 
        String customer_type = "";
        String getct = "select cust_type from customer where customer_id = @user_now;";
        ResultSet rs = DBUtil.DBexecute(App.getConnection(), getct);
        if (rs.next()) customer_type = rs.getString("cust_type");
        rs.close();

        if (customer_type.isEmpty()) 
        {
            System.out.println("Unkown customer type. Rental is not registered");
            return;
        }

        //create checkout item
        CheckoutItem torent = new CheckoutItem();
        
        if (currentview == CustomerUI_Library_Pane)
        { 
            if (customer_type.equals("SERIES ONLY")) 
            {
                //show  subscription label
                CustomerUI_CurrRental_InvalidSub_Label.setVisible(true);
                PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(5)
                );
                visiblePause.setOnFinished(
                        event -> CustomerUI_CurrRental_InvalidSub_Label.setVisible(false)
                );
                visiblePause.play();
                //log update
                String logUpdate = "insert into log values(NULL, \""+App.email+"\", NOW(), \"INSERT\", 0,  \"RENTAL\");";
                DBUtil.DBexecuteQuery(App.getConnection(), logUpdate);
                return;
            }

            Film tempselection = CustomerUI_Library_MoviesTable.getSelectionModel().getSelectedItem();
            if (tempselection != null)
            {
                torent.setFilm(tempselection);
                torent.setEpisode(null);
                torent.setCheckoutItemType("film");
                torent.setCheckoutItemTitle(tempselection.getFilmTitle());
            }
            
        }
        else if (currentview == CustomerUI_Library_Episodes_Pane && CustomerUI_Library_Episodes_Table.getSelectionModel().getSelectedItem() != null)
        {
            if (customer_type.equals("FILMS ONLY")) 
            {
                //show invalid subscription label
                CustomerUI_CurrRental_InvalidSub_Label.setVisible(true);
                PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(5)
                );
                visiblePause.setOnFinished(
                        event -> CustomerUI_CurrRental_InvalidSub_Label.setVisible(false)
                );
                visiblePause.play();
                
                //log update
                String logUpdate = "INSERT INTO log VALUES(NULL, \""+App.email+"\", NOW(), \"INSERT\", 0,  \"RENTAL\");";
                DBUtil.DBexecuteQuery(App.getConnection(), logUpdate);
                return;
            }

            Episode tempselection = CustomerUI_Library_Episodes_Table.getSelectionModel().getSelectedItem();
            if (tempselection != null)
            {
                torent.setFilm(null);
                torent.setEpisode(tempselection);
                torent.setCheckoutItemType("episode");
                torent.setCheckoutItemTitle(tempselection.getEpisodeTitle());
            }
        }
        else return;

        //resolve checkout item price based on @user_now.cust_type + product_type -> Note 16
        torent.setCheckoutItemPrice();

        CustomerUI_CurrRental_Checkout_TitleCol.setCellValueFactory(cellData -> cellData.getValue().getChIt_titleProperty());
        CustomerUI_CurreRental_Checkout_TypeCol.setCellValueFactory(cellData -> cellData.getValue().getChIt_typeProperty());
        CustomerUI_CurrRental_Checkout_PriceCol.setCellValueFactory(cellData -> cellData.getValue().getChIt_priceProperty().asObject());

        CustomerUI_CurrRental_Checkout_Table.getItems().add(torent);
    }

    
    //------ MY MOVIES/SERIES VIEW ------//
    
    private void initializeMMS() throws Exception
    {
        CustomerUI_MMS_MovieRentals_PriceCol.setCellValueFactory(cellData -> cellData.getValue().getRF_priceProperty().asObject());
        CustomerUI_MMS_MovieRentals_RentalDateCol.setCellValueFactory(cellData -> cellData.getValue().getRental().getR_datetimeProperty());
        CustomerUI_MMS_MovieRentals_MovieTitleCol.setCellValueFactory(cellData -> cellData.getValue().getFilm().getFTitleProperty());
        CustomerUI_MMS_MovieRentals_MovieLengthCol.setCellValueFactory(cellData -> cellData.getValue().getFilm().getFLengthProperty().asObject());
        
        ObservableList<RentalinjFilm> rentalsbycustList = RentalinjFilmDAO.getAllRecords("select * from rental inner join film on rental.film_id = film.film_id inner join payment on payment.rental_id = rental.rental_id where rental.customer_id=@user_now");
        FillRentalsinjFilmTable(rentalsbycustList);

        //EpisodeRentalColumns + call DAO
        CustomerUI_MMS_EpisodeRentals_PriceCol.setCellValueFactory(cellData -> cellData.getValue().getRES_priceProperty().asObject());
        CustomerUI_MMS_EpisodeRentals_RentaldateCol.setCellValueFactory(cellData -> cellData.getValue().getRental().getR_datetimeProperty());
        CustomerUI_MMS_EpisodeRentals_EpisodeTitleCol.setCellValueFactory(cellData -> cellData.getValue().getEpisode().getEp_titleProperty());
        CustomerUI_MMS_EpisodeRentals_EpisodeNumCol.setCellValueFactory(cellData -> cellData.getValue().getEpisode().getEp_numProperty().asObject());
        CustomerUI_MMS_EpisodeRentals_EpisodeLengthCol.setCellValueFactory(cellData -> cellData.getValue().getEpisode().getEp_lengthProperty().asObject());
        CustomerUI_MMS_EpisodeRentals_SeasonNumCol.setCellValueFactory(cellData -> cellData.getValue().getSeason().getSeas_noProperty().asObject());
        CustomerUI_MMS_EpisodeRentals_SeriesTitleCol.setCellValueFactory(cellData -> cellData.getValue().getRES_SeriesTitleProperty());

        String joinQuery = "select * from rental inner join episode on rental.episode_id=episode.episode_id inner join season on episode.season_id = season.season_id inner join series on series.series_id = season.series_id inner join payment on payment.rental_id = rental.rental_id where rental.customer_id = @user_now;";
        ObservableList<RentalinjEpisodeinjSeason> joinedObjList = RentalinjEpisodeinjSeasonDAO.getAllRecords(joinQuery);
        FillJoinedObjTable(joinedObjList);
    }

    private void FillRentalsinjFilmTable(ObservableList<RentalinjFilm> rentalsbycustList) {
        CustomerUI_MMS_MovieRentalsTable.setItems(rentalsbycustList);
    }

    private void FillJoinedObjTable(ObservableList<RentalinjEpisodeinjSeason> joinedObjList) {
        CustomerUI_MMS_EpisodeRentals_Table.setItems(joinedObjList);
    }

    
    @FXML
    private void showMMS()
    {
        if (currentview != null) currentview.setVisible(false);
        CustomerUI_MMS_Pane.setVisible(true);
        currentview = CustomerUI_MMS_Pane;
    }
    

    //----- CURRENT RENTAL VIEW -----//
    @FXML
    private void showCurrentRental() throws Exception
    {
        if (currentview != null) currentview.setVisible(false);
        CustomerUI_CurrRental_Pane.setVisible(true);
        currentview = CustomerUI_CurrRental_Pane; 

        CustomerUI_CurrRental_Info_DiscountLabel.setText("");
        showCustomerType();  
        calculateTotal();
    }

    private void showCustomerType() throws Exception
    {
        String type = "-";
        ResultSet rs; 
        rs = DBUtil.DBexecute(App.getConnection(), "select cust_type from customer where customer_id = @user_now;");
        if (rs.next())
        {
            type = rs.getString("cust_type");
        }
        rs.close();
        
        CustomerUI_CurrRental_Info_CustTypeValue_Label.setText(type); 
    }
    
    @FXML
    private void removeCheckoutItem() throws Exception
    {
        CheckoutItem selected = CustomerUI_CurrRental_Checkout_Table.getSelectionModel().getSelectedItem();

        if (selected!=null)
        {
            CustomerUI_CurrRental_Checkout_Table.getItems().remove(selected);
            calculateTotal();
        }
    }

    private void calculateTotal() throws Exception
    {
        /* 1. call procedure3_2 (how many rentals made today) 
         * 2. add their numbers to the customers' (all) rentals
         * 3. if the result % 3 = 0 discount granted 
        */
        ResultSet curr_email = DBUtil.DBexecute(App.connectDB, "select email from user where user_id=@user_now;");
        String email_now = "";
        if (curr_email.next()) 
            email_now = curr_email.getString("email");
        curr_email.close();

        int count=0;
        String getrentstoday = "call zhtoumeno3_2(?,?,?)";

        CallableStatement stmt= App.connectDB.prepareCall(getrentstoday);
        stmt.setString(1, email_now);
        Date sqlDate = new Date(System.currentTimeMillis());
        stmt.setDate(2, sqlDate);
        stmt.registerOutParameter(3, java.sql.Types.INTEGER);

        stmt.execute();
        count = stmt.getInt(3);
        stmt.close();
        
        float total = 0;        
        
        for (CheckoutItem it: CustomerUI_CurrRental_Checkout_Table.getItems())
        {
            count++;
            if (count%3==0) 
            {
                total += it.getCheckoutItemPrice()/2;
                CustomerUI_CurrRental_Info_DiscountLabel.setText("Discount Granted!");
            }
            else {
                total +=   it.getCheckoutItemPrice();
                CustomerUI_CurrRental_Info_DiscountLabel.setText("");
            }
            
        }
        
        CustomerUI_CurrRental_Info_PriceValue_Label.setText(String.format("%.2f", total)+"€");
    }
    
    @FXML
    private void checkout() throws Exception
    {
        for (CheckoutItem it: CustomerUI_CurrRental_Checkout_Table.getItems())
        {
            int rental_to_pay=-1;
            App.connectDB.setAutoCommit(false);
            if (it.getFilm() == null)   //episode rental
            {
                try {
                    DBUtil.DBexecuteQuery(App.getConnection(), "INSERT INTO rental VALUES (NULL, NOW(), null, "+it.getEpisode().getEpisodeId()+", @user_now);");
                    //insert into rental succeded: db trigger will update the log
                }catch (Exception e){  //insert into rental failed
                    //undo changes
                    try {
                        App.connectDB.rollback();
                    }catch (Exception s)
                    {
                        s.printStackTrace();
                    }

                    //print message
                    customerUI_Info_ErrorReportLabel.setText("Error Occured. Rental not registered.");
                    PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
                    visiblePause.setOnFinished(someevent -> customerUI_Info_ErrorReportLabel.setText(""));
                    visiblePause.play();

                    //log update: manual
                    String loginsertion = "insert into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"rental\");";
                    DBUtil.DBexecuteQuery(App.getConnection(), loginsertion);
                    e.printStackTrace();
                    //continue in order not to insert new payment
                    continue;
                }
            }else { //film rental
                try {
                    DBUtil.DBexecuteQuery(App.getConnection(), "INSERT INTO rental VALUES (NULL, NOW(), "+it.getFilm().getFilmId()+", null, @user_now);");
                    //rental successful: db trigger will update the log
                }catch(Exception e){  //insert into rental failed 
                    //undo changes
                    try {
                        App.connectDB.rollback();
                    }catch (Exception s)
                    {
                        s.printStackTrace();
                    }

                    //print message
                    customerUI_Info_ErrorReportLabel.setText("Error Occured. Rental not registered.");
                    PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
                    visiblePause.setOnFinished(someevent -> customerUI_Info_ErrorReportLabel.setText(""));
                    visiblePause.play();

                    //log update: manual
                    String loginsertion = "insert into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"rental\");";
                    DBUtil.DBexecuteQuery(App.getConnection(), loginsertion);
                    e.printStackTrace();

                    //continue in order to avoid insert on payment
                    continue;
                }
            }
            
            //rental insertion successfull
            /* PAYMENT INSERTION */
            ResultSet LastRentalIdSet = DBUtil.DBexecute(App.getConnection(), "select rental_id from rental order by rental_id desc limit 0,1");
            if (LastRentalIdSet.next()) rental_to_pay = LastRentalIdSet.getInt("rental_id");
            LastRentalIdSet.close();
            
            try { 
                DBUtil.DBexecuteQuery(App.getConnection(), "INSERT INTO payment VALUES(NULL, @user_now, "+rental_to_pay+", "+it.getCheckoutItemPrice()+", NOW());");
                //insert into payment succeded: db trigger will update log
            }catch(Exception e){  //insert into payment failed 
                //undo changes
                try {
                    App.connectDB.rollback();
                }catch (Exception s)
                {
                    s.printStackTrace();
                }

                //print message
                customerUI_Info_ErrorReportLabel.setText("Error Occured. Rental not registered.");
                PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
                visiblePause.setOnFinished(someevent -> customerUI_Info_ErrorReportLabel.setText(""));
                visiblePause.play();

                //log update: manual (no matter the query that failed, the whole action is considered failed)
                String loginsertion1 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"rental\");";
                DBUtil.DBexecuteQuery(App.getConnection(), loginsertion1);
                String loginsertion2 = "insert into log values (NULL, \""+App.email+"\", NOW(), \"INSERT\", 0, \"payment\");";
                DBUtil.DBexecuteQuery(App.getConnection(), loginsertion2);
                e.printStackTrace();
                continue;
            }  
        }
        App.connectDB.commit();
        App.connectDB.setAutoCommit(true);

        CustomerUI_CurrRental_Checkout_Table.getItems().clear();
        initializeMMS();
    }
    
    //--- USED BY MORE THAN ONE VIEWS ---//
    @FXML 
    private void showinfo() throws Exception
    {
        if (currentview== CustomerUI_MMS_Pane && CustomerUI_MMS_TabPane.getSelectionModel().getSelectedItem() == CustomerUI_MMS_MoviesTab)
        {
            RentalinjFilm selected = CustomerUI_MMS_MovieRentalsTable.getSelectionModel().getSelectedItem();
            if (selected !=null)
            {
                showLibrary();

                //select tab
                SelectionModel<Tab> selectedtab = CustomerUI_Library_TabPane.getSelectionModel();
                selectedtab.select(0);
                
                //select row
                ResultSet rs;
                rs = DBUtil.DBexecute(App.getConnection(), "select film_id from film ORDER BY film_id;");
                int index = -1;

                while(rs.next())
                {
                    if (rs.getInt("film_id")==selected.getRental().getRentalFilmId())
                    {
                        index = rs.getRow()-1;
                        break;
                    }
                }
                rs.close();
                CustomerUI_Library_MoviesTable.getSelectionModel().select(index);
                
            }           
        } 
        else if (currentview== CustomerUI_MMS_Pane  && CustomerUI_MMS_TabPane.getSelectionModel().getSelectedItem() == CustomerUI_MMS_EpisodesTab)
        {
            RentalinjEpisodeinjSeason selected = CustomerUI_MMS_EpisodeRentals_Table.getSelectionModel().getSelectedItem();
            if (selected !=null)
            {
                viewEpisodes();
                
                //select row
                ResultSet rs;
                rs = DBUtil.DBexecute(App.getConnection(), "select episode_id from episode where season_id ="+selected.getSeason().getSeasonId()+" order by episode_id;");
                int index = -1;
            
                while(rs.next())
                {
                    if (rs.getInt("episode_id")==selected.getRental().getRentalEpisodeId())
                    {
                        index = rs.getRow() -1;  
                        break;
                    }
                }
                rs.close();
                CustomerUI_Library_Episodes_Table.getSelectionModel().select(index);   

            }
        }
        else if (currentview == CustomerUI_CurrRental_Pane)
        {
            CheckoutItem selected = CustomerUI_CurrRental_Checkout_Table.getSelectionModel().getSelectedItem();

            if (selected != null)
            {
               //go to next view
               if (selected.getCheckoutItemType() == "film")
               {
                    showLibrary();

                    //select tab
                    SelectionModel<Tab> selectedtab = CustomerUI_Library_TabPane.getSelectionModel();
                    selectedtab.select(0);

                    //select row 
                    ResultSet rs;
                    rs = DBUtil.DBexecute(App.getConnection(), "select film_id from film ORDER BY film_id;");
                    int index = -1;
                    int idtoselect = selected.getFilm().getFilmId();

                    while(rs.next())
                    {
                        if (rs.getInt("film_id")== idtoselect)
                        {
                            index = rs.getRow()-1;
                            break;
                        }
                    }
                    rs.close();
                    CustomerUI_Library_MoviesTable.getSelectionModel().select(index);
               }
               else{
                    viewEpisodes();

                    //select row
                    ResultSet rs;
                    rs = DBUtil.DBexecute(App.getConnection(), "select episode_id from episode WHERE season_id="+selected.getEpisode().getEpisodeSeasonId()+" ORDER BY episode_id;");
                    int index = -1;
                    int idtoselect = selected.getEpisode().getEpisodeId();

                    while(rs.next())
                    {
                        if (rs.getInt("episode_id")== idtoselect)
                        {
                            index = rs.getRow()-1;
                            break;
                        }
                    }
                    rs.close();
                    CustomerUI_Library_Episodes_Table.getSelectionModel().select(index);

               }
            }
        }
    } 
}
