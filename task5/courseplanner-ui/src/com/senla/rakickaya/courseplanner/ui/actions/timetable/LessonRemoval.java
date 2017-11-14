package com.senla.rakickaya.courseplanner.ui.actions.timetable;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILecture;
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

public class LessonRemoval implements IAction {

	@Override
	public void execute() {
		Input input = Input.getInstance();
		IFacade facade = Facade.getInstance();

		IResponse responseLecture = facade.getAllLectures();
		List<ILecture> lectures = (List<ILecture>) responseLecture.getObject(TagsResponse.DATA);
		Printer.showList(lectures);
		Printer.show("Input the number of the lecture to remove time table");
		int positionLecture = input.getInt();

		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_LECTURE, String.valueOf(lectures.get(positionLecture - 1).getId())).build();
		IResponse response = facade.removeTimeTableForLecture(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());
	}

}
