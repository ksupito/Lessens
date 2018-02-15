package chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class UserChat extends Thread {
    private Socket socket;
    private ServerChat server;
    PrintWriter writer;

    public UserChat(Socket socket, ServerChat server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            sendMessage("Chat is opened");
            while (true) {
                String message = reader.readLine();
                if (message.equals("/exit")) {
                    server.removeUser(this);
                    socket.close();
                    break;
                }
                server.sendMessageAll(message, this);
            }

        } catch (IOException e) {
            System.out.println("тут будут логи");
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }
}
