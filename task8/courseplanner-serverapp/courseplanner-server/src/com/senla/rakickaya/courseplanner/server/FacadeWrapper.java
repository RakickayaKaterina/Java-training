package com.senla.rakickaya.courseplanner.server;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.Intentions;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.server.api.IFacadeWrapper;
import com.senla.rakickaya.courseplanner.utils.Decoder;

public class FacadeWrapper implements IFacadeWrapper {

	private static final Logger logger = Logger.getLogger(FacadeWrapper.class.getName());

	private IFacade facade;
	private Decoder decoder;

	public FacadeWrapper() {
		super();
		decoder = new Decoder();
		facade = (IFacade) ServiceDI.getInstance().getObject(IFacade.class);
	}

	@Override
	public String getResponse(String request) {
		IRequest req = decoder.decode(request);
		IResponse response = null;
		Intentions intentions = req.getIntent();
		Method method;
		try {
			Class<? extends IFacade> clFacade = facade.getClass();
			boolean hasParam = intentions.isHasParam();
			method = hasParam ? clFacade.getDeclaredMethod(intentions.getIntention(), IRequest.class)
					: clFacade.getDeclaredMethod(intentions.getIntention());
			response = hasParam ? (IResponse) method.invoke(facade, req) : (IResponse) method.invoke(facade);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		String decodeResponse = decoder.encode(response);
		return decodeResponse;
	}

}
