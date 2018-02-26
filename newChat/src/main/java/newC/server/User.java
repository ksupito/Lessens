package newC.server;

import newC.server.ServerThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class User {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String role;
    ServerThread chatter;
    ServerThread my;

    public User(String role, ServerThread chatter, ServerThread my, DataInputStream dis, DataOutputStream dos, Socket socket) {
        this.role = role;
        this.chatter = chatter;
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
        this.my = my;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public ServerThread getChatter() {
        return chatter;
    }

    public ServerThread getMy() {
        return my;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getRole() {
        return role;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public void setChatter(ServerThread chatter) {
        this.chatter = chatter;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public void setMy(ServerThread my) {
        this.my = my;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

