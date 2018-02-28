package newC.server;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ServerMethods serverMethods;
    private String name;
    private String registration;
    private static final Logger log = Logger.getLogger(ServerThread.class.getSimpleName());

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();) {
            dis = new DataInputStream(sin);
            dos = new DataOutputStream(sout);
            serverMethods = new ServerMethods();
            while (!socket.isClosed()) {
                registration = dis.readUTF();
                if (registration != null) {
                    if (registration.contains("/a")) {
                        createAgent();
                        break;
                    }
                    if (registration.contains("/c")) {
                        createClient();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void createAgent() throws IOException {
        name = registration.replaceFirst("/a ", "");
        AgentUser agent = new AgentUser(dis, dos, socket, name);
        serverMethods.addAgentToMap(agent);
        log.info("agent registered");
        agent.read();
    }

    private void createClient() throws IOException {
        name = registration.replaceFirst("/c ", "");
        ClientUser client = new ClientUser(dis, dos, socket, name);
        serverMethods.addClient(client);
        serverMethods.send("type message, pleaes!", dos, serverMethods.getChatName());
        log.info("client registered");
        client.read();
    }
}
