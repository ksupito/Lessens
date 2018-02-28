package newC.client;


import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class.getSimpleName());

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 1111);
             InputStream sin = socket.getInputStream();
             DataInputStream in = new DataInputStream(sin)) {
            ReaderFromConsole readerFromConsole = new ReaderFromConsole(socket);
            readerFromConsole.start();
            String line;
            while (!socket.isClosed()) {
                line = in.readUTF();
                if (line.equals("1")) {
                    socket.close();
                    break;
                }
                System.out.println(line);

            }
            System.exit(0);
        } catch (IOException e) {
            log.error(e.getMessage());
            System.out.println(e);
        }
    }
}
