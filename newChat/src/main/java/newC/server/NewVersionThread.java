package newC.server;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class NewVersionThread implements Runnable {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    ServerMethods serverMethods;
    private static final Logger log = Logger.getLogger(NewVersionThread.class.getSimpleName());

    public NewVersionThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();) {
            dis = new DataInputStream(sin);
            dos = new DataOutputStream(sout);
            serverMethods = new ServerMethods();
            while (true) {
                String registration = dis.readUTF();
                if (registration != null) {
                    if (registration.contains("/a")) {
                        String name = registration.replaceFirst("/a ", "");
                        AgentUser agent = new AgentUser( dis, dos, socket, name);
                        serverMethods.addAgentToMap(agent);
                        log.info("agent registered");
                        agent.read();
                        break;
                    }
                    if (registration.contains("/c")) {
                        String name = registration.replaceFirst("/c ", "");
                        ClientUser client = new ClientUser( dis, dos, socket, name);
                        serverMethods.addClient(client);
                        client.read();
                        log.info("client registered");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
