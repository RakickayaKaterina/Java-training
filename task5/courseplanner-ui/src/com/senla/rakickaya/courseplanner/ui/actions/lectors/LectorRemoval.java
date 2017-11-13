package com.senla.rakickaya.courseplanner.ui.actions.lectors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;
import com.senla.rakickaya.view.dataExchange.Request;
import com.senla.rakickaya.view.facade.Facade;

public class LectorRemoval implements IAction{

	@Override
	public void execute() {
		IFacade facade = Facade.getInstance();
		IResponse response = facade.getAllLectors();
		List<ILector> lectors = (List<ILector>) response.getObject(TagsResponse.data);
		Printer.showList(lectors);
		Input input = Input.getInstance();
		int n = input.getInt();
		Map<TagsRequest,String> map = new HashMap<>();
		map.put(TagsRequest.idLector, String.valueOf(lectors.get(n).getId()));
		IRequest request =  new Request(map);
		IResponse lectorResponse = facade.removeLector(request);
		Printer.show(lectorResponse.getObject(TagsResponse.message).toString());
	}

}
