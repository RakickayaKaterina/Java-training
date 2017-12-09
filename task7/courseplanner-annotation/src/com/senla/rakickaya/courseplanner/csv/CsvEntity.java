package com.senla.rakickaya.courseplanner.csv;

public @interface CsvEntity {
	String filename();

	String valueSeparator() default ",";

	String entityId();

}
