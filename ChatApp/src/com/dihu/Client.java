import java.io.IOException;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class  NetworkUtil {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public NetworkUtil(String s, int port) throws IOException {
        this.socket = new Socket(s, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public NetworkUtil(Socket s) throws IOException {
        this.socket = s;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public Object read() throws IOException, ClassNotFoundException {
        return ois.readUnshared();
    }

    public void write(Object o) throws IOException {
        oos.writeUnshared(o);
    }

    public void closeConnection() throws IOException {
        ois.close();
        oos.close();
    }
}


class WriteThread implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    String name;

    public WriteThread(NetworkUtil networkUtil, String name) {
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
                networkUtil.write(s);
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


class ReadThread implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;

    public ReadThread(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                String s = (String) networkUtil.read();
                System.out.println(s);
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
public class Client {

    public Client(String serverAddress, int serverPort) {
        try {
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            new ReadThread(networkUtil);
            new WriteThread(networkUtil, "Client");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String args[]) {
        if(args.length>0) {
            new Client(args[0], Integer.parseInt(args[1]));
        }
        else{
            System.out.print("Server Address: ");
            Scanner sc=new Scanner(System.in);
            String serverAddress=sc.next();
            System.out.print("Server Port: ");
            int serverPort=sc.nextInt();
            new Client(serverAddress, serverPort);
        }
    }
}
