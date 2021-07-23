package com.dihu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    public Map<String,NetworkUtil> clientMap;
    Server() {
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Server opened at port 5000");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void serve(Socket clientSocket) throws IOException,ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        networkUtil.write("Connected");
        new ReadThreadServer(clientMap,networkUtil);
    }
    public static void main(String args[]) {
        new Server();
    }
}
/*
ClientReadThread - Receive data from server and send to user
ClientWriteThread - Receive data from user and send to server
ServerReadThread - Recieve data from client and send to client
*/