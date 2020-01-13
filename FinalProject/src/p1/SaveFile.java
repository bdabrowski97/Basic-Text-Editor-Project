package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class SaveFile {
	public static void saveThis(TextArea ta) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
		fc.setTitle("Save text file");
		fc.setInitialDirectory(new File("inputData"));
		
		File saveFile = fc.showSaveDialog(null);
		if (saveFile == null) {
			//do nothing
		}
		else {
			try {
				PrintWriter pw = new PrintWriter(saveFile);
				pw.println(ta.getText());
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
