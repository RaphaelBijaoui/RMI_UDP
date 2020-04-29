package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import common.MessageInfo;

public class UDPClient {

	private DatagramSocket sendSoc;

	private static double time_taken = 0;

	public static void main(String[] args) {
		InetAddress	serverAddr = null;
		int			recvPort; //i.e. server port
		int 		countTo;

		// Get the parameters
		if (args.length < 3) {
			System.err.println("Arguments required: server name/IP, recv port, message count");
			System.exit(-1);
		}

		try {
			serverAddr = InetAddress.getByName(args[0]); //convert "localhost" to address
			System.out.println(serverAddr.getHostName());  
		} catch (UnknownHostException e) {
			System.out.println("Bad server address in UDPClient, " + args[0] + " caused an unknown host exception " + e);
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[1]); //the port to be attached to localhost
		countTo = Integer.parseInt(args[2]);


		// TO-DO: Construct UDP client class and try to send messages
		try{
			UDPClient client = new UDPClient();
			client.testLoop(serverAddr, recvPort, countTo);
			System.out.println("Time = "+ time_taken);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public UDPClient() {
		try{
			// Initialising UDP socket for sending data
			sendSoc = new DatagramSocket(); //creates socket on any available port
		}catch(SocketException e){
			e.printStackTrace();
		}
	}

	private void testLoop(InetAddress serverAddr, int recvPort, int countTo) {

		//Sending the messages to the server
		for(int attempt = 0; attempt < countTo; attempt++){
			String message = new String((Integer.toString(countTo))+ ";" + (Integer.toString(attempt)));
			send(message, serverAddr,recvPort);
		}
	}

	private void send(String payload, InetAddress destAddr, int destPort) {
		int	payloadSize = payload.length();
		byte[]				pktData;
		DatagramPacket		pkt;

		// TO-DO: build the datagram packet and send it to the server
		try {
			pktData = payload.getBytes();
			pkt = new DatagramPacket(pktData, payloadSize, destAddr, destPort);
			//Stopwatch begin
			long begin_time = System.nanoTime();
			sendSoc.send(pkt);
			//Stopwatch end
			long end_time = System.nanoTime();
			time_taken += ((end_time-begin_time)/ 1000000.0);
		} catch (Exception e){
			e.printStackTrace();
		}

	}
}
