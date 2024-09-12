import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LogInServer {
    public static void main(String[] args) throws IOException {
        Thread serverListener = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Server is waiting for client.");
                    ServerSocket serverSocket = new ServerSocket(1234);
                    Socket sc = serverSocket.accept();
                    System.out.println("Connection established with client!");
                    OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
                    BufferedWriter sWriter = new BufferedWriter(o);
                    InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                    BufferedReader sReader = new BufferedReader(isr);

                    AccountCheck accountCheck;
                    ArrayList<AccountCheck> accountArrayList = new ArrayList<>();
                    Scanner Data = new Scanner(new File("UserData.txt"), StandardCharsets.UTF_8);
                    String str = Data.next();
                    Data.close();
                    Scanner data = new Scanner(str).useDelimiter(",");
                    while (data.hasNext()) {
                        accountCheck = new AccountCheck(data.next(),
                                data.next(),
                                data.next(),
                                Integer.parseInt((data.next())));
                        accountArrayList.add(accountCheck);
                    }

                    String name = sReader.readLine();
                    String password = sReader.readLine();
                    if (Objects.equals(name, "Rifa") || Objects.equals(name, "abcd@gmail.com")) {
                        if (Objects.equals(password, "1234")) {
                            System.out.print("yes");
                            sWriter.write("1" + "\n");
                            sWriter.flush();
                        }
                    } else {
                        for (AccountCheck value : accountArrayList) {
                            if (Objects.equals(name, value.name) || Objects.equals(name, value.email)) {
                                if (Objects.equals(password, value.password)) {
                                    System.out.print("yes");
                                    sWriter.write("2" + "\n");
                                    sWriter.flush();
                                    break;
                                }
                            } else {
                                System.out.println("no");
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverListener.start();
    }
}
