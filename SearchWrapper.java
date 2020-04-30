import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

class SearchWrapper
{
	private InputField fStoreName = new InputField("Store Name");
	private InputField fMake = new InputField("Make");
	private InputField fModel = new InputField("Model");
	private InputField fCarType = new InputField("Car Type");
	private InputField fPrice = new InputField("Price Range", new ArrayList<String>(Arrays.asList("Lower Bound", "Upper Bound")), true);
	private InputField fMileage = new InputField("Mileage Range", new ArrayList<String>(Arrays.asList("Lower Bound", "Upper Bound")), true);
	
	private static ObservableList<ArrayList<String>> sObservable;	
	public static TableView<ArrayList<String>> inventoryList = new TableView<>();
	
	
	
	public SearchWrapper()
	{
		
	}
	
	public SearchWrapper(ObservableList<ArrayList<String>> observableList)
	{
		sObservable = observableList;
	}
	
	public static ObservableList<ArrayList<String>> getObservableList()
	{
		return sObservable;		
	}
	
	public static TableView<ArrayList<String>> updateObservable(ArrayList<ArrayList<String>> data)
	{
		sObservable.removeAll(sObservable);
		
		for (ArrayList<String> row : data)
		{
			sObservable.add(row);
		}
		System.out.println(sObservable.size());
		
		inventoryList.getItems().clear();
		inventoryList.getItems().addAll(sObservable);
		
		return inventoryList;
				
	}
	
	public ArrayList<InputField> getAllFields()
	{
		return new ArrayList<InputField>(Arrays.asList(fStoreName, fMake, fModel, fCarType, fPrice, fMileage));
	}
	
}