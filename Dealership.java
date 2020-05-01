
/* -----------------------------------------------
	 Submitted By: Dillon Mead, John Thompson
	 Final Project

	 Submitted On: 30 April 2020

 By submitting this program with my name,
 I affirm that the creation and modification
of this program is primarily my own work.
------------------------------------------------ */

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
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
    DBManager dealerDB = new DBManager();
    User admin = new User();
    BorderPane dealershipPane = new BorderPane(); //Stage Pane
    SearchWrapper searcher;
    
    public Dealership()
    {
        homePage();
    }

    private void homePage()
    {
        /* Menu and options */
        HBox menubar = new HBox(25);
        Button browseBtn = new Button("Browse Inventory");
        //Button locationBtn = new Button("Location");
        Button contactBtn = new Button("Contact Store");
        browseBtn.setFont(Font.font("Arial", 18));
        //locationBtn.setFont(Font.font("Arial", 18));
        contactBtn.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(browseBtn);//, contactBtn, locationBtn);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setTop(menubar);

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
        logInFields.getChildren().addAll(wronglogin, usernameTextbox, passwordTextbox, enterBtn);
        logInFields.setAlignment(Pos.CENTER);
        dealershipPane.setCenter(logInFields);

        /* log in performs verification query and changes pane if correct */
        enterBtn.setOnAction( loggingin ->{
            admin.setUserName(inputUsername.getText());
            admin.setPassword(inputPassword.getText());
            if (dealerDB.loginAdmin(admin.getUserName(), admin.getPassword()))
                adminHome();
            else
                wronglogin.setVisible(true);
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
        HBox menubar = new HBox(25);
        Button contactBtn = new Button("Contact Store");
        Button logInBtn = new Button("Admin Log In");
        Button searchBtn = new Button("Search Inventory");
        logInBtn.setFont(Font.font("Arial", 18));
        contactBtn.setFont(Font.font("Arial", 18));
        searchBtn.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(logInBtn, searchBtn);//, contactBtn);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setTop(menubar);

        /* show list of inventory*/
        VBox inventoryPane = new VBox();
        ObservableList<ArrayList<String>> inventory = FXCollections.observableArrayList(dealerDB.getAllCars());
        
        TableView<ArrayList<String>> inventoryList = new TableView<>();
        inventoryList.setEditable(true);
        
        ModularTable allCarsTable = new ModularTable(new ArrayList<String>(Arrays.asList("VIN", "Color", "Mileage", "Price", "Store ID", "Car Year", "Make", "Model", "Car Type")));
        
        inventoryList.getColumns().addAll(allCarsTable.getColumns());
        
        
        inventoryList.getItems().addAll(inventory);
        System.out.println(inventoryList.getItems().get(0).size() + " " + dealerDB.getAllCars().get(0).size());
        
        inventoryPane.getChildren().addAll(inventoryList);
        inventoryPane.setAlignment(Pos.CENTER);
        
        dealershipPane.setCenter(inventoryPane);
        
        
        /* selecting log in button displays home page */
        logInBtn.setOnAction( login -> {homePage();});
        searchBtn.setOnAction( search -> {searchInventoryPage();});
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
   

    private void searchInventoryPage()
    {
        /* Menu and options */
        HBox menubar = new HBox(25);
        Button contactBtn = new Button("Contact Store");
        Button logInBtn = new Button("Admin Log In");
        Button returnToInventoryBtn = new Button("Return to Inventory");
        Button searchBtn = new Button("Search");
        logInBtn.setFont(Font.font("Arial", 18));
        returnToInventoryBtn.setFont(Font.font("Arial", 18));

        /* Set main menu on stage left */
        menubar.getChildren().addAll(logInBtn, returnToInventoryBtn);//, contactBtn);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setTop(menubar);

        /* Search Fields */
        ObservableList<ArrayList<String>> inventory = FXCollections.observableArrayList(dealerDB.getAllCars());
        
        VBox searchFieldPane = new VBox();
        
        searcher = new SearchWrapper(inventory); // Not sure how else to update the results with a button :(
        
        for (InputField field : searcher.getAllFields())
        {
        	 searchFieldPane.getChildren().add(field.getPane());
        }
        
        searchFieldPane.getChildren().add(searchBtn);
        
        /* show list of inventory*/
        SearchWrapper.inventoryList = new TableView<>();
        SearchWrapper.inventoryList.setEditable(true);
        
        ModularTable allCarsTable = new ModularTable(new ArrayList<String>(Arrays.asList("VIN", "Color", "Mileage", "Price", "Store ID", "Car Year", "Make", "Model", "Car Type")));
        
        SearchWrapper.inventoryList.getColumns().addAll(allCarsTable.getColumns());
        
        SearchWrapper.inventoryList.getItems().addAll(SearchWrapper.getObservableList());
        
        dealershipPane.setLeft(searchFieldPane);
        dealershipPane.setCenter(SearchWrapper.inventoryList);
        
        
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
        
        /* Search Logic */
        searchBtn.setOnAction( e -> {
        	
        	ArrayList<InputField> allFields = searcher.getAllFields();
        	
        	String storeName = allFields.get(0).getInput().get(0);
        	String make = allFields.get(1).getInput().get(0);
        	String model = allFields.get(2).getInput().get(0);
        	String carType = allFields.get(3).getInput().get(0);
        	double priceUpper;
        	double priceLower;
        	double mileageUpper;
        	double mileageLower;
        	
        	
        	if (allFields.get(4).getInput().get(0) != null)
        		priceUpper = Double.parseDouble(allFields.get(4).getInput().get(0));
        	else
        		priceUpper = -1;
        		
        	if (allFields.get(4).getInput().get(1) != null)
        		priceLower = Double.parseDouble(allFields.get(4).getInput().get(1));
        	else
        		priceLower = -1;
        	
        	if (allFields.get(5).getInput().get(0) != null)
        		mileageUpper = Double.parseDouble(allFields.get(5).getInput().get(0));
        	else
        		mileageUpper = -1;
        	
        	if (allFields.get(5).getInput().get(1) != null)
        		mileageLower = Double.parseDouble(allFields.get(5).getInput().get(1));
        	else
        		mileageLower = -1;
        	
        	
        	dealershipPane.setCenter(SearchWrapper.updateObservable(dealerDB.getCarsBy(storeName, make, model, carType, priceUpper, priceLower, mileageUpper, mileageLower)));
        	
        });
        
        returnToInventoryBtn.setOnAction( retrn -> {browseInventory();});
        

    }

    
    
    private void adminHome()
    {
        /* menu and options */
        HBox menubar = new HBox(25);
        Button browseInventoryBtn = new Button("Browse Inventory");
        Button browseAdminBtn = new Button("Browse Store Admin");
        Button logOut = new Button("Log Out");
        browseInventoryBtn.setFont(Font.font("Arial", 18));
        browseAdminBtn.setFont(Font.font("Arial", 18));
        logOut.setFont(Font.font("Arial", 18));
    
        /* Set main menu on stage left */
        menubar.getChildren().addAll(browseInventoryBtn, browseAdminBtn, logOut);
        menubar.setAlignment(Pos.CENTER);
        dealershipPane.setTop(menubar);

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
        logOut.setOnAction( logout ->{
            welcome.getChildren().clear();
            dealerDB.logoutAdmin(admin.getUserName());
            homePage();});
    }

    /* admin inventory view */
    private void adminInventory()
    {
        /* menu and options */
        HBox menubar = new HBox(25);
        Button browseAdminBtn = new Button("View Admin List");
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
        dealershipPane.setTop(menubar);

        /* list inventory for admin's store and update buttons for list */
        VBox inventoryPane = new VBox(10);
        ObservableList<ArrayList<String>> inventory = FXCollections.observableArrayList(dealerDB.getAllCars());
        //ObservableList<String> inventory = FXCollections.observableArrayList(dealerDB.getCarsBy(dealerDB.executeStatement("SELECT storeName FROM dealerships WHERE storeID = (SELECT storeID FROM admin WHERE username = " + admin.getUserName() + ");")));
        
        TableView<ArrayList<String>> inventoryList = new TableView<>();
        inventoryList.setEditable(true);

        ModularTable carsTable = new ModularTable(new ArrayList<String>(Arrays.asList("VIN", "Color", "Mileage", "Price", "Store ID", "Car Year", "Make", "Model", "Car Type")));

        inventoryList.getColumns().addAll(carsTable.getColumns());
        inventoryList.getItems().addAll(inventory);
        System.out.println(inventoryList.getItems().get(0).size() + " " + dealerDB.getAllCars().get(0).size());
        
        inventoryPane.getChildren().addAll(inventoryList);
        inventoryPane.setAlignment(Pos.CENTER);

        dealershipPane.setCenter(inventoryPane);
        
        VBox alterInventory = new VBox(10);
        alterInventory.getChildren().addAll(inventoryList);
        alterInventory.setAlignment(Pos.CENTER);

        /* generate fields for database editing */
            /* panes for option inputs */
            HBox addCarPane = new HBox(15);
            HBox updateCarPane = new HBox(15);
            HBox deleteCarPane = new HBox(15);
            VBox currentvinPane = new VBox(10);
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
            Text currentvinText = new Text("Current VIN");
            currentvinText.setFont(Font.font("Arial", 18));
            TextField currentvinField = new TextField();
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

            /* collect text and textfields into group panes */
            currentvinPane.getChildren().addAll(currentvinText, currentvinField);
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

            /* combine all group panes */
            addCarPane.getChildren().addAll(vinPane, storePane, pricePane, yearPane, makePane, modelPane, typePane, colorPane, mileagePane, enterPane);
            addCarPane.setAlignment(Pos.BOTTOM_CENTER);
            updateCarPane.getChildren().addAll(currentvinPane, vinPane, storePane, pricePane, yearPane, makePane, modelPane, typePane, colorPane, mileagePane, enterPane);
            updateCarPane.setAlignment(Pos.BOTTOM_CENTER);
            deleteCarPane.getChildren().addAll(currentvinPane, enterPane);
            deleteCarPane.setAlignment(Pos.BOTTOM_CENTER);
            

        /* selecting add Vehicle opens pane with text fields to create new tuple */
        addCarBtn.setOnAction( addcar ->{
            alterInventory.getChildren().clear();
            alterInventory.getChildren().addAll(inventoryList, addCarPane);
            alterInventory.setAlignment(Pos.CENTER);
            dealershipPane.setCenter(alterInventory);
            enterBtn.setOnAction( addcarQuery ->{
                dealerDB.addCar(Integer.parseInt(vinField.getText()), colorField.getText(), Double.parseDouble(mileageField.getText()), Double.parseDouble(priceField.getText()), Integer.parseInt(storeField.getText()), Integer.parseInt(yearField.getText()), makeField.getText(), modelField.getText(), typeField.getText());
                adminInventory();
            });
        });
        /* selecting update Vehicle opens pane with text fields to update tuple */
        updateCarBtn.setOnAction( updatecar ->{
            alterInventory.getChildren().clear();
            alterInventory.getChildren().addAll(inventoryList, updateCarPane);
            alterInventory.setAlignment(Pos.CENTER);
            dealershipPane.setCenter(alterInventory);
            enterBtn.setOnAction( updatecarQuery ->{
                if (!(vinField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET VIN = " + vinField.getText() + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(colorField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET color = " + colorField.getText() + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(mileageField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET mileage = " + Double.parseDouble(mileageField.getText()) + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(priceField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET price = " + Double.parseDouble(priceField.getText()) + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(storeField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET storeID = " + Integer.parseInt(storeField.getText()) + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(yearField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET carYear = " + Integer.parseInt(yearField.getText()) + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(makeField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET make = " + makeField.getText() + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(modelField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET model = " + modelField.getText() + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
                else if (!(typeField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE inventories SET carType = " + typeField.getText() + " WHERE VIN = " + Integer.parseInt(currentvinField.getText()) + ");");
            else {}
                adminInventory();
            });
        });

        /* selecting delete Vehicle opens pane with text fields to delete tuple */
        deleteCarBtn.setOnAction( deletecar ->{
            alterInventory.getChildren().clear();
            alterInventory.getChildren().addAll(inventoryList, deleteCarPane);
            alterInventory.setAlignment(Pos.CENTER);
            dealershipPane.setCenter(alterInventory);
            enterBtn.setOnAction( deletecarQuery ->{
                dealerDB.removeCar(currentvinField.getText());
                adminInventory();
            });
        });

        /* selecting view store admin displays admin list for admins only */
        browseAdminBtn.setOnAction( viewAdmin ->{adminList();});

        /* selecting log out button logs admin out and returns to home page */
        // change loggedin boolean to false
        logOut.setOnAction( logout ->{
            alterInventory.getChildren().clear();
            dealerDB.logoutAdmin(admin.getUserName());
            homePage();});
    }

    /* admin view of admin list */
    private void adminList()
    {
        /* menu and options */
        HBox menubar = new HBox(25);
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
        dealershipPane.setTop(menubar);

        /* list inventory for admin's store and update buttons for list */
        VBox adminPane = new VBox();
        ObservableList<ArrayList<String>> adminList = FXCollections.observableArrayList(dealerDB.getAllAdmins());
        ListView<ArrayList<String>> adminView = new ListView<>(adminList);
        adminPane.getChildren().addAll(adminView);
        adminPane.setAlignment(Pos.CENTER);
        dealershipPane.setCenter(adminPane);

        VBox alterAdmin = new VBox(10);
        alterAdmin.getChildren().addAll(adminPane);
        alterAdmin.setAlignment(Pos.CENTER);

        /* generate fields for database editing */
            /* panes for option inputs */
            HBox addAdminPane = new HBox(15);
            HBox updateAdminPane = new HBox(15);
            HBox deleteAdminPane = new HBox(15);
            VBox userUpdatePane = new VBox(10);
            VBox fullnamePane = new VBox(10);
            VBox usernamePane = new VBox(10);
            VBox passwordPane = new VBox(10);
            VBox titlePane = new VBox(10);
            VBox storeIDPane = new VBox(10);
            VBox enterPane = new VBox();

            /* textfields for options */
            Text userUpdateText = new Text("Current Username");
            userUpdateText.setFont(Font.font("Arial", 18));
            TextField userUpdateField = new TextField();
            Text fullnameText = new Text("New Fullname");
            fullnameText.setFont(Font.font("Arial", 18));
            TextField fullnameField = new TextField();
            Text usernameText = new Text("New Username");
            usernameText.setFont(Font.font("Arial", 18));
            TextField usernameField = new TextField();
            Text passwordText = new Text("New Password");
            passwordText.setFont(Font.font("Arial", 18));
            TextField passwordField = new TextField();
            Text titleText = new Text("New Title");
            titleText.setFont(Font.font("Arial", 18));
            TextField titleField = new TextField();
            Text storeIDText = new Text("New StoreID");
            storeIDText.setFont(Font.font("Arial", 18));
            TextField storeIDField = new TextField();
            Button enterBtn = new Button("Enter");
            enterBtn.setFont(Font.font("Arial", 18));

            /* collect text and textfields into group panes */
            userUpdatePane.getChildren().addAll(userUpdateText, userUpdateField);
            fullnamePane.getChildren().addAll(fullnameText, fullnameField);
            usernamePane.getChildren().addAll(usernameText, usernameField);
            passwordPane.getChildren().addAll(passwordText, passwordField);
            titlePane.getChildren().addAll(titleText, titleField);
            storeIDPane.getChildren().addAll(storeIDText, storeIDField);
            enterPane.getChildren().addAll(enterBtn);
            enterPane.setAlignment(Pos.CENTER);

            /* combine all group panes */
            addAdminPane.getChildren().addAll(fullnamePane, usernamePane, passwordPane, titlePane, storeIDPane, enterPane);
            addAdminPane.setAlignment(Pos.BOTTOM_CENTER);
            updateAdminPane.getChildren().addAll(userUpdatePane, fullnamePane, usernamePane, passwordPane, titlePane, storeIDPane, enterPane);
            updateAdminPane.setAlignment(Pos.BOTTOM_CENTER);
            deleteAdminPane.getChildren().addAll(userUpdatePane, enterPane);
            deleteAdminPane.setAlignment(Pos.BOTTOM_CENTER);

        /* show list of admins*/

        VBox adminListPane = new VBox();
        ObservableList<ArrayList<String>> administrators = FXCollections.observableArrayList(dealerDB.getAllAdmins());
        TableView<ArrayList<String>> adminListView = new TableView<>();
        adminListView.setEditable(true);

        ModularTable allAdminsTable = new ModularTable(new ArrayList<String>(Arrays.asList("Full Name", "Username", "Job Title", "Store ID", "Logged In")));
        adminListView.getColumns().addAll(allAdminsTable.getColumns());
        adminListView.getItems().addAll(administrators);
        
        adminListPane.getChildren().addAll(adminListView);
        adminListPane.setAlignment(Pos.CENTER);

        dealershipPane.setCenter(adminListPane);

        /* selecting browse inventory displays inventory page for admins only */
        browseInventoryBtn.setOnAction( viewInventory ->{adminInventory();});

        /* selecting add admin opens pane with text fields to create new tuple */
        addAdminBtn.setOnAction( addadmin ->{
            adminListPane.getChildren().addAll(addAdminPane);
            dealershipPane.setCenter(adminListPane);
            enterBtn.setOnAction( addAdminQuery ->{
                dealerDB.addAdmin(usernameField.getText(), passwordField.getText(), fullnameField.getText(), titleField.getText(), Integer.parseInt(storeIDField.getText()));
                adminList();
            });
        });

        /* selecting update admin opens pane with text fields to update tuple */
        updateAdminBtn.setOnAction( updateadmin ->{
            adminListPane.getChildren().clear();
            adminListPane.getChildren().addAll(adminListView, updateAdminPane);
            adminListPane.setAlignment(Pos.CENTER);
            dealershipPane.setCenter(adminListPane);
            enterBtn.setOnAction( updateAdminQuery ->{
                if (!(fullnameField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE admin SET fullname = " + fullnameField.getText() + " WHERE username = " + userUpdateField.getText() + ");");
                else if (!(usernameField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE admin SET username = " + usernameField.getText() + " WHERE username = " + userUpdateField.getText() + ");");
                else if (!(passwordField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE admin SET password = " + passwordField.getText() + " WHERE username = " + userUpdateField.getText() + ");");
                else if (!(titleField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE admin SET title = " + titleField.getText() + " WHERE username = " + userUpdateField.getText() + ");");
                else if (!(storeIDField.getText().isEmpty()))
                    dealerDB.executeModification("UPDATE admin SET storeID = " + Integer.parseInt(storeIDField.getText()) + " WHERE username = " + userUpdateField.getText() + ");");
                else {}
                adminList();
            });
        });

        /* selecting delete admin opens pane with text fields to delete tuple */
        deleteAdminBtn.setOnAction( deleteadmin ->{
            adminListPane.getChildren().clear();
            adminListPane.getChildren().addAll(adminListView, deleteAdminPane);
            adminListPane.setAlignment(Pos.CENTER);
            dealershipPane.setCenter(adminListPane);
            enterBtn.setOnAction( deleteAdminQuery ->{
                dealerDB.removeAdmin(userUpdateField.getText());
                adminList();
            });
        });

        /* selecting log out button logs admin out and returns to home page */
        // change loggedin boolean to false
        logOut.setOnAction( logout ->{
            adminListPane.getChildren().clear();
            dealerDB.logoutAdmin(admin.getUserName());
            homePage();});
    }

    public class User
    {
	    private String username;
	    private String password;
	
	    public void setUserName(String first)
	    {
		    this.username = first;
	    }
	    public String getUserName()
	    {
		    return username;
	    }

	    public void setPassword(String last)
	    {
		    this.password = last;
	    }
	    public String getPassword()
	    {
		    return password;
        }
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