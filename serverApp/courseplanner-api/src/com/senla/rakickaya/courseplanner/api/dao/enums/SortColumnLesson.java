package com.senla.rakickaya.courseplanner.api.dao.enums;

public enum SortColumnLesson {
	DATE("date"), LECTURE_NAME("name");
	private String orderColumn;
	private SortColumnLesson(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderColumn() {
		return orderColumn;
	}

}
