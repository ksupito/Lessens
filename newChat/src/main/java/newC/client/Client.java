package newC.client;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class.getSimpleName());

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }

    private void startClient() {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 1111);
             InputStream sin = socket.getInputStream();
             DataInputStream in = new DataInputStream(sin)) {
            ClientReader clientReader = new ClientReader(socket);
            clientReader.start();
            String message;
            while (!socket.isClosed()) {
                message = in.readUTF();
                if (message.equals("cancel")) {
                    socket.close();
                    break;
                }
                System.out.println(message);
            }
            System.exit(0);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
