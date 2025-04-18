package com.dihu;

import java.util.Scanner;

public class Client {

    public Client(String serverAddress, int serverPort) {
        try {
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            new ReadThreadClient(networkUtil);
            new WriteThreadClient(networkUtil);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String args[]) {
        new Client("localhost", 5000);
    }
}
