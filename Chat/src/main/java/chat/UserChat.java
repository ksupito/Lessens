package chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class UserChat extends Thread {
    private Socket socket;
    private ServerChat server;

    public UserChat(Socket socket, ServerChat server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("тут будут логи");
        }
    }
}
