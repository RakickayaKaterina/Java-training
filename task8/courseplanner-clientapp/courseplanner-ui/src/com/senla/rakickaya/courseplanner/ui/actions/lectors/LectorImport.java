package com.senla.rakickaya.courseplanner.ui.actions.lectors;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.dataExchange.RequestBuilder;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class LectorImport implements IAction {
	private ServiceDI service = ServiceDI.getInstance();
	private IFacade facade = (IFacade) service.getObject(IFacade.class);

	@Override
	public void execute() {
		Input input = Input.getInstance();
		Printer.show("Input path to file");
		String path = input.getString();
		IRequest request = new RequestBuilder().setHead(TagsRequest.PATH, path).build();
		IResponse response = facade.importLector(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());
	}

}
