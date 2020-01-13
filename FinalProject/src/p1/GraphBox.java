package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GraphBox {
	
public static void display(TextArea ta) { // BASED ON WHAT WAS SAID IN CLASS (USING PERCENTAGES OF THE TOTAL STRING LENGTH)
	
		System.out.println("text area ver.");
		
		Stage window = new Stage();
		window.setTitle("Graph");
		window.initModality(Modality.APPLICATION_MODAL); // blocks interaction with other windows
		BorderPane bp = new BorderPane();
		
		TextArea useThisTA = new TextArea(); // used for getting data on the strings
		
		long[] allTimes3Loops = new long[11]; // stores all the total times we'll be using
		long[] allTimes1Loop = new long[11]; // stores all the total times for single loop
		
		
		double percentToUse = 0.0; // start with ten percent
		int wordsToGrab = 0;
		
		int num = 0;
		
		try {
			while (percentToUse <= 1) {
				
				StringTokenizer st = new StringTokenizer(ta.getText());
				double inBetween = st.countTokens() * percentToUse;
				wordsToGrab = (int) (inBetween);
			
				//	System.out.println(wordsToGrab); // DEBUG
				
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < wordsToGrab; i++) {
					sb.append(st.nextToken());
				}
				String finalString = sb.toString();
				useThisTA.setText(finalString);
				
				// Now we get the times
				// ---3 Loops ----
				long startTime = System.currentTimeMillis();
				WordCheck.wordCount(useThisTA);
				SentenceCheck.sentenceCount(useThisTA);
				FleschCheck.syllableCount(useThisTA,false);
				long endTime = System.currentTimeMillis();
				allTimes3Loops[num] = (endTime - startTime);
				
				// ---1 Loop ----
				long starttime = System.currentTimeMillis();
				OneLoop.analyze(useThisTA.getText(),false);
				long endtime = System.currentTimeMillis();
				allTimes1Loop[num] = (endtime-starttime);
				
				percentToUse+=.1;
				num++;
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		// Creating LineChart
		Button button = new Button("Close Window");
		button.setOnAction(e ->{ SaveGraphData.saveGraph(allTimes3Loops, allTimes1Loop); window.close(); });
		NumberAxis xAxisWords = new NumberAxis(0,100,10);
		NumberAxis yAxisTime = new NumberAxis(0,300,10);
		LineChart graph = new LineChart(xAxisWords,yAxisTime);
		xAxisWords.setLabel("Words Percentage");
		yAxisTime.setLabel("Time (Miliseconds)");
		XYChart.Series series = new XYChart.Series();
		series.setName("3 Loops");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("1 Loop");
		
		// Creating data points
		series.getData().add(new XYChart.Data(10,allTimes3Loops[1]));
		series.getData().add(new XYChart.Data(20,allTimes3Loops[2]));
		series.getData().add(new XYChart.Data(30,allTimes3Loops[3]));
		series.getData().add(new XYChart.Data(40,allTimes3Loops[4]));
		series.getData().add(new XYChart.Data(50,allTimes3Loops[5]));
		series.getData().add(new XYChart.Data(60,allTimes3Loops[6]));
		series.getData().add(new XYChart.Data(70,allTimes3Loops[7]));
		series.getData().add(new XYChart.Data(80,allTimes3Loops[8]));
		series.getData().add(new XYChart.Data(90,allTimes3Loops[9]));
		series.getData().add(new XYChart.Data(100,allTimes3Loops[10]));
		
		series2.getData().add(new XYChart.Data(10,allTimes1Loop[1]));
		series2.getData().add(new XYChart.Data(20,allTimes1Loop[2]));
		series2.getData().add(new XYChart.Data(30,allTimes1Loop[3]));
		series2.getData().add(new XYChart.Data(40,allTimes1Loop[4]));
		series2.getData().add(new XYChart.Data(50,allTimes1Loop[5]));
		series2.getData().add(new XYChart.Data(60,allTimes1Loop[6]));
		series2.getData().add(new XYChart.Data(70,allTimes1Loop[7]));
		series2.getData().add(new XYChart.Data(80,allTimes1Loop[8]));
		series2.getData().add(new XYChart.Data(90,allTimes1Loop[9]));
		series2.getData().add(new XYChart.Data(100,allTimes1Loop[10]));
	
		
		graph.getData().add(series); // adds data to the graph
		graph.getData().add(series2);
		
		bp.setCenter(graph);
		bp.setBottom(button);
		bp.setAlignment(button, Pos.CENTER);
		
		Scene scene = new Scene(bp);
		window.setScene(scene);
		window.showAndWait();
		
	}

	public static void displayByHandout(TextArea ta) { // BASED ON WHAT WAS GIVEN ON THE PROJECT HANDOUT (USING 100,1000,10000, & 100000 WORD STRINGS)
		
		System.out.println("text area ver.");
		
		Stage window = new Stage();
		window.setTitle("Graph");
		window.initModality(Modality.APPLICATION_MODAL); // blocks interaction with other windows
		BorderPane bp = new BorderPane();
		
		TextArea useThisTA = new TextArea(); // used for getting data on the strings
		
		long[] allTimes3Loops = new long[4]; // stores all the total times we'll be using
		long[] allTimes1Loop = new long[4]; // stores all the total times for single loop
		
		int wordsToGrab = 100;
		
		try {
			for (int i = 0; i < allTimes3Loops.length; i++) {
				if (i == 1) { wordsToGrab = 1000;}
				if (i == 2) { wordsToGrab = 10000;}
				if (i == 3) { wordsToGrab = 100000;}
				
				StringTokenizer st = new StringTokenizer(ta.getText());
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < wordsToGrab; j++) {
					sb.append(st.nextToken());
				}
				String finalString = sb.toString();
				useThisTA.setText(finalString);
				
				// Now we get the times
				// ---3 Loops ----
				long startTime = System.currentTimeMillis();
				WordCheck.wordCount(useThisTA);
				SentenceCheck.sentenceCount(useThisTA);
				FleschCheck.syllableCount(useThisTA,false);
				long endTime = System.currentTimeMillis();
				allTimes3Loops[i] = (endTime - startTime);
				
				// ---1 Loop ----
				long starttime = System.currentTimeMillis();
				OneLoop.analyze(useThisTA.getText(),false);
				long endtime = System.currentTimeMillis();
				allTimes1Loop[i] = (endtime-starttime);
			}
		} catch (Exception error) {
			ta.appendText("\n NOT ENOUGH WORDS IN TEXT AREA TO MAKE GRAPH");
		}
		
		// Creating LineChart
		Button button = new Button("Close Window");
		button.setOnAction(e ->{ SaveGraphData.saveGraph(allTimes3Loops, allTimes1Loop); window.close(); });
		NumberAxis xAxisWords = new NumberAxis(0,100000,10000);
		NumberAxis yAxisTime = new NumberAxis(0,100,10);
		LineChart graph = new LineChart(xAxisWords,yAxisTime);
		xAxisWords.setLabel("Words");
		yAxisTime.setLabel("Time (Miliseconds)");
		XYChart.Series series = new XYChart.Series();
		series.setName("3 Loops");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("1 Loop");
		
		// Creating data points
		series.getData().add(new XYChart.Data(100,allTimes3Loops[0]));
		series.getData().add(new XYChart.Data(1000,allTimes3Loops[1]));
		series.getData().add(new XYChart.Data(10000,allTimes3Loops[2]));
		series.getData().add(new XYChart.Data(100000,allTimes3Loops[3]));
		
		series2.getData().add(new XYChart.Data(100,allTimes1Loop[0]));
		series2.getData().add(new XYChart.Data(1000,allTimes1Loop[1]));
		series2.getData().add(new XYChart.Data(10000,allTimes1Loop[2]));
		series2.getData().add(new XYChart.Data(100000,allTimes1Loop[3]));
		
		graph.getData().add(series); // adds data to the graph
		graph.getData().add(series2);
		
		bp.setCenter(graph);
		bp.setBottom(button);
		
		Scene scene = new Scene(bp);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
