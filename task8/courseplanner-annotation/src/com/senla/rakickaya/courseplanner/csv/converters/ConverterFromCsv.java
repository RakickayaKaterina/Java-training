package com.senla.rakickaya.courseplanner.csv.converters;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.csv.CsvEntity;
import com.senla.rakickaya.courseplanner.csv.CsvProperty;
import com.senla.rakickaya.courseplanner.csv.PropertyType;
import com.senla.rakickaya.courseplanner.csv.converters.entities.CsvColumns;
import com.senla.rakickaya.courseplanner.csv.converters.entities.CsvResponse;

public class ConverterFromCsv {
	private static final Logger logger = Logger.getLogger(ConverterFromCsv.class.getName());

	public static CsvResponse convert(String str, Class<?> objClass) throws Exception {
		CsvResponse response = new CsvResponse();
		CsvEntity csvEntity = objClass.getDeclaredAnnotation(CsvEntity.class);
		if (csvEntity == null) {
			throw new Exception("This class isn't csv entity");
		}
		String separator = csvEntity.valueSeparator();
		CsvColumns columns = new CsvColumns(str.split(separator));
		Object resultEntity = objClass.newInstance();
		for (Field field : objClass.getDeclaredFields()) {
			CsvProperty csvProperty = field.getDeclaredAnnotation(CsvProperty.class);
			if (csvProperty == null) {
				continue;
			}
			int column = csvProperty.columnNumber();
			if (csvProperty.propertyType() == PropertyType.SimpleProperty) {
				setSimpleProperty(resultEntity, columns.getString(column), field);
			} else {
				setRelationProperty(resultEntity, columns.getString(column), field, response);
			}
		}
		response.setEntity(resultEntity);
		return response;
	}

	private static void setRelationProperty(Object resultEntity, String string, Field field, CsvResponse response) {

		String nameField = field.getName();
		CsvProperty csvProperty = field.getDeclaredAnnotation(CsvProperty.class);
		Class<?> typeField = field.getType();
		Object relation = null;
		try {
			if (typeField == List.class) {
				relation = getList(string.split(csvProperty.separartorArray()),
						csvProperty.typeField());

			} else {

				Class<?> typeKeyValue = csvProperty.typeField();
				relation = cast(typeKeyValue, string);
			}
		} catch (SecurityException | ParseException e) {
			logger.error(e.getMessage());
		}
		if (relation != null) {
			response.getRelation().put(nameField, relation);
		}

	}

	private static List<?> getList(String[] strings, Class<?> cl) {
		List<Object> list = new ArrayList<>();
		for (String str : strings) {
			if (str != null && !str.equals("")) {
				try {
					list.add(cast(cl, str));
				} catch (ParseException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return list;

	}

	private static void setSimpleProperty(Object resultEntity, String string, Field field) {
		try {
			Class<?> typeField = field.getType();
			Object value = cast(typeField, string);
			field.setAccessible(true);
			field.set(resultEntity, value);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

	}

	private static Object cast(Class<?> typeField, String string) throws ParseException {
		if (typeField == int.class || typeField == Integer.class) {
			return Integer.valueOf(string);
		}
		if (typeField == String.class) {
			return string;
		}
		if (typeField == long.class || typeField == Long.class) {
			return Long.valueOf(string);
		}
		if (typeField == Date.class) {
			return DateFormat.getDateInstance(DateFormat.DEFAULT).parse(string);
		}
		return null;

	}
}
