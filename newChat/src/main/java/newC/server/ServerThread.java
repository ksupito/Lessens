package newC.server;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
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
                    user = new User("agent", null, this, dis, dos, socket);
                    serverMethods.addAgent(user);
                    break;
                }
                if (line.contains("/c")) {
                    user = new User("client", null, this, dis, dos, socket);
                    serverMethods.addClient(user);
                    break;
                } else {
                    dos.writeUTF("Incorrect" + "\n");
                    dos.flush();
                }
            }

            findChatter();
            while (true) {
                line = dis.readUTF();
                if (user.getChatter() != null) {
                    user.getChatter().send(line);
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
            for (User us : serverMethods.getListAgents()) {
                if (us.getChatter() == null) {
                    us.setChatter(this);
                    user.setChatter(us.getMy());
                }
            }
        } else if (user.role.equals("agent")) {
            for (User us : serverMethods.getListClients()) {
                if (us.getChatter() == null) {
                    us.setChatter(this);
                    user.setChatter(us.getMy());
                }
            }
        }


    }
}

