package newC.server;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String role;
    ServerMethods serverMethods;
    User user;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            dis = new DataInputStream(sin);
            dos = new DataOutputStream(sout);
            serverMethods = new ServerMethods();
            String line;

            while (true) {
                line = dis.readUTF();
                if (line.contains("/a")) {
                    role = "agent";
                    user = new User(role, null, this, dis, dos, socket);
                    // serverMethods.addAgent(this);
                    Server.list.add(user);
                    break;
                }
                if (line.contains("/c")) {
                    role = "client";
                    user = new User(role, null, this, dis, dos, socket);
                    //  serverMethods.addClient(this);
                    Server.listClients.add(user);
                    break;
                } else {
                    dos.writeUTF("Incorrect" + "\n");
                    dos.flush();
                }
            }

            findChatter();
            while (true) {
                line = dis.readUTF();
                if (user.chatter != null) {
                    user.chatter.send(line);
                }
                if (line.equalsIgnoreCase("quit")) {
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public synchronized void send(String line) throws IOException {
        dos.writeUTF("Server receive text : " + line + "\n");
        dos.flush();
    }


    public void findChatter() {
        if (user.role.equals("client")) {
            for (User thread : Server.list) {
                if (thread.chatter == null) {
                    thread.chatter = this;
                    user.chatter = thread.my;
                }
            }
        } else if (user.role.equals("agent")) {
            for (User thread : Server.listClients) {
                if (thread.chatter == null) {
                    thread.chatter = this;
                    user.chatter = thread.my;
                }
            }
        }


    }
}
