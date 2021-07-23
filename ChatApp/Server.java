import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    public Map<String,NetworkUtil> clientMap;
    String name;


    public ReadThreadServer(Map<String,NetworkUtil> map, NetworkUtil networkUtil) {
        this.clientMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            name = (String) networkUtil.read();
            clientMap.put(name,networkUtil);
            networkUtil.write(new Message("Server","Hello "+name));
            while (true) {
                Message m = (Message) networkUtil.read();
                for(Map.Entry<String, NetworkUtil> cm : clientMap.entrySet()){
                    if(!cm.getKey().equals(m.getFrom())) {
                        try{
                            cm.getValue().write(m);
                        } catch (Exception e) {
                            System.out.println(name+" stopped receiving data");
                            Server.removeClient(name);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(name+" stopped sending data");
            Server.removeClient(name);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                System.out.println("Can't close: "+e);
            }
        }
    }
}

public class Server {
    private ServerSocket serverSocket;
    public static Map<String,NetworkUtil> clientMap;

    Server() {
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Server opened at port 5000");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client entered");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void serve(Socket clientSocket) throws IOException,ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(clientMap,networkUtil);
    }
    public static void removeClient(String name){
        System.out.println("Client left");
        clientMap.remove(name);
    }
    public static void main(String args[]) {
        new Server();
    }
}