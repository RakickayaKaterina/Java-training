package com.senla.rakickaya.courseplanner.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;



public class Runner {
	private static final Logger logger = Logger.getLogger(Runner.class.getName());
	public static void main(String[] args){		
		try(ServerSocket serverSocket =  new ServerSocket(8888)){
			while(true){
				Socket socket = serverSocket.accept();
				DaemonClientExchanger daemonClientExchanger = new DaemonClientExchanger(socket);
				daemonClientExchanger.start();
			}
			
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		
	}
}
