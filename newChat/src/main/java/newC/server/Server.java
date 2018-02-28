package newC.server;

import org.apache.log4j.Logger;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static final Logger log = Logger.getLogger(Server.class.getSimpleName());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1111);) {
            while (true) {
                Socket socket = serverSocket.accept();
                NewVersionThread newVersionThread = new NewVersionThread(socket);
                new Thread(newVersionThread).start();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
        }
    }
}


