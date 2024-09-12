import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class savingNewItemServer {
    public static void main(String[] args) {

        Thread serverListener = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Server is waiting for client.");
                    ServerSocket serverSocket = new ServerSocket(9997);
                    Socket sc = serverSocket.accept();
                    System.out.println("Connection established with client!");
                    InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                    BufferedReader sReader = new BufferedReader(isr);

                    String type = sReader.readLine();
                    String name = sReader.readLine();
                    int quantity = Integer.parseInt(sReader.readLine());
                    String price = sReader.readLine();
                    if (Objects.equals(type, "Headphone")) {
                        item items;
                        ArrayList<item> itemArrayList = new ArrayList<>();
                        try {
                            Scanner Data = new Scanner(new File("HeadphoneData.txt"), StandardCharsets.UTF_8);
                            String str = Data.next();
                            Data.close();
                            Scanner data = new Scanner(str).useDelimiter(",");
                            while (data.hasNext()) {
                                items = new item(data.next(),
                                        data.next(), data.next());
                                itemArrayList.add(items);
                            }
                            File file = new File("HeadphoneData.txt");
                            file.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FileWriter Writer = new FileWriter("HeadphoneData.txt", true);
                        int i = 0;
                        for (item value : itemArrayList) {
                            if (Objects.equals(name, value.name)) {
                                value.quantity = Integer.toString(Integer.parseInt(value.quantity) + quantity);
                                i = 1;
                            }
                            try {
                                Writer.write(value.name + ","
                                        + value.quantity + "," + value.price + ",");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (i == 0) {
                            try {
                                String Quantity = Integer.toString(quantity);
                                Writer.write(name + ","
                                        + Quantity + "," + price + ",");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Writer.close();
                    } else if (Objects.equals(type, "Charger")) {
                        item items;
                        ArrayList<item> itemArrayList = new ArrayList<>();
                        try {
                            Scanner Data = new Scanner(new File("ChargerData.txt"), StandardCharsets.UTF_8);
                            String str = Data.next();
                            Data.close();
                            Scanner data = new Scanner(str).useDelimiter(",");
                            while (data.hasNext()) {
                                items = new item(data.next(),
                                        data.next(), data.next());
                                itemArrayList.add(items);
                            }
                            File file = new File("ChargerData.txt");
                            file.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FileWriter Writer = new FileWriter("ChargerData.txt", true);
                        int i = 0;
                        for (item value : itemArrayList) {
                            if (Objects.equals(name, value.name)) {
                                value.quantity = Integer.toString(Integer.parseInt(value.quantity) + quantity);
                                i = 1;
                            }
                            try {
                                Writer.write(value.name + ","
                                        + value.quantity + "," + value.price + ",");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (i == 0) {
                            try {
                                String Quantity = Integer.toString(quantity);
                                Writer.write(name + ","
                                        + Quantity + "," + price + ",");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Writer.close();
                    } else if (Objects.equals(type, "Pendrive")) {
                        item items;
                        ArrayList<item> itemArrayList = new ArrayList<>();
                        try {
                            Scanner Data = new Scanner(new File("PendriveData.txt"), StandardCharsets.UTF_8);
                            String str = Data.next();
                            Data.close();
                            Scanner data = new Scanner(str).useDelimiter(",");
                            while (data.hasNext()) {
                                items = new item(data.next(),
                                        data.next(), data.next());
                                itemArrayList.add(items);
                            }
                            File file = new File("PendriveData.txt");
                            file.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FileWriter Writer = new FileWriter("PendriveData.txt", true);
                        int i = 0;
                        for (item value : itemArrayList) {
                            if (Objects.equals(name, value.name)) {
                                value.quantity = Integer.toString(Integer.parseInt(value.quantity) + quantity);
                                i = 1;
                            }
                            try {
                                Writer.write(value.name + ","
                                        + value.quantity + "," + value.price + ",");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (i == 0) {
                            try {
                                String Quantity = Integer.toString(quantity);
                                Writer.write(name + ","
                                        + Quantity + "," + price + ",");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Writer.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverListener.start();
    }
}