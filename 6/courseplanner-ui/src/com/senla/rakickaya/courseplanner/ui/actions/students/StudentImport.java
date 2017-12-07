package com.senla.rakickaya.courseplanner.ui.actions.students;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.facade.Facade;
import com.senla.rakickaya.courseplanner.facade.dataExchange.RequestBuilder;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class StudentImport implements IAction {

	@Override
	public void execute() {
		IFacade facade = Facade.getInstance();
		Input input = Input.getInstance();
		Printer.show("Input path to file");
		String path = input.getString();
		IRequest request = new RequestBuilder().setHead(TagsRequest.PATH, path).build();
		IResponse response = facade.importStudent(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());
	}

}
