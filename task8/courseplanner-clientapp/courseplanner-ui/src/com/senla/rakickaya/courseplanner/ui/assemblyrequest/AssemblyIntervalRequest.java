package com.senla.rakickaya.courseplanner.ui.assemblyrequest;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.dataExchange.RequestBuilder;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class AssemblyIntervalRequest implements IAssemblyRequest {
	private String text;

	public AssemblyIntervalRequest(String text) {
		super();
		this.text = text;
	}

	@Override
	public IRequest assemblyRequest() {
		Printer.show(text);
		Input input = Input.getInstance();
		String startDate = input.getString();
		String endDate = input.getString();
		return new RequestBuilder().setHead(TagsRequest.START_DATE, startDate).setHead(TagsRequest.END_DATE, endDate)
				.build();

	}

}
