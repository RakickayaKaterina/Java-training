package com.senla.rakickaya.courseplanner.ui.actions;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IEntity;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.assembly_request.IAssemblyRequest;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class PrinterInfoParam implements IAction {
	private IListResponse listResponse;
	private IAssemblyRequest assemblyRequest;

	public PrinterInfoParam(IListResponse listResponse, IAssemblyRequest assemblyRequest) {
		super();
		this.listResponse = listResponse;
		this.assemblyRequest = assemblyRequest;
	}

	@Override
	public void execute() {
		IRequest request = assemblyRequest.assemblyRequest();
		IResponse response = listResponse.getResponse(request);
		List<IEntity> list = (List<IEntity>) response.getObject(TagsResponse.DATA);
		Printer.showList(list);
	}

	public interface IListResponse {
		public IResponse getResponse(IRequest request);
	}
}
