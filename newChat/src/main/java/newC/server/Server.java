package newC.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    static List<User> list = new ArrayList<>();
    static List<User> listClients = new LinkedList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1111);) {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                new Thread(serverThread).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


