package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class BodyFatReader {
	

	public static List<Double> bodyfatReader(String fileName) throws IOException {
		List<Double>bodyfatHistory = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("Bodyfat history:")) {
					line = reader.readLine(); // skip the header line
					while (line != null && line.matches("\\d+\\.\\s+\\d+\\.\\d+")) {
						double bodyfatReader = Double.parseDouble(line.substring(line.indexOf(' ') + 1));
						bodyfatHistory.add(bodyfatReader);
						line = reader.readLine();
					}
				} else {
					line  = reader.readLine();
				}
			}
		}
		
		 return bodyfatHistory ;
		 }
}