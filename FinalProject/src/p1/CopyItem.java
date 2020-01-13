package p1;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javafx.scene.control.TextArea;

public class CopyItem {
	public static void copyThis(TextArea ta) {
		if (ta.getSelectedText().isEmpty() == true) {/*do nothing*/ }
		else {
			String textToCopy = ta.getSelectedText().toString();
			StringSelection strSel = new StringSelection(textToCopy);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(strSel, null);
		}
	}
}
