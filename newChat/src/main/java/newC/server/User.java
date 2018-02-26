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

}
