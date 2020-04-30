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
	
	public ArrayList<ArrayList<String>> executeStatement(String query)
	{
		ArrayList<ArrayList<String>> results = new ArrayList<>();
		int numColumns = 0;
		
		
		try
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
						
			ArrayList<String> row = new ArrayList<>();
			
			while (rs.next())
			{
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
				{
					row.add(rs.getString(i));
				}
				
				results.add((ArrayList<String>) row.clone());
				
				
				row.clear();				
			}
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return results;
	}
	
	public void executeModification(String mod)
	{
		try
		{
			Statement s = connection.createStatement();
			
			s.execute(mod);
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	// Get-all methods
	
	public ArrayList<ArrayList<String>> getAllCars()
	{
		
		ArrayList<ArrayList<String>> result = executeStatement("SELECT * FROM inventories;");
		
			
		return result;
	}
	
	public ArrayList<ArrayList<String>> getAllAdmins()
	{
		ArrayList<ArrayList<String>> result = executeStatement("SELECT fullname, username, title, storeID, loggedIn FROM admin;");
		
		return result;
	}
	
	public ArrayList<ArrayList<String>> getAllStores()
	{
		ArrayList<ArrayList<String>> result = executeStatement("SELECT * FROM dealerships;");
		
		return result;
	}
	
	// Specific queries
	
	public ArrayList<ArrayList<String>> getCarsBy(String storeName)
	{
		ArrayList<ArrayList<String>> result = executeStatement("SELECT * FROM inventories WHERE storeid in (SELECT storeid FROM dealerships WHERE storeName = " + storeName + ");");
		
		return result;
	}
	
	public ArrayList<ArrayList<String>> getCarsBy(String storeName, String make, String model, String carType, Double mileageUpperBound, Double mileageLowerBound, Double priceUpperbound, Double priceLowerBound)
	{
		// NOTE: use null, "-1", or -1 for any option that must be disabled
		
		String[][] inventoryOptions = {{" make = ", make}, {" model = ", model}, {" carType = ", carType}, {" mileage < ", mileageUpperBound.toString()}, {" mileage > ", mileageLowerBound.toString()}, {" price < ", priceUpperbound.toString()}, {" price > ", priceLowerBound.toString()}};
		String storeID = "-1";
		
		String conditions = "";
		int index = 0;
		int counter = 0;
		
		
		for (String[] option : inventoryOptions)
		{
			System.out.println(option[0] + " " + option[1]);
			if (option[1] != null && !option[1].equals("-1.0"))
			{			
				if (counter > 0)
					conditions = conditions + " AND ";
				
				if (index < 3)
					conditions = conditions + option[0] +  "\"" + option[1] + "\"";
				else
					conditions = conditions + option[0] + option[1];
				counter++;
			}
			
			index++;
		}
		
		if(storeName != null)
		{
			ArrayList<ArrayList<String>> result = executeStatement("SELECT storeID FROM dealerships WHERE storeName = \"" + storeName + "\";");
			
			if (!result.isEmpty())
			{
				storeID = result.get(0).get(0);
				if (!conditions.isEmpty())
					conditions = conditions + " AND storeID = " + storeID;
				else
					conditions = "storeID = " + storeID;
			}	
		}
		
		System.out.println("SELECT * FROM inventories WHERE " + conditions + ";");
		ArrayList<ArrayList<String>> result = executeStatement("SELECT * FROM inventories WHERE " + conditions + ";");
		
		return result;
	}
	
	
	// Deletions
	
	public void removeAdmin(String username)
	{
		executeModification("DELETE FROM admin WHERE username = \"" + username + "\";");
	}
	
	public void removeCar(String vin)
	{
		executeModification("DELETE FROM inventories WHERE vin = " + vin + ";");
	}
	
	public void removeCompany(String companyName)
	{
		executeModification("DELETE FROM company WHERE name = \"" + companyName + "\";");
	}
	
	public void removeCompany(int companyID)
	{
		executeModification("DELETE FROM company WHERE companyID = " + companyID + ";");
	}
	
	// Insertions
	
	public void addAdmin(String username, String password, String fullname, String title, int storeID)
	{
		executeModification("INSERT INTO admin VALUES (" + fullname + " " + username + " " + password + " " + title + " " + storeID + " FALSE);");
	}
	
	public void addCar(int vin, String color, double mileage, double price, int storeID, int carYear, String make, String model, String carType)
	{
		executeModification("INSERT INTO admin VALUES (" + vin + " " + color + " " + mileage + " " + price + " " + storeID + " " + carYear + " " + make + " " + model + " " + carType + ");");
	}
	
	
	// Login stuffs
	
	public boolean loginAdmin(String username, String password)
	{
		boolean result = false;
		
		
		if (!password.isEmpty() && !username.isEmpty())
		{
			ArrayList<ArrayList<String>> user = executeStatement("SELECT username FROM admin WHERE username = \"" + username + "\" AND password = \"" + password + "\";");
			
			
			
			if (user.isEmpty())
			{
				System.out.println("Failed to find user: Incorrect credentials?");
			}
			else
			{
				executeModification("UPDATE admin SET loggedin = true WHERE username = \"" + username + "\";");
				result = true;
			}
		}
		
		else
		{
			System.out.println("Cannot input empty credentials!");
		}
		
		return result;
	}

	public void logoutAdmin(String username)
	{
		executeModification("UPDATE admin SET loggedin = false WHERE username = \"" + username + "\";");
	}
}

