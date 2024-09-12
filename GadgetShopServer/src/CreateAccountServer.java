import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateAccountServer {
    public static void main(String[] args) {
        Thread serverListener = new Thread(() -> {
            while(true) {
                try {
                    System.out.println("Server is waiting for client.");
                    ServerSocket serverSocket = new ServerSocket(2345);
                    Socket sc = serverSocket.accept();
                    System.out.println("Connection established with client!");
                    InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                    BufferedReader sReader = new BufferedReader(isr);
                    FileWriter Writer = new FileWriter("UserData.txt", true);

                    Writer.write(sReader.readLine() + ","
                            + sReader.readLine() + ","
                            + sReader.readLine() + ","
                            + sReader.readLine() + ",");
                    Writer.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }}});
        serverListener.start();

    }
}
