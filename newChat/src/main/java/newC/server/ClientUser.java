package newC.server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientUser {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    NewVersionThread chatter;
    NewVersionThread my;
    String name;
    String message;
    ServerMethods serverMethods;
    boolean waitAgent = false;
    AgentUser agentUser = null;

    List<String> messages = new LinkedList<>();

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String m) {
        messages.add(m);
    }

    public ClientUser(NewVersionThread chatter, NewVersionThread my, DataInputStream dis, DataOutputStream dos, Socket socket, String name) {
        this.name = name;
        this.chatter = chatter;
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
        this.my = my;
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

    public NewVersionThread getChatter() {
        return chatter;
    }

    public NewVersionThread getMy() {
        return my;
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

    public void setChatter(NewVersionThread chatter) {
        this.chatter = chatter;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public void setMy(NewVersionThread my) {
        this.my = my;
    }

    public void read() throws IOException {
        InputStream sin = socket.getInputStream();
        OutputStream sout = socket.getOutputStream();
        dis = new DataInputStream(sin);
        dos = new DataOutputStream(sout);
        serverMethods = new ServerMethods();
        while (true) {
            message = dis.readUTF();
            if (message.equalsIgnoreCase("/exit")) {
                serverMethods.exitClient(this);
                serverMethods.searchChat();
                socket.close();
                break;
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
                    my.send("Wait please");
                    continue;
                }
            }
            if (waitAgent && agentUser == null) {
                if (serverMethods.searchChat()) {
                    serverMethods.send(message, agentUser.getDos(), name);
                    continue;
                } else {
                    this.addMessage(message);
                    my.send("Wait please");
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
