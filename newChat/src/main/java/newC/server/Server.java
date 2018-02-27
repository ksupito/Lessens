package newC.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1111);) {
            while (true) {
                Socket socket = serverSocket.accept();
                NewVersionThread newVersionThread = new NewVersionThread(socket);
                new Thread(newVersionThread).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


