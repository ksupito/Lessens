package newC.server;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class AgentUser {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String name;
    String message;
    ServerMethods serverMethods;
    ClientUser clientUser = null;
  // private static final Logger log = Logger.getLogger(AgentUser.class.getSimpleName());

    public AgentUser( DataInputStream dis, DataOutputStream dos, Socket socket, String name) {
        this.name = name;
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
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

    public void read() throws IOException {
        try (
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();) {
            dis = new DataInputStream(sin);
            dos = new DataOutputStream(sout);
            serverMethods = new ServerMethods();
            serverMethods.searchChat();
            while (true) {
                message = dis.readUTF();
                if (message.equals("/exit")) {
                    serverMethods.exitAgent(this);
                    serverMethods.searchChat();
                    socket.close();
                    break;
                }
                if (clientUser != null) {
                    serverMethods.send(message, clientUser.getDos(), name);
                }
            }
        }
    }
}
