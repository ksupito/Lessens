package newC.server;

import java.io.*;
import java.net.Socket;

public class NewVersionThread implements Runnable{
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    ServerMethods serverMethods;
    String line;

    public NewVersionThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();){
            dis = new DataInputStream(sin);
            dos = new DataOutputStream(sout);
            serverMethods = new ServerMethods();
            while (true) {
                String registration = dis.readUTF();
                if(registration != null) {
                    if (registration.contains("/a")) {
                        String name = registration.replaceFirst("/a ", "");
                        AgentUser agent = new AgentUser(null, this, dis, dos, socket, name);
                        serverMethods.addAgentToMap(agent);
                        agent.read();
                        break;
                    }
                    if (registration.contains("/c")) {
                        String name = registration.replaceFirst("/c ", "");
                        ClientUser client = new ClientUser(null, this, dis, dos, socket, name);
                        serverMethods.addClient(client);
                        client.read();
                        break;
                    }
                }
            }
        } catch (IOException e) {
    }
    }

    public synchronized void send(String line) throws IOException {
        dos.writeUTF("Server receive text : " + line + "\n");
        dos.flush();
    }
}
