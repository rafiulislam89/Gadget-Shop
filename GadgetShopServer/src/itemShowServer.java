import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class itemShowServer {
    public static void main(String[] args) {
        Thread serverListener = new Thread(() -> {
            while (true) {
                try {
                        System.out.println("Server is waiting for client.");
                        ServerSocket serverSocket = new ServerSocket(1999);
                        Socket sc = serverSocket.accept();
                        System.out.println("Connection established with client!");
                        OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
                        InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                        BufferedReader sReader = new BufferedReader(isr);
                        BufferedWriter sWriter = new BufferedWriter(o);

                        item items;
                        ArrayList<item> itemArrayList = new ArrayList<>();
                        Scanner Data = new Scanner(new File(sReader.readLine()), StandardCharsets.UTF_8);
                        String str = Data.next();
                        Data.close();
                        Scanner data = new Scanner(str).useDelimiter(",");
                        while (data.hasNext()) {
                            items = new item(data.next(),
                                    data.next(),data.next());
                            itemArrayList.add(items);
                        }

                        sWriter.write(itemArrayList.size() + "\n");
                        for (item value : itemArrayList) {
                            sWriter.write(value.name + "\n");
                            sWriter.write(value.quantity + "\n");
                            sWriter.write(value.price + "\n");
                        }
                    sWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverListener.start();
    }
}
