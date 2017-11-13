package com.senla.rakickaya.courseplanner.ui;

import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.ui.actions.courses.info.PrinterInformation;
import com.senla.rakickaya.courseplanner.ui.actions.courses.info.PrinterInformation.IListResponse;
import com.senla.rakickaya.view.facade.Facade;

public class Buider {
	private Menu rootMenu;

	public void buildMenu(){
		MenuItem itemSortedCourseByAlf = new MenuItem();
		itemSortedCourseByAlf.setAction(new PrinterInformation(new IListResponse() {
			
			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByAlphabet();
			}
		}));
		MenuItem itemSortedCourseByDate = new MenuItem();
		itemSortedCourseByDate.setAction(new PrinterInformation(new IListResponse() {
			
			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByStartDate();
			}
		}));
		MenuItem itemSortedCourseByLector = new MenuItem();
		itemSortedCourseByLector.setAction(new PrinterInformation(new IListResponse() {
			
			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByLectorName();
			}
		}));
		MenuItem itemSortedCourseByCountStudent = new MenuItem();
		itemSortedCourseByCountStudent.setAction(new PrinterInformation(new IListResponse() {
			
			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByCountStudents();
			}
		}));
		//TODO build
	}
	
	public Menu getRootMenu() {
		return rootMenu;
	}
	
}
