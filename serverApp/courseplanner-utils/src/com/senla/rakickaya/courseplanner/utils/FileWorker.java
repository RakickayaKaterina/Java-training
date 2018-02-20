package com.senla.rakickaya.courseplanner.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileWorker {

	private static final Logger logger = Logger.getLogger(FileWorker.class.getName());
	private String filePath;

	public FileWorker(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void write(List<String> list) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
			for (String str : list) {
				writer.write(str);
				writer.newLine();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public List<String> read() {
		List<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
			String buffer = null;
			while ((buffer = reader.readLine()) != null) {
				list.add(buffer);
			}

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
}
