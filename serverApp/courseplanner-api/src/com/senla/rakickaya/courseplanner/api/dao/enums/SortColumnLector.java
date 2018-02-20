package com.senla.rakickaya.courseplanner.api.dao.enums;

public enum SortColumnLector {
	NAME_LECTOR("name");
	private String orderColumn;
	private SortColumnLector(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderColumn() {
		return orderColumn;
	}

}
