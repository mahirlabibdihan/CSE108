package com.dihu;

import java.io.IOException;
import java.util.Map;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    public Map<String,NetworkUtil> clientMap;

    public ReadThreadServer(Map<String,NetworkUtil> map, NetworkUtil networkUtil) {
        this.clientMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o instanceof Message)
                {
                    Message m=(Message) o;
                    for(Map.Entry<String, NetworkUtil> cm : clientMap.entrySet()) {
                        if (!cm.getKey().equals(m.getFrom()) && (cm.getKey().equals(m.getTo()) || m.getTo().equals("all"))) {
                            cm.getValue().write(m);
                        }
                    }
                }
                else if(o instanceof String) {
                    String s = (String) o;
                    if (s.equals("list")) {
                        for (Map.Entry<String, NetworkUtil> cm : clientMap.entrySet()) {
                            networkUtil.write(cm.getKey());
                        }
                    }
                    else{
                            clientMap.put(s, networkUtil);
                    }
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