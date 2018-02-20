package com.senla.rakickaya.courseplanner.api.dao.enums;

public enum SortColumnCourse {
	START_DATE("start_date"), NAME_COURSE("name"), LECTOR_NAME("nameLector");
	private String orderColumn;
	private SortColumnCourse(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	
}
