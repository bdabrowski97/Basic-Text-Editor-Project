package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class SaveGraphData {
	public static void saveGraph(long[] loops3Time, long[] loop1Time) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("outputData"));
		try {
			File file = new File("outputData\\GraphData.txt");
			PrintWriter pw = new PrintWriter(file);
			pw.println("-----3 LOOPS-----	(BigO: Aprox. N*3 == N)");
			pw.printf("10%% — Miliseconds %d \n"
					+ "20%% — Miliseconds %d \n"
					+ "30%% — Miliseconds %d \n"
					+ "40%% — Miliseconds %d \n"
					+ "50%% — Miliseconds %d \n"
					+ "60%% — Miliseconds %d \n"
					+ "70%% — Miliseconds %d \n"
					+ "80%% — Miliseconds %d \n"
					+ "90%% — Miliseconds %d \n"
					+ "100%% — Miliseconds %d \n",
					loops3Time[1],loops3Time[2],loops3Time[3],loops3Time[4],loops3Time[5],loops3Time[6],loops3Time[7],loops3Time[8],loops3Time[9],loops3Time[10]);
			pw.println("-----1 LOOP-----	(BigO: Aprox. N)");
			pw.printf("10%% — Miliseconds %d \n"
					+ "20%% — Miliseconds %d \n"
					+ "30%% — Miliseconds %d \n"
					+ "40%% — Miliseconds %d \n"
					+ "50%% — Miliseconds %d \n"
					+ "60%% — Miliseconds %d \n"
					+ "70%% — Miliseconds %d \n"
					+ "80%% — Miliseconds %d \n"
					+ "90%% — Miliseconds %d \n"
					+ "100%% — Miliseconds %d \n",
					loop1Time[1],loop1Time[2],loop1Time[3],loop1Time[4],loop1Time[5],loop1Time[6],loop1Time[7],loop1Time[8],loop1Time[9],loop1Time[10]);
			pw.printf("\n\n THEY BOTH ARE BigO N");
			pw.close();
		} catch (Exception error) {
			error.printStackTrace();
		}
			
			
	}
	
}

