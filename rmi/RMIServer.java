/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.rmi.registry.*;

import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;
	private int num_receivedMessages;

	public RMIServer() throws RemoteException {
	}

	public void receiveMessage(MessageInfo msg) throws RemoteException {

		// On receipt of first message, initialise the receive buffer
		if (totalMessages == -1) {
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
		}

		// Log receipt of the message
		receivedMessages[msg.messageNum] = 1;
		num_receivedMessages++;

		// If this is the last expected message, then identify
		// any missing messages.
		if (msg.messageNum == totalMessages - 1) { // TCP/IP sends all messages sequentially
			print_missing_messages();
			num_receivedMessages = 0;
			totalMessages = -1;
		}

	}

	public static void main(String[] args) {

		RMIServer rmis = null;

		// // Initialise Security Manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			// Instantiate the server class
			rmis = new RMIServer();
			String server_url = "RMIServer";

			// Bind to RMI registry
			rebindServer(server_url, rmis);

		} catch (Exception e) {
			System.out.println("Error while binding. Trace: ");
			e.printStackTrace();
		}
	}

	protected static void rebindServer(String serverURL, RMIServer server) {

		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind(serverURL, server);
			System.out.println("Running");

		} catch (Exception e) {
			System.out.println("Problem binding in server. Trace: ");
			e.printStackTrace();
		}

	}

	public void print_missing_messages() {
		int missing_messages = totalMessages - num_receivedMessages;

		// Print any lost messages
		if (missing_messages > 0) {
			System.out.println("Number of missing messages: " + missing_messages);

			for (int i = 0; i < receivedMessages.length; ++i) {
				if (receivedMessages[i] == 0) {
					System.out.print(i + " ");
				}
			}
			System.out.println(num_receivedMessages + "/" + totalMessages + " messages received");
		} else {
			System.out.println("ALL " + totalMessages + "/" + totalMessages + " messages received! :) ");
		}
	}

}
