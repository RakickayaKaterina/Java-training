package com.senla.rakickaya.courseplanner.dependency;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ServiceDI {
	private static ServiceDI serviceDI;
	private static Logger logger = Logger.getLogger(ServiceDI.class);
	private Map<Class<?>, Object> mapObjects;
	private Properties properties;

	private ServiceDI() {
		mapObjects = new HashMap<>();
		try (InputStream inputStream = new FileInputStream(new File("res/di.properties"))) {
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	public static ServiceDI getInstance() {
		if (serviceDI == null) {
			return serviceDI = new ServiceDI();
		}
		return serviceDI;
	}

	public Object getObject(Class<?> cl) {
		try {
			if (mapObjects.containsKey(cl)) {
				return mapObjects.get(cl);
			}
			String name =properties.getProperty(cl.getName());
			Class<?> clazz = Class.forName(name);
			Object obj = clazz.newInstance();
			mapObjects.put(cl, obj);
			return obj;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.error(new Date() + " " + e.getMessage());
			return null;
		}

	}

}