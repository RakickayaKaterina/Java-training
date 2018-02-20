package com.senla.rakickaya.courseplanner.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {

	private static Config conf;

	private static Logger logger = Logger.getLogger(Config.class.getName());

	private Properties properties;

	public static Config getInstance() {
		if (conf == null) {
			conf = new Config();
		}
		return conf;
	}

	private Config() {
		properties = new Properties();
		for (String file : Arrays.asList("res/config.properties", "res/jdbc.properties")) {
			try (InputStream inputStream = new FileInputStream(new File(file))) {

				properties.load(inputStream);
			} catch (IOException e) {
				logger.error(new Date() + " " + e.getMessage());
			}
		}
	}

	public int getAmountStudents() {
		String tag = Tags.AMOUNT.name();
		String obj = properties.getProperty(tag);
		int i = Integer.valueOf(obj);
		return i;
	}

	private String getPath(Tags tag) {
		return properties.getProperty(tag.name());
	}

	public String getPathId() {
		return getPath(Tags.GENERATOR_FILE);
	}

	public String getPathStudent() {
		return getPath(Tags.STUDENTS_FILE);
	}

	public String getPathLector() {
		return getPath(Tags.LECTORS_FILE);
	}

	public String getPathRelation() {
		return getPath(Tags.RELATIONS_FILE);
	}

	public String getPathTimeTable() {
		return getPath(Tags.TIME_TABLE_FILE);
	}

	public String getUrlDb() {
		return getPath(Tags.URL);
	}
	
	public String getUserDb() {
		return getPath(Tags.USER);
	}
	public String getPasswordDb() {
		return getPath(Tags.PASSWORD);
	}
	public String getDriverNameJDBC() {
		return getPath(Tags.JDBC_DRIVER);
	}
	public String getPathCourse() {
		return getPath(Tags.COURSES_FILE);
	}
}
