# RMI_UDP
Implementation and exploration of two client-server interactions: the Java Remote Method Invocation (RMI) and the User Data Protocol (UDP). 

Coursework component of Imperial CO527 Computer Networks & Distributed Systems. Co-author: Nathan Amar

## Background
This work is essentially a comparison of interaction methods- a first, primitive method (UDP), and a remote procedure call (Java RMI). A bit of background on these two methods, courtesy of the teachings from CO527:

**UDP**
The User Datagram Protocol (UDP) is simplest Transport Layer communication protocol available of the TCP/IP protocol suite. It involves minimum amount of communication mechanism. UDP is said to be an unreliable transport protocol but it uses IP services which provides best effort delivery mechanism.

<p align="center">
  <img src="https://github.com/RaphaelBijaoui/images/blob/master/UDP.png">
</p>
<p align="center">
  <i>General client-server interaction for UDP communication</i>
</p>


**Remote procedure calls**
However, basic message passing in UDP can leave a lot of work for the programmer e.g. constructing messages, transforming data types.

Remote Procedure Calls aim to make a call to a remote service look the same as a call to a local procedure.
– The parameters to the call are carried in one or several request messages and the results returned reply message(s).
– However calls to procedures implemented remotely can fail in different ways to procedures implemented locally, the semantics of an RPC are different from those of a local procedure.

<p align="center">
  <img src="https://github.com/RaphaelBijaoui/images/blob/master/RMI.png">
</p>
<p align="center">
  <i>General client-server interaction for RPC communication</i>
</p>

## Build & Execution
From root of repository,
```
make all
```
to build both the RMI and UDP component.

### RMI
To run the RMIServer:
```
./rmiserver.sh 
```
To run the RMIClient
```
./rmiclient.sh [IP ADDRESS / SERVERNAME] [RECEIVING PORT] [NUMBER OF MESSAGES]
```
where [IP ADDRESS / SERVERNAME] is the server's IP address or the servername, and [NUMBER OF MESSAGES] is the number of messages you would like to transmit to the server. For example:
```
./rmiclient.sh localhost 100
```
or
```
./rmiclient.sh 116.99.232.120 100
```


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
./udpclient.sh [IP ADDRESS / SERVERNAME] [RECEIVING PORT] [NUMBER OF MESSAGES]
```
where [IP ADDRESS / SERVERNAME] is the server's IP address or the servername, [RECEIVING PORT] is the same as that used by the server, and [NUMBER OF MESSAGES] is the number of messages you would like to transmit to the server. For example:

```
./udpclient.sh 116.99.232.120 5000 1000
```
or
```
./udpclient.sh localhost 5000 1000
```
