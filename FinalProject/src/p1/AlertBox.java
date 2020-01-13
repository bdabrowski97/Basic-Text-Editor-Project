package p1;

import java.util.Scanner;

import javafx.geometry.Pos;
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

public class AlertBox {
	public static void display(String title, String message) {
		Stage window = new Stage();
		window.setTitle(title);
		Label msg = new Label(message);
		window.setMinWidth(250);
		window.initModality(Modality.APPLICATION_MODAL); //blocks interaction with other windows
		BorderPane bp = new BorderPane();
		Button b = new Button("OK");
		b.setOnAction(e -> {window.close(); });
		bp.setTop(msg);
		bp.setBottom(b);
		bp.setAlignment(b, Pos.CENTER);
		bp.setAlignment(msg, Pos.CENTER);
		Scene scene = new Scene(bp);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
