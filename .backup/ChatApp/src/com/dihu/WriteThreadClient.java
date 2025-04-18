package com.dihu;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class WriteThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    String clientName;

    public WriteThreadClient(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }
    public void connect(){
        Scanner scanner = new Scanner(System.in);
        clientName = scanner.nextLine();
        try{
            networkUtil.write(clientName);
        }catch (Exception e) {
            System.out.println(clientName+" stopped receiving data");
        }
    }
    public void run() {
        try {
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println( "1. Connect" + "\n" +
                                    "2. GetList" + "\n" +
                                    "3. SendOne" + "\n" +
                                    "4. Broadcast");

                int option = input.nextInt();
                input.nextLine();
                switch(option)
                {
                    case 1:
                        connect();
                        break;
                    case 2:
                        networkUtil.write("list");
                        break;
                    case 3:
                        networkUtil.write("list");
                        String name = input.nextLine();
                        networkUtil.write(new Message(clientName,input.nextLine(),name));
                        break;
                    case 4:
                        networkUtil.write(new Message(clientName,input.nextLine()));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
