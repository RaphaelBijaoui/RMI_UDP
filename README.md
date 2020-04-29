# RMI_UDP
Implementation and exploration of two client-server interactions: the Java Remote Method Invocation (RMI) and the User Data Protocol (UDP). 

Coursework component of Imperial CO527 Computer Networks & Distributed Systems. Co-author: Nathan Amar

## Background


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
