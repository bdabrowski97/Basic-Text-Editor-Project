package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class OpenFile {
	public static void openThis(TextArea ta) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("inputData"));
		fc.setTitle("Select text file");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
		
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile == null) {
			; // nop
		}
		else {
			try {
				Scanner sc = new Scanner(selectedFile);
				StringBuilder sb = new StringBuilder();
				while (sc.hasNextLine()) {
					sb.append(sc.nextLine());
					sb.append("\n");
				}
				String finalString = sb.toString();
				sc.close();
				ta.setText(finalString);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}	
	}
}
