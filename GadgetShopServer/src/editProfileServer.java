import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class editProfileServer {
    public static void main(String[] args) {
        Thread serverListener = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Server is waiting for client.");
                    ServerSocket serverSocket = new ServerSocket(6543);
                    Socket sc = serverSocket.accept();
                    System.out.println("Connection established with client!");
                    InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                    BufferedReader sReader = new BufferedReader(isr);
                    AccountCheck account;
                    ArrayList<AccountCheck> accountArrayList = new ArrayList<>();
                    Scanner Data = new Scanner(new File("UserData.txt"), StandardCharsets.UTF_8);
                    String str = Data.next();
                    Data.close();
                    Scanner data = new Scanner(str).useDelimiter(",");
                    while (data.hasNext()) {
                        account = new AccountCheck(data.next(),
                                data.next(),data.next(),
                                Integer.parseInt((data.next())));
                        accountArrayList.add(account);
                    }
                    File file = new File("UserData.txt");
                    file.delete();
                    String on = sReader.readLine();
                    String op = sReader.readLine();
                    String nn = sReader.readLine();
                    String np = sReader.readLine();
                    FileWriter Writer = new FileWriter("UserData.txt", true);

                    for (AccountCheck value : accountArrayList) {
                        if (Objects.equals(on, value.name) && Objects.equals(op, value.password)) {
                            value.name = nn;
                            value.password = np;
                        }
                        try {
                            Writer.write(value.name + ","
                                    + value.password + ","
                                    + value.email + ","
                                    + value.phone + ",");
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverListener.start();
    }
}
