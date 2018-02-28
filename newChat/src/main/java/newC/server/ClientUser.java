package newC.server;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientUser {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String name;
    String message;
    ServerMethods serverMethods;
    boolean waitAgent = false;
    AgentUser agentUser = null;
    // private static final Logger log = Logger.getLogger(ClientUser.class.getSimpleName());

    List<String> messages = new LinkedList<>();

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String m) {
        messages.add(m);
    }

    public ClientUser(DataInputStream dis, DataOutputStream dos, Socket socket, String name) {
        this.name = name;
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
    }

    public AgentUser getAgentUser() {
        return agentUser;
    }

    public void setAgentUser(AgentUser agentUser) {
        this.agentUser = agentUser;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public synchronized void read() throws IOException {
        try (
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();) {
            dis = new DataInputStream(sin);
            dos = new DataOutputStream(sout);
            serverMethods = new ServerMethods();
            while (true) {
                message = dis.readUTF();
                if (message.equalsIgnoreCase("/exit")) {
                    if (agentUser != null) {
                        serverMethods.exitClient(this);
                        serverMethods.searchChat();
                        socket.close();
                        break;
                    }
                    if (waitAgent == true) {
                        serverMethods.exitClientFromQueue(this);
                        serverMethods.searchChat();
                        socket.close();
                        break;
                    } else {
                        serverMethods.exitClientFromList(this);
                        serverMethods.searchChat();
                        socket.close();
                        break;
                    }

                } else if (message.equalsIgnoreCase("/leave")) {
                    serverMethods.disconnectClient(this);
                    serverMethods.searchChat();
                    continue;
                }
                if (agentUser == null && !waitAgent) {
                    serverMethods.changeQueue(this);
                    waitAgent = true;
                    if (serverMethods.searchChat()) {
                        serverMethods.send(message, agentUser.getDos(), name);
                        continue;
                    } else {
                        this.addMessage(message);
                        serverMethods.send("wait please!", this.getDos(), serverMethods.getChatName());
                        continue;
                    }
                }
                if (waitAgent && agentUser == null) {
                    if (serverMethods.searchChat()) {
                        serverMethods.send(message, agentUser.getDos(), name);
                        continue;
                    } else {
                        this.addMessage(message);
                        serverMethods.send("wait please!", this.getDos(), serverMethods.getChatName());
                        continue;
                    }
                } else {
                    if (agentUser != null) {
                        serverMethods.send(message, agentUser.getDos(), name);
                    }
                }
            }
        }
    }
}
