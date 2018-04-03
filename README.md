# Multimedia-File-Transfer

A socket is one endpoint of a two-way communication link between two programs running on the network. A socket is bound to a port number so that the TCP layer can identify the application that data is destined to be sent to.

Considering a situation where a set of multimedia file are stored on a shared location by the clients on some specified terms and they wish to download some necessay file from the location.

Multiple Client Server Architecture is implemented.

Server:
  The server consists of two classes
  1)  Server Class:
                    The server class consists of server socket listening to a particular port number and accepts aal clients connected to it and assigns a thread for each client so that it supports multiple clients. The thread is then initiated to process the client request.
  
  2)  ClientHandler Class:
                          The class is created as a thread object in order to server each client request. File searching algorithm is implemented in order to search for the file requested by clients be it audio, text, video etc., and necessary responce is provided to the client. If the file is matched the contents of the file are sent to the client
                          
Client:
  The Client consists if two classes
  1)GUI:
        The class consists of a GUI interface for the clients where the clients has to provide the IP address of server with port number.
        Name of the file for which the contents has to be recieved.
        Disconnect button to disconnect the connection with server.
  
  2)Client:
           The details enter in the GUI are extracted to establish connection. The file name is sent to server to find it and if available read the contents from server. On recieving the contents store them in file of client workspace.
           
The code has been designed and tested on eclipse.

Steps to run:
Server: Server Class
        ClientHandler Class
        Run the server class file

Client: GUI Class
        Client Class
        Run the gui class file
