package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	
	String [] name = new String [100];
	RadixSort radix = new RadixSort();
	
	// Label object
	Label herelabel = new Label("Here: ");
	Label numberOfCharacter = new Label();
	Label totallabel = new Label("Total Words: 0");
	
	// TextField object
	TextField heretf = new TextField();
	
	// ComboBox object
	ComboBox cb = new ComboBox();
	
	// Button object
	Button addbutton = new Button("Add");
	Button removebutton = new Button("Remove");
	Button clearbutton = new Button("Clear");
	Button sortbutton = new Button("Sort");
	
	// TextArea object
	TextArea ta = new TextArea();
	
    //////////////////////////  Begin Start Method     //////////////////////////
	
	@Override
	public void start(Stage stage) throws Exception {
		
		// set true for TextArea to wrap in the line
		ta.setWrapText(true);
		
		// Button object size
		addbutton.setPrefSize(90, 30);
		removebutton.setPrefSize(90, 30);
		sortbutton.setPrefSize(90, 30);
		clearbutton.setPrefSize(90, 30);
		
		// Button Cursor
		addbutton.setCursor(Cursor.HAND);
		removebutton.setCursor(Cursor.HAND);
		sortbutton.setCursor(Cursor.HAND);
		clearbutton.setCursor(Cursor.HAND);
		
		// Label object font
		herelabel.setFont(new Font("Arial",14));
		addbutton.setFont(new Font("Arial",14));
		removebutton.setFont(new Font("Arial",14));
		sortbutton.setFont(new Font("Arial",14));
		clearbutton.setFont(new Font("Arial",14));
		
		// heretf TextField Border, ToolTip
		heretf.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		heretf.setTooltip(new Tooltip("0-50 character"));
		
		// Button Border and Background
		addbutton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		addbutton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		
		removebutton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		removebutton.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		removebutton.setTextFill(Color.INDIANRED);
		
		clearbutton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		clearbutton.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		clearbutton.setTextFill(Color.RED);
		
		sortbutton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		sortbutton.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		sortbutton.setTextFill(Color.GREEN);
		
		// Focus Down
		heretf.setFocusTraversable(false);
		cb.setFocusTraversable(false);
		
		HBox hb = new HBox(10);
		hb.getChildren().addAll(herelabel, heretf, numberOfCharacter, cb, totallabel, sortbutton);
		
		VBox vb = new VBox(20);
		vb.getChildren().addAll(addbutton, removebutton, clearbutton);
		vb.setTranslateY(60);
		
		VBox vb2 = new VBox(10);
		vb2.getChildren().addAll(hb, ta);
		
		HBox hb2 = new HBox(10);
		hb2.getChildren().addAll(vb, vb2);
		
		Group group = new Group();
		group.getChildren().add(hb2);
		group.setLayoutX(100);
		group.setLayoutY(50);
		
		Scene scene = new Scene(group, 750, 350);
		
		stage.setTitle("Project One!!!");
		stage.setScene(scene);
		stage.show();
		
	                   ////////   Button Actions     ////////
		
		/* Add button
		 add item to combobox object 
		 and to [name] array of string
		 */
		addbutton.setOnAction(e -> { // Add button action
			if(!heretf.getText().isEmpty()) {
				name[cb.getItems().size()] = heretf.getText().trim();
				cb.getItems().add(heretf.getText());
				cb.getSelectionModel().selectLast();
				totallabel.setText("Total item: "+cb.getItems().size());
				heretf.setText("");
				numberOfCharacter.setText("");
				numberOfCharacter.setTextFill(Color.BLACK);
			}
			else
				new Alert(AlertType.WARNING, "Text Field is Empty!!!").show();
		});
		
		
		/* Remove Button
		 Remove a selected item in the combobox object
		 and call RemoveIndex Method to remove the same item from the [name] array of string
		 */
		removebutton.setOnAction(e -> { // Remove Button action
			if(!cb.getSelectionModel().isEmpty()) {
				RemoveIndex(cb.getSelectionModel().getSelectedIndex());
				cb.getItems().remove(cb.getSelectionModel().getSelectedIndex());
				cb.getSelectionModel().selectLast();
				totallabel.setText("Total Words: "+cb.getItems().size());
			}
			else
				new Alert(AlertType.WARNING, "No Words!!!").show();
		});
		
		/* Clear Button
		 initialize the [name] array of string to null
		 and clear all data in the combobox
		 */
		clearbutton.setOnAction(e -> { // Clear Button
			for(int i = 0 ; i < cb.getItems().size() ; i++)
				name[i] = null;
			cb.getItems().clear();
			totallabel.setText("Total Words: "+ cb.getItems().size());
		});
		
		
		/* Sort Button
		  Sort Button action: call RadixSort method in the RadixSort Class,
		  and print the element of the array into the TextArea
		 */
		sortbutton.setOnAction(e -> { // Sort Button
			if(name[0] == null)
				ta.setText("No Words!!!");
			else {
				String s = "";
				String [] string = Transport();
				for(String str : radix.RadixSort(string, cb.getItems().size()) ) {
					if(str != null)
						s += "  "+str+"  \n";
				}
//				CbArray();
				ta.setText(s);
			}
		});
		
		/*
		 Compute the size of the word in the TextField
		 if exceed 50 then print an alert warning object
		 */
		heretf.setOnKeyTyped(e -> { // Number Of Character: print number of Character in the text side the TextField
			numberOfCharacter.setText(heretf.getText().length()+1+"");
			if(Integer.parseInt(numberOfCharacter.getText()) >= 50) {
				numberOfCharacter.setText("50");
				numberOfCharacter.setTextFill(Color.RED);
				new Alert(AlertType.WARNING, "0-50 Character").show();
				heretf.setText(heretf.getText().substring(0, 49));
			}
			else
				numberOfCharacter.setTextFill(Color.BLACK);
		});
	}
    //////////////////////////   End Start Method     //////////////////////////
	
	private String [] Transport() { // call to put all element in the [name] array to anthor array
		String [] string = new String[cb.getItems().size()];
		for(int i = 0 ; i < string.length ; i++)
			string[i] = name[i];
		return string;
	}
	
	
	
	private void RemoveIndex(int index) { // Call when remove an Word, shift the index of the array
		int size = cb.getItems().size();
		for(int i = index ; i < size ; i++) {
			name[i] = name[i+1];
		}
	}
	
	
//	private void CbArray() { // Clean combo box, to be sorted as the [name] array of String
//		int size = cb.getItems().size();
//		cb.getItems().clear();
//		for(int i = 0 ; i < size ; i++) {
//			cb.getItems().add(name[i]);
//		}
//		cb.getSelectionModel().selectLast();
//	}
	
	public static void main(String[] args) { // Main method
		launch(args);
	}
	
}
