import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class InputField 
{
	private HBox rowPane = new HBox();
	private Text description = new Text();
	private TextField textField1 = new TextField();
	private TextField textField2 = null;
	
	private int width;
	private int height;
	
	public InputField(String description)
	{
		this(description, false);
	}
	
	public InputField(String description, boolean isDoubleField)
	{
		this(description, null, isDoubleField);
	}
	
	public InputField(String description, ArrayList<String> inputHints, boolean isDoubleField)
	{
		this.description.setText(description);
		
		if (isDoubleField)
			this.textField2 = new TextField();
		
		if (inputHints != null && !inputHints.isEmpty())
		{
			this.textField1.setPromptText(inputHints.get(0));
			
			if (inputHints.size() > 1)
			{			
				this.textField2.setPromptText(inputHints.get(1));
			}
		}
		
		if (isDoubleField)
			rowPane.getChildren().addAll(this.description, this.textField1, this.textField2);
		else
			rowPane.getChildren().addAll(this.description, this.textField1);
	}
	
	public HBox getPane()
	{
		return this.rowPane;
	}
	
	public ArrayList<String> getInput()
	{
		ArrayList<String> result = new ArrayList<>();
		
		if (textField1.getText() != null && !textField1.getText().isEmpty())
			result.add(textField1.getText());
		else
			result.add(null);
		
		if (textField2 != null && textField2.getText() != null && !textField2.getText().isEmpty())
			result.add(textField2.getText());
		else 
			result.add(null);
		
	
		
		return result;
	}
	
	public void clearInput()
	{
		this.textField1.clear();
		
		if (textField2 != null)
			textField2.clear();
	}
}
