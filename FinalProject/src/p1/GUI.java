package p1;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {
		
	DecimalFormat df = new DecimalFormat("#.00"); // Makes the Flesch Score Decimal look gud
	
	//Toggles for the status bar
	boolean wordToggle = false;
	boolean sentenceToggle = false;
	boolean scoreToggle = false;
	
	int wordNum = 0;
	int sentNum = 0;		
	int score = 0; // delete this and just use a scorecheck class to add to the label
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane(); //main window
			
		//creating the menus for the main menu bar
		Menu fileMenu = new Menu("File");
		Menu viewMenu = new Menu("View");
		Menu editMenu = new Menu("Edit");
		Menu extraMenu = new Menu("Extra");
		
		//Text area for editing/writing files
		TextArea ta = new TextArea();
		ta.setWrapText(true);
		ta.setPadding(new Insets(0,10,0,10));
		
		//Status Bars
		HBox statusBar = new HBox();
		Label wordLabel = new Label("");
		Label sentenceLabel = new Label("");
		Label scoreLabel = new Label("");
		statusBar.getChildren().addAll(wordLabel,sentenceLabel,scoreLabel);
		statusBar.setAlignment(Pos.CENTER);
		MenuBar topMenu = new MenuBar(fileMenu,viewMenu,editMenu,extraMenu); // Creating the actual bars
		
		//creating menu items and adding to bars
		MenuItem itemNew = new MenuItem("New"); // done — maybe add save function?
		MenuItem itemOpen = new MenuItem("Open"); // done
		MenuItem itemClose = new MenuItem("Close"); // done — maybe add save function?
		MenuItem itemSave = new MenuItem("Save"); // done
		MenuItem itemExit = new MenuItem("Exit"); // done
		fileMenu.getItems().addAll(itemNew,itemOpen,itemSave,itemClose,itemExit);
	
		MenuItem itemWC = new MenuItem("Word Count"); // toggle works, updates via String Tokenizer, doesn't update when using menu functions
		MenuItem itemSC = new MenuItem("Sentence Count"); // toggle works, live updates however we have to make it not count Dr.,Mr., ..., !?, etc as seperate sentences per symbol
		MenuItem itemFS = new MenuItem("Flesch Score"); // toggle works
		viewMenu.getItems().addAll(itemWC,itemSC,itemFS);
		
		MenuItem itemCopy = new MenuItem("Copy"); // done
		MenuItem itemCut = new MenuItem("Cut"); // done
		MenuItem itemDelete = new MenuItem("Delete"); // done
		MenuItem itemPaste = new MenuItem("Paste"); // done
		MenuItem itemMarkov = new MenuItem("Markov");
		editMenu.getItems().addAll(itemCopy,itemCut,itemDelete,itemPaste,itemMarkov);
		
		MenuItem useGraph = new MenuItem("Use Graph");
	//	MenuItem oneLoop = new MenuItem("One Loop");
		MenuItem useSpellCheck = new MenuItem("Check Spelling");
		
		extraMenu.getItems().addAll(useGraph,useSpellCheck);
		
		//FILE MENU//
		itemNew.setOnAction(e -> {ta.clear(); }); // New clears the text area
		itemClose.setOnAction(e -> {if (!ta.getText().isEmpty()) {SaveBox.display(ta,"Save before closing file?");} }); // Closes the textarea with optional save
		itemOpen.setOnAction(e -> {OpenFile.openThis(ta); }); // Opens a text file
		itemSave.setOnAction(e -> {SaveFile.saveThis(ta); }); // Saves a text file
		itemExit.setOnAction(e ->{if (!ta.getText().isEmpty()) {SaveBox.display(ta, "Save before closing program?");} System.exit(0); }); // Exit closes the program with optional save
		
		//VIEW MENU//
		// Update the labels if in use
		ta.setOnKeyPressed(e -> { // updates the word count per keystroke if on
			if (wordToggle == true) {
				wordNum = WordCheck.wordCount(ta);
				wordLabel.setText(wordNum + " words | ");
			}
			if (sentenceToggle == true) {
				sentNum = SentenceCheck.sentenceCount(ta);	
				 sentenceLabel.setText(sentNum + " sentences | ");
			}
			if (scoreToggle == true) {
				scoreLabel.setText(df.format(FleschCheck.calculateFleschScore(ta)) + " fleschScore |");
			}
		});
		
		itemWC.setOnAction(e -> { // Word count toggle
			if (wordToggle == false) {
				wordLabel.setText(WordCheck.wordCount(ta) + " words | ");
				wordToggle = true;
			}
			else {
				wordToggle = false;
				wordLabel.setText("");
			}
		});
		itemSC.setOnAction(e -> { // Sentence count toggle
			if (sentenceToggle == false) {
				sentenceLabel.setText(SentenceCheck.sentenceCount(ta) + " sentences | ");
				sentenceToggle = true;
			}
			else {
				sentenceToggle = false;
				sentenceLabel.setText("");
			}
		});
		itemFS.setOnAction(e -> { // flesch score toggle
			if (scoreToggle == false) {
				scoreLabel.setText(df.format(FleschCheck.calculateFleschScore(ta)) + " fleschScore |");
				scoreToggle = true;
			}
			else {
				scoreToggle = false;
				scoreLabel.setText("");
			}
		});
		
		//EDIT MENU//
		itemCopy.setOnAction( e -> {CopyItem.copyThis(ta); }); // Copy copies the selected text to clipboard
		itemCut.setOnAction(e -> {CutItem.cutThis(ta); }); // Cut cuts the selected text and copies to clipboard
		itemDelete.setOnAction(e -> {ta.replaceSelection(""); }); // Delete deletes the selected text
		itemPaste.setOnAction(e -> {PasteItem.pasteThis(ta); }); // Paste pastes the contents from the clipboard to textarea
		itemMarkov.setOnAction(e -> {MarkovPopUp.display(ta); }); // Markov takes all words from the text area and randomly generates a new paragraph
		
		//EXTRA MENU//
		useGraph.setOnAction(e->{ GraphBox.display(ta); }); // displays the graph comparisons
		useSpellCheck.setOnAction(e ->{ SpellCheck.display(ta); });
		
		// Setting up the borderpane window
		bp.setTop(topMenu);
		bp.setCenter(ta);
		bp.setBottom(statusBar);
		
		Scene scene = new Scene(bp,500,500);
		stage.setScene(scene);
		stage.setTitle("Final Project — Brandon Dabrowski");
		stage.show();
	}

}
