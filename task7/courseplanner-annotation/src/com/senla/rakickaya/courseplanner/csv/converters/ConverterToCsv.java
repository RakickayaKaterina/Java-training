package com.senla.rakickaya.courseplanner.csv.converters;

import java.lang.reflect.Field;
import java.util.List;

import com.senla.rakickaya.courseplanner.csv.CsvEntity;
import com.senla.rakickaya.courseplanner.csv.CsvProperty;
import com.senla.rakickaya.courseplanner.csv.PropertyType;
import com.senla.rakickaya.courseplanner.csv.converters.columns.CsvColumns;

public class ConverterToCsv {
	public static String convert(Object obj) throws Exception {
		Class<?> cl = obj.getClass();
		CsvEntity csvEntity = cl.getDeclaredAnnotation(CsvEntity.class);
		if (csvEntity == null) {
			throw new Exception("No Csv Entity");
		}
		Field[] fields = cl.getDeclaredFields();
		CsvColumns columns = new CsvColumns(fields.length);
		for (Field field : fields) {
			CsvProperty csvProperty = field.getDeclaredAnnotation(CsvProperty.class);
			if (csvProperty == null) {
				continue;
			}
			String valueField;
			if (csvProperty.propertyType() == PropertyType.SimpleProperty) {
				valueField = getSimpleProperty(field, obj);
			} else {
				valueField = getComposityProperty(field, obj);
			}
			columns.addString(valueField, csvProperty.columnNumber());
		}
		return columns.getConcatString(csvEntity.valueSeparator());

	}

	private static String getSimpleProperty(Field field, Object object) {
		String property = "";
		try {
			field.setAccessible(true);
			property = String.valueOf(field.get(object));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.getMessage();
		}
		return property;

	}

	private static String getComposityProperty(Field field, Object object) {
		String result = "";
		try {
			Class<?> typeField = field.getType();
			if (typeField == List.class) {
				result = getListProperty(field, object);
			} else {
				result = getValue(field, object);
			}

		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static String getValue(Field field, Object object)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		CsvProperty csvProperty = field.getDeclaredAnnotation(CsvProperty.class);
		String keyId = csvProperty.keyField();
		field.setAccessible(true);
		Object fieldValue = field.get(object);
		Class<?> typeFieldValue = fieldValue.getClass();
		Field keyField = typeFieldValue.getDeclaredField(keyId);
		keyField.setAccessible(true);
		Object obj  = keyField.get(fieldValue);
		return String.valueOf(obj);

	}

	private static String getListProperty(Field field, Object object) {
		StringBuilder result = new StringBuilder();
		try {
			field.setAccessible(true);
			Object fieldValue = field.get(object);
			List<?> list = (List<?>) fieldValue;
			CsvProperty property = field.getDeclaredAnnotation(CsvProperty.class);
			String keyField = property.keyField();
			String separator = property.separartorArray();
			for (Object obj : list) {
				Class<?> classObj = obj.getClass();
				Field keyObjField = classObj.getDeclaredField(keyField);
				keyObjField.setAccessible(true);
				String value = String.valueOf(keyObjField.get(obj));
				result.append(value)
						.append(separator);
			}
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();

	}
}
