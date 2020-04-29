/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

import common.MessageInfo;

public class UDPServer {

	private DatagramSocket recvSoc;
	private int totalMessages = -1;
	private int[] receivedMessages;
	private boolean close;
	private long timer_start;


	// SocketTimeoutException -> signals timeout occurred on Socket read/accept
	private void run() throws SocketTimeoutException {
		int pacSize;
		byte[] pacData;
		DatagramPacket pac;

		//  Receive the messages and process them by calling processMessage(...).
		// Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever
		close = false;

		try {
			pacSize = 65000; // Initially set to near maximum packet size of 65,507 in bytes. NOTE: vary this
												// during testing.

			recvSoc.setSoTimeout(40000);
			while (!close) {
				pacData = new byte[pacSize];
				pac = new DatagramPacket(pacData, pacSize);
				recvSoc.receive(pac);
				String msg = new String(pac.getData(), 0, pac.getLength());
				processMessage(msg);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void processMessage(String data) {

		MessageInfo msg = null;
		String message = new String(data.split("\n")[0]);

		// Use the data to construct a new MessageInfo object
		try {
			msg = new MessageInfo(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On receipt of first message, initialise the receive buffer
		if (totalMessages == -1) {
			receivedMessages = new int[msg.totalMessages];

			totalMessages = msg.totalMessages;

			// start server timer
			timer_start = System.currentTimeMillis();
		}

		// Log receipt of the message
		receivedMessages[msg.messageNum] = (msg.messageNum + 1);

		// If this is the last expected message, then identify
		// any missing messages
		if (msg.messageNum == (msg.totalMessages - 1)) {
			close = true;
			// find the number of missing messages
			int missing_msg = 0;
			for (int i = 0; i < msg.totalMessages; i++) {
				if (receivedMessages[i] == 0) {
					System.out.println("Missing message, expected " + (i));
					missing_msg++;
				}
			}
			System.out.println("Total number of messages sent = " + msg.totalMessages);
			System.out.println("Total number of messages lost = " + missing_msg);

			long timer_end = System.currentTimeMillis();
			System.out.println("Time elapsed: " + ((timer_end - timer_start)) + "(ms)");
		}

	}

	// Initialising UDP socket for receiving data
	public UDPServer(int rp) {
		try {
			// Initialise socket
			recvSoc = new DatagramSocket(rp);
			// Set 40sec timeout to socket
			recvSoc.setSoTimeout(40000);
			System.out.println("UDPServer ready");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		int recvPort;

		// Get the parameters from command line
		if (args.length < 1) {
			System.err.println("Arguments required: recv port");
			System.exit(-1);
		}

		recvPort = Integer.parseInt(args[0]);

		// TO-DO: Construct Server object and start it by calling run().
		UDPServer udp_server_obj = new UDPServer(recvPort); //create Server at agreed port
		try{
			udp_server_obj.run();
		}catch(SocketTimeoutException e){
			e.printStackTrace();
		}
	}

}
