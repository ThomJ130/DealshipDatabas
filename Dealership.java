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
//import javafx.scene.control.MenuItem;
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
        contactBtn.setOnAction( contact ->{
            VBox contactPane = new VBox(15);
            HBox contactText = new HBox();
            HBox goBack = new HBox();
            Text contactInfo = new Text("Contact Info will be here!");
            contactInfo.setFont(Font.font("Arial", 18));
            Button returnBtn = new Button("Return");
            returnBtn.setFont(Font.font("Arial", 18));
            contactText.getChildren().addAll(contactInfo);
            goBack.getChildren().addAll(returnBtn);
            contactText.setAlignment(Pos.BOTTOM_CENTER);
            goBack.setAlignment(Pos.CENTER);
            contactPane.getChildren().addAll(contactText, goBack);
            contactPane.setAlignment(Pos.CENTER);
            dealershipPane.setCenter(contactPane);

            /* goBack button returns to previous pane */
            returnBtn.setOnAction( returnTo ->{homePage();});
        });

        /* selecting browse displays inventory list */
        browseBtn.setOnAction( viewInventory ->{browseInventory();});
    }

    /* General user inventory view */
    private void browseInventory()
    {
        /* Menu and options */
        VBox menubar = new VBox(50);
        Button contactBtn = new Button("Contact Store");
        Button logInBtn = new Button("Admin Log In");
        logInBtn.setFont(Font.font("Arial", 18));
        contactBtn.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(logInBtn, contactBtn);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setLeft(menubar);

        /* show list of inventory*/
        VBox inventoryList = new VBox();
        Text inventoryMsg = new Text("Inventory List goes here!");
        inventoryMsg.setFont(Font.font("Arial", 18));
        inventoryList.getChildren().addAll(inventoryMsg);
        inventoryList.setAlignment(Pos.CENTER);
        dealershipPane.setCenter(inventoryList);

        /* selecting log in button displays home page */
        logInBtn.setOnAction( login ->{homePage();});

        /* selecting contact store displays store info */
        contactBtn.setOnAction( contact ->{
            VBox contactPane = new VBox(15);
            HBox contactText = new HBox();
            HBox goBack = new HBox();
            Text contactInfo = new Text("Contact Info will be here!");
            contactInfo.setFont(Font.font("Arial", 18));
            Button returnBtn = new Button("Return");
            returnBtn.setFont(Font.font("Arial", 18));
            contactText.getChildren().addAll(contactInfo);
            goBack.getChildren().addAll(returnBtn);
            contactText.setAlignment(Pos.BOTTOM_CENTER);
            goBack.setAlignment(Pos.CENTER);
            contactPane.getChildren().addAll(contactText, goBack);
            contactPane.setAlignment(Pos.CENTER);
            dealershipPane.setCenter(contactPane);

            /* goBack button returns to previous pane */
            returnBtn.setOnAction( returnTo ->{browseInventory();});
        });
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
        Text hello = new Text("Welcome to the Admin page!");
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

    /* admin inventory view */
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
        VBox inventoryList = new VBox();
        Text inventoryMsg = new Text("Inventory List goes here!");
        inventoryMsg.setFont(Font.font("Arial", 18));
        inventoryList.getChildren().addAll(inventoryMsg);
        inventoryList.setAlignment(Pos.CENTER);
        dealershipPane.setCenter(inventoryList);

        /* selecting browse store admin displays admin list for admins only */
        browseAdminBtn.setOnAction( viewAdmin ->{adminList();});

        /* selecting add Vehicle opens pane with text fields to create new tuple */
        addCarBtn.setOnAction( addcar ->{
            /* panes for option inputs */
            HBox addCarPane = new HBox(15);
            VBox vinPane = new VBox(10);
            VBox storePane = new VBox(10);
            VBox pricePane = new VBox(10);
            VBox yearPane = new VBox(10);
            VBox makePane = new VBox(10);
            VBox modelPane = new VBox(10);
            VBox typePane = new VBox(10);
            VBox colorPane = new VBox(10);
            VBox mileagePane = new VBox(10);
            VBox enterPane = new VBox();

            /* textfields for options */
            Text vinText = new Text("VIN");
            vinText.setFont(Font.font("Arial", 18));
            TextField vinField = new TextField();
            Text storeText = new Text("Store ID");
            storeText.setFont(Font.font("Arial", 18));
            TextField storeField = new TextField();
            Text priceText = new Text("Price");
            priceText.setFont(Font.font("Arial", 18));
            TextField priceField = new TextField();
            Text yearText = new Text("Year");
            yearText.setFont(Font.font("Arial", 18));
            TextField yearField = new TextField();
            Text makeText = new Text("Make");
            makeText.setFont(Font.font("Arial", 18));
            TextField makeField = new TextField();
            Text modelText = new Text("Model");
            modelText.setFont(Font.font("Arial", 18));
            TextField modelField = new TextField();
            Text typeText = new Text("Type");
            typeText.setFont(Font.font("Arial", 18));
            TextField typeField = new TextField();
            Text colorText = new Text("Color");
            colorText.setFont(Font.font("Arial", 18));
            TextField colorField = new TextField();
            Text mileageText = new Text("Mileage");
            mileageText.setFont(Font.font("Arial", 18));
            TextField mileageField = new TextField();
            Button enterBtn = new Button("Enter");
            enterBtn.setFont(Font.font("Arial", 18));

            /* collect textfields into panes and display */
            vinPane.getChildren().addAll(vinText, vinField);
            storePane.getChildren().addAll(storeText, storeField);
            pricePane.getChildren().addAll(priceText, priceField);
            yearPane.getChildren().addAll(yearText, yearField);
            makePane.getChildren().addAll(makeText, makeField);
            modelPane.getChildren().addAll(modelText, modelField);
            typePane.getChildren().addAll(typeText, typeField);
            colorPane.getChildren().addAll(colorText, colorField);
            mileagePane.getChildren().addAll(mileageText, mileageField);
            enterPane.getChildren().addAll(enterBtn);
            enterPane.setAlignment(Pos.CENTER);
            addCarPane.getChildren().addAll(vinPane, storePane, pricePane, yearPane, makePane, modelPane, typePane, colorPane, mileagePane, enterPane);
            addCarPane.setAlignment(Pos.BOTTOM_CENTER);
            inventoryList.getChildren().addAll(addCarPane);
            dealershipPane.setBottom(inventoryList);

        });
        /* selecting update Vehicle opens pane with text fields to update tuple */
        updateCarBtn.setOnAction( updatecar ->{});

        /* selecting delete Vehicle opens pane with text fields to delete tuple */
        deleteCarBtn.setOnAction( deletecar ->{});

        /* selecting log out button logs admin out and returns to home page */
        // change loggedin boolean to false
        logOut.setOnAction( logout ->{homePage();});

    }

    /* admin view of admin list */
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
        VBox adminList = new VBox();
        Text adminMsg = new Text("List of store Admins goes here!");
        adminMsg.setFont(Font.font("Arial", 18));
        adminList.getChildren().addAll(adminMsg);
        adminList.setAlignment(Pos.CENTER);
        dealershipPane.setCenter(adminList);

        /* selecting browse inventory displays inventory page for admins only */
        browseInventoryBtn.setOnAction( viewInventory ->{adminInventory();});

        /* selecting add admin opens pane with text fields to create new tuple */
        addAdminBtn.setOnAction( addadmin ->{});

        /* selecting update admin opens pane with text fields to update tuple */
        updateAdminBtn.setOnAction( updateadmin ->{});

        /* selecting delete admin opens pane with text fields to delete tuple */
        deleteAdminBtn.setOnAction( deleteadmin ->{});

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
		primaryStage.setWidth(primaryScreenBounds.getWidth() * 0.8);
		primaryStage.setHeight(primaryScreenBounds.getHeight() * 0.8);
		primaryStage.centerOnScreen();

		// set the scene, title and show the primary stage
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Dealership Database");
		primaryStage.show();
		
	}
}