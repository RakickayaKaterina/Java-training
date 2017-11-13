package com.senla.rakickaya.courseplanner.ui.actions.courses.info;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IEntity;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class PrinterInformation implements IAction {
	private IListResponse listResponse;

	public PrinterInformation(IListResponse listResponse) {
		super();
		this.listResponse = listResponse;
	}

	@Override
	public void execute() {
		IResponse response = listResponse.getResponse();
		List<IEntity> list = (List<IEntity>) response.getObject(TagsResponse.data);
		Printer.showList(list);
	}


	public interface IListResponse {
		public IResponse getResponse();
	}
}
