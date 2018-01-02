package com.senla.rakickaya.courseplanner.csv;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWorker {
	private final String PATH_TO_FILE;
	private final File file;

	public CsvWorker(String filePath) {
		this.PATH_TO_FILE = filePath;
		file = new File(PATH_TO_FILE);
	}

	public List<CsvObject> readCSV() {
		String csvObject;
		List<CsvObject> csvObjects = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			while ((csvObject = bufferedReader.readLine()) != null) {
				csvObjects.add(new CsvObject(csvObject));
			}
			return csvObjects;
		} catch (IOException e) {
			return null;
		}
	}

	public void writeCSV(List<CsvObject> csvObjects)  {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			for (CsvObject object : csvObjects) {
				bufferedWriter.write(object.toString());
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}