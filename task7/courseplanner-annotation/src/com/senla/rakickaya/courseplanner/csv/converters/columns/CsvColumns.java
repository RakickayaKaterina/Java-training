package com.senla.rakickaya.courseplanner.csv.converters.columns;

import java.util.Arrays;

public class CsvColumns {
	private String[] columns;

	public CsvColumns(int count) {
		columns = new String[count];
	}

	public void addString(String str, int numberColumn) {
		if (numberColumn < 0) {
			return;
		}
		if (columns.length <= numberColumn) {
			columns = Arrays.copyOf(columns, numberColumn + 1);
		}
		columns[numberColumn] = str;
	}

	public String getString(int numberColumn) {
		if (numberColumn < 0 || columns.length >= numberColumn) {
			return "";
		}
		return columns[numberColumn];
	}

	private void cuteArray() {
		int length = columns.length;
		for (; length > 0; length--) {
			if (columns[length - 1] != null) {
				break;
			}
		}
		if(length !=columns.length){
			columns = Arrays.copyOf(columns, length);
		}
	}

	public String getConcatString(String separator) {
		StringBuilder result = new StringBuilder();
		cuteArray();
		for (int i = 0; i < columns.length - 1; i++) {
			result.append(columns[i]).append(separator);
		}
		result.append(columns[columns.length - 1]);
		return result.toString();
	}

}
