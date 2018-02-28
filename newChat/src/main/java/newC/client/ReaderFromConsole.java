package newC.client;

import newC.server.AgentUser;
import newC.server.ClientUser;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ReaderFromConsole extends Thread {
    Socket socket;
    String registration;
    String line;
    private static final Logger log = Logger.getLogger(ReaderFromConsole.class.getSimpleName());

    public ReaderFromConsole(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
             OutputStream sout = socket.getOutputStream();
             DataOutputStream out = new DataOutputStream(sout);) {
            System.out.println("register please");
            while (true) {
                registration = keyboard.readLine();
                if (registration.contains("/a") || registration.contains("/c")) {
                    break;
                } else {
                    System.out.println("incorrect!");
                }
            }
            out.writeUTF(registration);
            out.flush();
            System.out.println("Type in something and press enter");
            System.out.println();
            while (!socket.isClosed()) {
                line = keyboard.readLine();
                out.writeUTF(line);
                out.flush();

            }

        } catch (IOException e) {
            log.error(e.getMessage());
            System.out.println(e);
        }
    }
}
