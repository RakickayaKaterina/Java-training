package com.senla.rakickaya.courseplanner.ui.assembly_request;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;
import com.senla.rakickaya.view.dataExchange.RequestBuilder;

public class AssemblyDateRequest implements IAssemblyRequest {
	private String text;
	
	public AssemblyDateRequest(String text) {
		super();
		this.text = text;
	}

	@Override
	public IRequest assemblyRequest() {
		Printer.show(text);
		Input input = Input.getInstance();
		String date = input.getString();
		return new RequestBuilder().setHead(TagsRequest.DATE, date).build();
	}

}
