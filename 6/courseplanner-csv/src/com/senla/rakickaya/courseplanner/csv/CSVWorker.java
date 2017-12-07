package com.senla.rakickaya.courseplanner.csv;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWorker {
	private final String PATH_TO_FILE;
	private final File file;

	public CSVWorker(String filePath) {
		this.PATH_TO_FILE = filePath;
		file = new File(PATH_TO_FILE);
	}

	public List<CSVObject> readCSV() {
		String csvObject;
		List<CSVObject> csvObjects = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			while ((csvObject = bufferedReader.readLine()) != null) {
				csvObjects.add(new CSVObject(csvObject));
			}
			return csvObjects;
		} catch (IOException e) {
			return null;
		}
	}

	public void writeCSV(List<CSVObject> csvObjects)  {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			for (CSVObject object : csvObjects) {
				bufferedWriter.write(object.toString());
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}