package com.senla.rakickaya.courseplanner.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {
	PropertyType propertyType();
	int columnNumber();
	String keyField() default "id";
	Class<?> typeField() default long.class;
	String separartorArray() default " ";
	
}
