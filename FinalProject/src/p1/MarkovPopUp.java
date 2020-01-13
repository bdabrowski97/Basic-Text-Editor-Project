package p1;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MarkovPopUp  {
	
	public static void display(TextArea ta) {
		Stage window = new Stage();
		
		String taString = ta.toString();
		Scanner scanner = new Scanner(taString);
		
		window.initModality(Modality.APPLICATION_MODAL); //blocks interaction with other windows
		window.setTitle("Decision");
		window.setMinWidth(250);
		
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		
		Label l1 = new Label("Starter word: ");
		Label l2 = new Label("Amount of words: ");
		Label l3 = new Label("");
		
		TextField tf1 = new TextField("word");
		TextField tf2 = new TextField("num");
		
		Button b = new Button("confirm");
		
		hb1.getChildren().addAll(l1,tf1);
		hb2.getChildren().addAll(l2,tf2);
		hb3.getChildren().addAll(b,l3);
		vb.getChildren().addAll(hb1,hb2,hb3);
		
		b.setOnAction(e -> {
			try {
				int wordNum = Integer.parseInt(tf2.getText());
				window.close();
				try {
					Markov.randomize(ta, tf1.getText(), wordNum);
				} catch(Exception error) {
					error.printStackTrace();
				}
				
			} catch(Exception error) {
				l3.setText("ERROR: invalid word/int");
			}
			
		});
		
		BorderPane bp = new BorderPane();
		bp.setCenter(vb);
		
		Scene scene = new Scene(bp);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
