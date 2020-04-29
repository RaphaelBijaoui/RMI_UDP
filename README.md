# RMI_UDP
Implementation and exploration of two client-server interactions: the Java Remote Method Invocation (RMI) and the User Data Protocol (UDP). 

Coursework component of Imperial CO527 Computer Networks & Distributed Systems. Co-author: Nathan Amar

## Build & Execution
From root of repository,
```
make all
```
to build both the UDP and RMI component.

### UDP
To run the UDP Server:
```
./udpserver.sh [RECEIVING PORT]
```
where [RECEIVING PORT] is the agreed port you will use for communication with the client. For example:

```
./udpserver.sh 5000
```

To run the UDP Client:
```
./udpclient.sh [IP ADDRESS] [RECEIVING PORT] [NUMBER OF MESSAGES]
```
where [IP ADDRESS] is the server's IP address, [RECEIVING PORT] is the same as that used by the server, and [NUMBER OF MESSAGES] is the number of messages you would like to transmit to the server. For example:

```
./udpclient.sh 116.99.232.120 5000 1000
```
### RMI
To 


