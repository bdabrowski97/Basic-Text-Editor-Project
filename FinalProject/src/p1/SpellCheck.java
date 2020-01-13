package p1;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SpellCheck {
	public static void display(TextArea ta) {
		Stage window = new Stage();
		window.setTitle("Spell Checker");
		window.setMinWidth(250);
		// window.initModality(Modality.APPLICATION_MODAL); //blocks interaction with other windows
		BorderPane bp = new BorderPane();
		
		
		 HashMap<String,String> hmDic = new HashMap<String,String>(99171);
		
		HBox buttons = new HBox(); // Buttons
		Button closeWindow = new Button("Close");
		Button refreshList = new Button("Check");
		buttons.getChildren().addAll(refreshList,closeWindow);
		
		HBox lists = new HBox(); // Lists
		ListView<String> words = new ListView();
		lists.getChildren().add(words);
		
		try { // create dictionary
			File file = new File("dictionary.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().toLowerCase();
				if (!hmDic.containsKey(line)) {
					hmDic.put(line, line);
				}
				else {
					; // nop
				}
			}
			scanner.close();
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		refreshList.setOnAction(e ->{ // Finds all mispelled words
			words.getItems().clear();
			StringTokenizer textAreaWords = new StringTokenizer(ta.getText().replaceAll("(\\.|,|\\\"|\\?|\\!|-|\\(|\\))", " ")); 	// grab all words written in text area
			while (textAreaWords.hasMoreTokens()) {
				String checkThis = textAreaWords.nextToken();
				if (!hmDic.containsKey(checkThis.toLowerCase())) {
					words.getItems().add(checkThis);
				}	
			}
		});
		
		closeWindow.setOnAction(e ->{window.close(); });
		
		bp.setCenter(words);
		bp.setBottom(buttons);
		
		Scene scene = new Scene(bp);
		window.setScene(scene);
		window.showAndWait();
		
	}
	


}
