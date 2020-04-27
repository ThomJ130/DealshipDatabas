//package Dealership;

/* -----------------------------------------------
	 Submitted By: Dillon Mead, John Thompson
	 Final Project

	 Submitted On: 30 April 2020

 By submitting this program with my name,
 I affirm that the creation and modification
of this program is primarily my own work.
------------------------------------------------ */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Dealership extends Application
{
    BorderPane dealershipPane = new BorderPane(); //Stage Pane
    VBox mainPane = new VBox();
    MenuItem close = new MenuItem("Close");

    public Dealership()
    {
        homePage();
    }

    private void homePage()
    {
        /* Menu and options */
        VBox menubar = new VBox(50);
        Button browseBtn = new Button("Browse Inventory");
        //Button locationBtn = new Button("Location");
        Button contactBtn = new Button("Contact Store");
        browseBtn.setFont(Font.font("Arial", 18));
        //locationBtn.setFont(Font.font("Arial", 18));
        contactBtn.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(browseBtn, contactBtn);//, locationBtn);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setLeft(menubar);

        /* Create main menu log in text fields */
        VBox logInFields = new VBox(15);
        HBox usernameTextbox = new HBox();
        HBox passwordTextbox = new HBox();
        Text askUsername = new Text("Username: ");
        Text askPassword = new Text("Password: ");
        Text wronglogin = new Text("Incorrect username or password");
        TextField inputUsername = new TextField();
        TextField inputPassword = new TextField();
        Button enterBtn = new Button("Enter");
        askUsername.setFont(Font.font("Arial", 18));
        askPassword.setFont(Font.font("Arial", 18));
        wronglogin.setFont(Font.font("Arial", 18));
        enterBtn.setFont(Font.font("Arial", 18));
        wronglogin.setVisible(false);

        /* Add text fields to main menu */
        usernameTextbox.getChildren().addAll(askUsername, inputUsername);
        passwordTextbox.getChildren().addAll(askPassword, inputPassword);
        usernameTextbox.setAlignment(Pos.CENTER);
        passwordTextbox.setAlignment(Pos.CENTER);
        logInFields.getChildren().addAll(usernameTextbox, passwordTextbox, enterBtn);
        logInFields.setAlignment(Pos.CENTER);
        dealershipPane.setCenter(logInFields);

        /* log in performs verification query and changes pane if correct */
        enterBtn.setOnAction( loggingin ->{
            /* verification query & change loggedin boolean to true
        if (loggedin)
        {
            adminHome();
        }
        else
        {
            wronglogin.setVisible(true);
        } */
        adminHome();
        });

        /* selecting contact store querys and displays store info */


        /* selecting browse displays inventory list */
        browseBtn.setOnAction( viewInventory ->{browseInventory();});
    }

    private void browseInventory()
    {
        /* Menu and options */
        VBox menubar = new VBox(50);
        Button contactBtn = new Button("Contact Store");
        Button logInBtn = new Button("Log In");
        logInBtn.setFont(Font.font("Arial", 18));
        contactBtn.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(logInBtn, contactBtn);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setLeft(menubar);

        /* show list of inventory*/

        /* selecting log in button displays home page */
        logInBtn.setOnAction( login ->{homePage();});

        /* selecting contact store displays store info */

    }

    private void adminHome()
    {
        /* menu and options */
        VBox menubar = new VBox(50);
        Button browseInventoryBtn = new Button("Browse Inventory");
        Button browseAdminBtn = new Button("Browse Store Admin");
        Button logOut = new Button("Log Out");
        browseInventoryBtn.setFont(Font.font("Arial", 18));
        browseAdminBtn.setFont(Font.font("Arial", 18));
        logOut.setFont(Font.font("Arial", 18));
    
        /* Set main menu on stage left */
        menubar.getChildren().addAll(browseInventoryBtn, browseAdminBtn, logOut);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setLeft(menubar);

        /* display "Welcome!" on center stage */
        VBox welcome = new VBox();
        Text hello = new Text("Welcome!");
        hello.setFont(Font.font("Arial", 18));
        welcome.getChildren().addAll(hello);
        welcome.setAlignment(Pos.CENTER);
        dealershipPane.setCenter(welcome);

        /* selecting browse inventory button displays inventory page for admins only */
        browseInventoryBtn.setOnAction( viewInventory ->{adminInventory();});

        /* selecting browse store admin displays admin list for admins only */
        browseAdminBtn.setOnAction( viewAdmin ->{adminList();});

        /* selecting log out button logs admin out and returns to home page */
        // change loggedin boolean to false
        logOut.setOnAction( logout ->{homePage();});
    }

    private void adminInventory()
    {
        /* menu and options */
        VBox menubar = new VBox(50);
        Button browseAdminBtn = new Button("Browse Store Admin");
        Button addCarBtn = new Button("Add Vehicle");
        Button updateCarBtn = new Button("Update Vehicle Info");
        Button deleteCarBtn = new Button("Delete Vehicle");
        Button logOut = new Button("Log Out");
        browseAdminBtn.setFont(Font.font("Arial", 18));
        addCarBtn.setFont(Font.font("Arial", 18));
        updateCarBtn.setFont(Font.font("Arial", 18));
        deleteCarBtn.setFont(Font.font("Arial", 18));
        logOut.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(browseAdminBtn, addCarBtn, updateCarBtn, deleteCarBtn, logOut);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setLeft(menubar);

        /* list inventory for admin's store and update buttons for list */
        

        /* selecting browse store admin displays admin list for admins only */
        browseAdminBtn.setOnAction( viewAdmin ->{adminList();});

        /* selecting add Vehicle opens pane with text fields to create new tuple */

        /* selecting update Vehicle opens pane with text fields to update tuple */

        /* selecting delete Vehicle opens pane with text fields to delete tuple */

        /* selecting log out button logs admin out and returns to home page */
        // change loggedin boolean to false
        logOut.setOnAction( logout ->{homePage();});

    }

    private void adminList()
    {
        /* menu and options */
        VBox menubar = new VBox(50);
        Button browseInventoryBtn = new Button("Browse Inventory");
        Button addAdminBtn = new Button("Add Admin");
        Button updateAdminBtn = new Button("Update Admin Info");
        Button deleteAdminBtn = new Button("Delete Admin");
        Button logOut = new Button("Log Out");
        browseInventoryBtn.setFont(Font.font("Arial", 18));
        addAdminBtn.setFont(Font.font("Arial", 18));
        updateAdminBtn.setFont(Font.font("Arial", 18));
        deleteAdminBtn.setFont(Font.font("Arial", 18));
        logOut.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(browseInventoryBtn, addAdminBtn, updateAdminBtn, deleteAdminBtn, logOut);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setLeft(menubar);

        /* list inventory for admin's store and update buttons for list */

        /* selecting browse inventory displays inventory page for admins only */
        browseInventoryBtn.setOnAction( viewInventory ->{adminInventory();});

        /* selecting add admin opens pane with text fields to create new tuple */

        /* selecting update admin opens pane with text fields to update tuple */
 
        /* selecting delete admin opens pane with text fields to delete tuple */

        /* selecting log out button logs admin out and returns to home page */
        // change loggedin boolean to false
        logOut.setOnAction( logout ->{homePage();});

    }

    public static void main(String[] args)
	{
		launch(args);
	}

    @Override
	public void start(Stage primaryStage) throws Exception
	{
		// make the main Scene
		Scene mainScene = new Scene(dealershipPane);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		/* set Stage boundaries to 3/4 size of computer screen & center */
		primaryStage.setWidth(primaryScreenBounds.getWidth() * 0.75);
		primaryStage.setHeight(primaryScreenBounds.getHeight() * 0.75);
		primaryStage.centerOnScreen();

		// set the scene, title and show the primary stage
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Dealership Database");
		primaryStage.show();
		
		close.setOnAction( exit ->{primaryStage.close();}); // action closes the stage window
	}
}