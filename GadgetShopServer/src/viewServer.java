import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class viewServer {
    public static void main(String[] args) {
        Thread serverListener = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Server is waiting for client.");
                    ServerSocket serverSocket = new ServerSocket(2);
                    Socket sc = serverSocket.accept();
                    System.out.println("Connection established with client!");
                    InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                    BufferedReader sReader = new BufferedReader(isr);


                    FileWriter Writer = new FileWriter("buy.txt", true);

                    Writer.write(sReader.readLine() + "," + sReader.readLine() + "," + sReader.readLine() + ",");
                    Writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverListener.start();
    }
}
