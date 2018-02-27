package newC.server;

import java.io.*;
import java.net.Socket;

public class AgentUser {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    NewVersionThread chatter;
    NewVersionThread my;
    String name;
    String message;
    ServerMethods serverMethods;
    ClientUser clientUser = null;

    public AgentUser(NewVersionThread chatter, NewVersionThread my, DataInputStream dis, DataOutputStream dos, Socket socket, String name) {
        this.name = name;
        this.chatter = chatter;
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
        this.my = my;
    }

    public ClientUser getClientUser() {
        return clientUser;
    }

    public void setClientUser(ClientUser clientUser) {
        this.clientUser = clientUser;
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
        serverMethods.searchChat();
        while (true) {
            message = dis.readUTF();
            if (message.equalsIgnoreCase("/exit")) {
                serverMethods.exitAgent(this);
                serverMethods.searchChat();
                socket.close();
                break;
            }
            if (clientUser != null) {
                serverMethods.send(message, clientUser.getDos(), name);
            }
            if (message.equalsIgnoreCase("quit")) {
                socket.close();
                break;
            }
        }
    }
}
