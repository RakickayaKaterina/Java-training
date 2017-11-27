package com.senla.rakickaya.courseplanner.ui.actions.lectors;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
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

public class LectorRemoval implements IAction {

	@Override
	public void execute() {
		IFacade facade = Facade.getInstance();
		IResponse response = facade.getAllLectors();
		@SuppressWarnings("unchecked")
		List<ILector> lectors = (List<ILector>) response.getObject(TagsResponse.DATA);
		Printer.showList(lectors);
		Input input = Input.getInstance();
		Printer.show("Input the number to remove the lector");
		int n = input.getInt();
		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_LECTOR, String.valueOf(lectors.get(n - 1).getId())).build();
		IResponse lectorResponse = facade.removeLector(request);
		Printer.show(lectorResponse.getObject(TagsResponse.MESSAGE).toString());
	}

}
