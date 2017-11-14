package com.senla.rakickaya.courseplanner.ui.actions.lectors;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;
import com.senla.rakickaya.view.dataExchange.RequestBuilder;
import com.senla.rakickaya.view.facade.Facade;

public class LectorAddition implements IAction {

	@Override
	public void execute() {

		Input input = Input.getInstance();
		IFacade facade = Facade.getInstance();
		Printer.show("Input lector's name : ");
		String name = input.getString();

		IRequest request = new RequestBuilder().setHead(TagsRequest.LECTOR_NAME, name).build();
		IResponse response = facade.addLector(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());
	}

}
