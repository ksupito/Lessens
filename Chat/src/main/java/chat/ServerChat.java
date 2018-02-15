package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerChat {
    private static final int PORT = 8887;
    private ArrayList<UserChat> users = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerChat server = new ServerChat();
        server.start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                UserChat user = new UserChat(socket, this);
                users.add(user);
                user.start();
            }
        } catch (IOException ex) {
            System.out.println("тут будут логи");
        }
    }

    public void sendMessageAll(String message, UserChat use) {
        for (UserChat user : users) {
            if (user != use)
                user.sendMessage(message);
        }
    }

    public void removeUser(UserChat use) {
        for (UserChat user : users) {
            if (user != use)
                users.remove(user);
        }
    }
}
