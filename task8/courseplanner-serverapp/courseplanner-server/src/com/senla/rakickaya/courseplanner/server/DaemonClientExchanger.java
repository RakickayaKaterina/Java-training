package com.senla.rakickaya.courseplanner.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.server.api.IFacadeWrapper;

public class DaemonClientExchanger extends Thread {
	private static final Logger logger = Logger.getLogger(DaemonClientExchanger.class.getName());

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	private IFacadeWrapper wrapper;

	public DaemonClientExchanger(Socket socket) {
		super();
		setDaemon(true);
		this.socket = socket;
		init();

	}

	private void init() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			wrapper = (IFacadeWrapper) ServiceDI.getInstance().getObject(IFacadeWrapper.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			while (true) {

				String request = reader.readLine();
				if (request == null && !socket.isConnected()) {
					break;
				}
				String response = wrapper.getResponse(request);
				writer.write(response);
				writer.newLine();
				writer.flush();
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}

	}
}
