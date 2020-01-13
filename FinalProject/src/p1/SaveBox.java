package p1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SaveBox {
	public static void display(TextArea ta,String msg) {
		Stage window = new Stage();
		window.setTitle("Decision");
		Label msgLabel = new Label(msg);
		Label spacer = new Label("  ");
		Button yes = new Button("Save");
		Button no = new Button("Close Anyway");
		yes.setAlignment(Pos.CENTER_LEFT);
		no.setAlignment(Pos.CENTER_RIGHT);
		HBox buttonBar = new HBox();
		buttonBar.setAlignment(Pos.CENTER);
		buttonBar.getChildren().addAll(yes,spacer,no);
		window.setMinWidth(250);
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane bp = new BorderPane();
		bp.setCenter(msgLabel);
		bp.setBottom(buttonBar);
		bp.setAlignment(buttonBar,Pos.CENTER);
		bp.setAlignment(msgLabel, Pos.CENTER);
		Scene scene = new Scene(bp);
		window.setScene(scene);
		
		//Button actions
		no.setOnAction(e -> {
			ta.clear();
			window.close();
		});
		
		yes.setOnAction(e -> {
			SaveFile.saveThis(ta);
			ta.clear();
			window.close();
		});
		
		 window.showAndWait();
	}

}
