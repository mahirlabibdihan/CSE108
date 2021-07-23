package com.dihu;

import java.io.IOException;
import java.util.Map;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;

    public ReadThreadClient(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o=networkUtil.read();
                if (o instanceof Message){
                    Message m = (Message) o;
                    System.out.println(m.getFrom()+": "+m.getText());
                }
                else if(o instanceof String){
                    String s=(String) o;
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
