import java.io.IOException;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.Serializable;

class Message implements Serializable {
    private String from;
    private String to;
    private String text;

    public Message() {

    }
    public Message(String from,String text) {
        this.from = from;
        this.text = text;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
class WriteThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    String name;

    public WriteThreadClient(NetworkUtil networkUtil, String name) {
        this.networkUtil = networkUtil;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }


    public void run() {
        try {
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine();
                Message m=new Message(name,s);
                networkUtil.write(m);
            }
        } catch (Exception e) {
            System.out.println("Server stopped receiving data");
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class ReadThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    String name;

    public ReadThreadClient(NetworkUtil networkUtil,String name) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        this.name = name;
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Message m = (Message) networkUtil.read();   /******Debug******/
                System.out.println(m.getFrom()+": "+m.getText());
            }
        } catch (Exception e) {
            System.out.println("Server stopped sending data");
        } finally {
            try {
                networkUtil.closeConnection();
                //Server.removeClient(name);
            } catch (IOException e) {
                System.out.println("Can't close: "+e);
            }
        }
    }
}
public class Client {
    public Client(String serverAddress, int serverPort) {
        try {
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter name of the client: ");
            String clientName = scanner.nextLine();
            networkUtil.write(clientName);
            new ReadThreadClient(networkUtil,clientName);
            new WriteThreadClient(networkUtil,clientName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String args[]) {
        if(args.length>0) {
            new Client(args[0], Integer.parseInt(args[1]));
        }
        else{
            Scanner sc=new Scanner(System.in);
            System.out.print("Server Address: ");
            String serverAddress=sc.next();
            System.out.print("Server Port: ");
            String serverPort=sc.next();
            new Client(serverAddress, Integer.parseInt(serverPort));
        }
    }
}
