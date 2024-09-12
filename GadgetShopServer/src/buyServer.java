import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class buyServer {
    public static void main(String[] args) {
        Thread serverListener = new Thread(() -> {
            while(true) {
                try {
                    System.out.println("Server is waiting for client.");
                    ServerSocket serverSocket = new ServerSocket(69);
                    Socket sc = serverSocket.accept();
                    System.out.println("Connection established with client!");
                    OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
                    BufferedWriter sWriter = new BufferedWriter(o);

                    Scanner Data = new Scanner(new File("buy.txt"), StandardCharsets.UTF_8);
                    String str = Data.next();
                    Data.close();
                    Scanner data = new Scanner(str).useDelimiter(",");

                    sWriter.write(data.next() + "\n");
                    sWriter.write(data.next() + "\n");
                    sWriter.write(data.next() + "\n");
                    sWriter.flush();

                    File file = new File("buy.txt");
                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }}});
        serverListener.start();

    }
}
