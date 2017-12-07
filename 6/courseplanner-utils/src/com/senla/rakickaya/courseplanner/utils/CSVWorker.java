package com.senla.rakickaya.courseplanner.utils;
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

	public List<String> readCSV() {
		String csvObject;
		List<String> csvObjects = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.readLine();
			while ((csvObject = bufferedReader.readLine()) != null) {
				csvObjects.add(csvObject);
			}
			return csvObjects;
		} catch (IOException e) {
			return null;
		}
	}

	public void writeCSV(List<String> csvObjects, String headerCSV)  {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			csvObjects.add(0, headerCSV);
			for (String object : csvObjects) {
				bufferedWriter.write(object);
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}