package com.senla.rakickaya.courseplanner.ui.actions;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IEntity;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class PrinterInfo implements IAction {
	private IListResponse listResponse;

	public PrinterInfo(IListResponse listResponse) {
		super();
		this.listResponse = listResponse;
	}

	@Override
	public void execute() {
		IResponse response = listResponse.getResponse();
		List<IEntity> list = (List<IEntity>) response.getObject(TagsResponse.DATA);
		Printer.showList(list);
	}

	public interface IListResponse {
		public IResponse getResponse();
	}
}
