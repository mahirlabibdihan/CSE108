package com.dihu.server;

import com.dihu.classes.Club;
import com.dihu.util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientEntryThread implements Runnable{
    private Server server;
    private Thread thr;

    public ClientEntryThread(Server server) {
        this.server = server;
        this.thr = new Thread(this,"Client Entry Thread");
        thr.start();
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                server.serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }
}
