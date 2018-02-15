package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
    private Socket socket;
    private ServerChat server;
    private PrintWriter writer;

    public Client(Socket socket, ServerChat server) {
        this.socket = socket;
        this.server = server;
    }
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
                if (message.equals("/leave")) {
                    server.severUser(this);
                    break;
                }
                server.sendClientMessage(message, this);
            }
        } catch (IOException e) {
            System.out.println("тут будут логи");
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}
