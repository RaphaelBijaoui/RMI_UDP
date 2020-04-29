/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import common.MessageInfo;

public class RMIClient {

	private static double time_taken = 0;
	public static void main(String[] args) {

		RMIServerI iRMIServer = null;

		// Check arguments for Server host and number of messages
		if (args.length < 2){
			System.out.println("Needs 2 arguments: ServerHostName/IPAddress, TotalMessageCount");
			System.exit(-1);
		}

		String urlServer = new String(args[0]);
		String registryName = "RMIServer";
		int port_RMI = 1099;
		int numMessages = Integer.parseInt(args[1]);

		// // Initialise Security Manager
		if(System.getSecurityManager()== null){
			System.setSecurityManager(new SecurityManager());
		}

		// Binding to RMIServer

		try{
			Registry web_registry = LocateRegistry.getRegistry(urlServer,port_RMI);
			iRMIServer = (RMIServerI) web_registry.lookup(registryName);

		// Send messages the specified number of times

			for(int i = 0; i<numMessages;i++){
				MessageInfo msg = new MessageInfo(numMessages,i);
				
			//Start timer
			long startTime = System.nanoTime();
			iRMIServer.receiveMessage(msg); //send message (i.e. send and wait for "receive")
			//Stopwatch end
			long endTime = System.nanoTime();
			time_taken += ((endTime - startTime)/1000000.0); //time in MS
			}

		} catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("Time Taken = " + time_taken);
	}
}
