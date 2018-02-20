package chat.classes;

import java.io.*;
import java.net.Socket;

import chat.server.*;
import org.apache.log4j.Logger;

public class Agent implements User {
    private static final Logger log = Logger.getLogger(Reader.class.getSimpleName());
    private Socket socket;
    private ServerChat server;
    private PrintWriter writer;
    public String name;

    public Agent(Socket socket, ServerChat server, String name) {
        this.socket = socket;
        this.server = server;
        this.name = name;
    }

    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
            writer = new PrintWriter(socket.getOutputStream(), true);
            server.searchChat();
            while (true) {
                String message = reader.readLine();
                if (message != null) {
                    if (message.equals("/exit")) {
                        exit();
                        break;
                    }
                    server.sendAgentMessage(message, this);
                } else {
                    exit();
                    break;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public synchronized void sendMessage(String message, String name) {
            writer.println(name + " : " + message);
    }

    private synchronized void exit() throws IOException {
        server.exitAgent(this);
        server.searchChat();
        socket.close();
    }
}