import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {

	private Connection connection;
	
	public DBManager() 
	{
		System.out.println("Starting connection...");
		try
		{
			System.out.println("Working...");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dealership", "root", "mysql");
			System.out.println("Connected!");
		} 
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}

	
	
	// General
	
	public ArrayList<String> executeStatement(String query)
	{
		ArrayList<String> results = new ArrayList<>();
		
		try
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			int counter = 1;
			
			while (rs.next())
			{
				results.add(rs.getString(counter));
			}
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return results;
	}
	
	// Get-all methods
	
	public ArrayList<String> getAllCars()
	{
		
		ArrayList<String> result = executeStatement("SELECT * FROM ineventories;");
			
		return result;
	}
	
	public ArrayList<String> getAllAdmins()
	{
		ArrayList<String> result = executeStatement("SELECT * FROM admin;");
		
		return result;
	}
	
	public ArrayList<String> getAllStores()
	{
		ArrayList<String> result = executeStatement("SELECT * FROM dealerships;");
		
		return result;
	}
	
	// Specific queries
	
	public ArrayList<String> getCarsBy(String storeName)
	{
		ArrayList<String> result = executeStatement("SELECT * FROM inventories WHERE storeid in (SELECT storeid FROM dealerships WHERE storeName = " + storeName + ");");
		
		return result;
	}
	
	public ArrayList<String> getCarsBy(String storeName, String make, String model, Double mileageUpperBound, Double mileageLowerBound, Double priceUpperbound, Double priceLowerBound)
	{
		// NOTE: use "-1" or -1 for any option that must be disabled
		
		String[][] inventoryOptions = {{"storename = ", storeName}, {"AND make = ", make}, {" AND model = ", model}, {" AND mileage < ", mileageUpperBound.toString()}, {" AND mileage > ", mileageLowerBound.toString()},
				{" AND price < ", priceUpperbound.toString()}, {" AND price > " + priceLowerBound}};
		
		String conditions = "";
		
		for (String[] option : inventoryOptions)
		{
			if (!option[1].equals("-1"))
			{
				conditions = conditions + option[0];
			}
		}
		
		ArrayList<String> result = executeStatement("SELECT * FROM dealerships WHERE " + conditions + ");");
		
		return result;
	}
	
	
	// Deletions
	
	public void removeAdmin(String username)
	{
		executeStatement("DELETE FROM admin WHERE username = " + username + ");");
	}
	
	public void removeCar(String vin)
	{
		executeStatement("DELETE FROM inventories WHERE vin = " + vin + ");");
	}
	
	public void removeCompany(String companyName)
	{
		executeStatement("DELETE FROM company WHERE name = " + companyName + ");");
	}
	
	public void removeCompany(int companyID)
	{
		executeStatement("DELETE FROM company WHERE name = " + companyID + ");");
	}
	
	// Insertions
	
	public void addAdmin(String username, String password, String fullname, String title, int storeID)
	{
		executeStatement("INSERT INTO admin VALUES (" + fullname + " " + username + " " + password + " " + title + " " + storeID + " FALSE);");
	}
	
	public void addCar(int vin, String color, double mileage, double price, int storeID, int carYear, String make, String model, String carType)
	{
		executeStatement("INSERT INTO admin VALUES (" + vin + " " + color + " " + mileage + " " + price + " " + storeID + " " + carYear + " " + make + " " + model + " " + carType + ");");
	}
	
	public void loginAdmin(String username, String password)
	{
		ArrayList<String> user = executeStatement("SELECT username FROM admin WHERE username = " + username + " AND password = " + password + ");");
		
		if (user.isEmpty())
		{
			System.out.println("Failed to find user: Incorrect credentials?");
		}
		else
		{
			executeStatement("UPDATE admin SET loggedin = true WHERE username = " + username + ");");
		}
	}

	public void logoutAdmin(String username)
	{
		executeStatement("UPDATE admin SET loggedin = false WHERE username = " + username + ");");
	}
}

