package com.senla.rakickaya.courseplanner.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWorker {
	private String filePath;

	public FileWorker(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void write(List<String> list) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))){
			for(String str : list){
				writer.write(str);
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
