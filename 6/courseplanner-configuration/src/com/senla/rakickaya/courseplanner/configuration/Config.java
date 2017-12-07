package com.senla.rakickaya.courseplanner.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {

	private static Config conf;

	private Properties properties;
	
	private static Logger logger = Logger.getLogger(Config.class.getName());

	public static Config getInstance() {
		if (conf == null) {
			conf = new Config();
		}
		return conf;
	}

	private Config() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File("resourses/config.properties")));
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	public int getAmountStudents() {
		return Integer.valueOf(properties.getProperty(Tags.AMOUNT.name()));
	}

	private String getPath(Tags tag){
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
	public String getPathCourse() {
		return getPath(Tags.COURSES_FILE);
	}
}
