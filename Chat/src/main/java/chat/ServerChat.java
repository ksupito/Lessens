package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChat {
    private static final int PORT = 8887;
    private ArrayList<UserChat> users = new ArrayList<>();

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                UserChat newUser = new UserChat(socket, this);
                users.add(newUser);
                newUser.start();
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println("hello");

            }

        } catch (IOException ex) {
            System.out.println("тут будут логи");
        }
    }

    public static void main(String[] args) throws IOException {
        ServerChat clearServer = new ServerChat();
        clearServer.start();
    }
}
