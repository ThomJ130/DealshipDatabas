import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;

public class ModularTable 
{
	private ArrayList<ArrayList<String>> data;
	private ArrayList<TableColumn<ArrayList<String>, String>> columns = new ArrayList<>();
	
	public ModularTable(ArrayList<ArrayList<String>> data, ArrayList<String> columnTitles)
	{
		this.data = data;
		
		int i = 0;
		
		for (String title : columnTitles)
		{
			if (i <= 7)
				columns.add(generateColumn(title, i));
			
			i++;			
		}
		
		
	}
	
	private TableColumn<ArrayList<String>, String> generateColumn(String name, int columnIndex)
	{
		TableColumn<ArrayList<String>, String> result = new TableColumn<>(name);
		
		result.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnIndex)));
		
		return result;
	}
	
	public 	ArrayList<TableColumn<ArrayList<String>, String>> getColumns()
	{
		System.out.println(columns.size());
		return columns;
	}
}
