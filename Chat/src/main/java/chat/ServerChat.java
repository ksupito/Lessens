package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServerChat {
    private static final int PORT = 8887;
    private Map<Agent, Client> oneChat = new HashMap<>();
    private Queue<Client> queueClients = new LinkedList<>();
    private PrintWriter writer;

    public static void main(String[] args) throws IOException {
        ServerChat server = new ServerChat();
        server.start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println("Start");
                while (true) {
                    String message = reader.readLine();
                    if (message.contains("/register agent ")) {
                        Agent agent = new Agent(socket, this);
                        new Thread(agent).start();
                        oneChat.put(agent, null);
                        checkFreeAgent();
                        break;
                    }
                    if (message.contains("/register client ")) {
                        Client client = new Client(socket, this);
                        new Thread(client).start();
                        queueClients.add(client);
                        checkFreeAgent();
                        break;
                    } else {
                        writer.println("Incorrect console command ");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("тут будут логи");
        }
    }
    public void checkFreeAgent(){
        for (Map.Entry<Agent, Client> entry : oneChat.entrySet()) {
            if(queueClients.size() != 0){
                Agent key = entry.getKey();
                Client value = entry.getValue();
                if (key != null && value == null)
                    oneChat.put(key, queueClients.remove());
            }
        }
    }

    public void sendAgentMessage(String message, Agent agent) {
        for (Map.Entry<Agent, Client> entry : oneChat.entrySet()) {
            Agent key = entry.getKey();
            Client value = entry.getValue();
            if (key == agent)
                value.sendMessage(message);
        }
    }

    public void sendClientMessage(String message, Client client) {
        for (Map.Entry<Agent, Client> entry : oneChat.entrySet()) {
            Agent key = entry.getKey();
            Client value = entry.getValue();
            if (value == client)
                key.sendMessage(message);
        }
    }

    public void removeUser(Client use) {
        for (Map.Entry<Agent, Client> entry : oneChat.entrySet()) {
            Agent key = entry.getKey();
            Client value = entry.getValue();
            if (value == use)
                oneChat.replace(key, null);
        }
    }

    public void severUser(Client use) {
        for (Map.Entry<Agent, Client> entry : oneChat.entrySet()) {
            Agent key = entry.getKey();
            Client value = entry.getValue();
            if (value == use)
                queueClients.add(value);
                oneChat.replace(key, null);
        }
    }
}
