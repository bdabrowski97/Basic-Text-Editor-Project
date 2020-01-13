package p1;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import javafx.scene.control.TextArea;

public class PasteItem {
	public static void pasteThis(TextArea ta) {
		try {
			if (ta.getSelectedText().isEmpty() == false) { //if highlighting some text and you paste, it overwrites the highlighted text
				String textToPaste = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				ta.replaceSelection(textToPaste.toString());
			}
			else {
				String textToPaste = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				if (textToPaste.isEmpty() == true) {
					//do nothing
				}
				else {
					ta.insertText(ta.getCaretPosition(), textToPaste.toString());
				}
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}
