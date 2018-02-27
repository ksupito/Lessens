package newC.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] ar) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 1111);
             InputStream sin = socket.getInputStream();
             DataInputStream in = new DataInputStream(sin)) {
            ReaderFromConsole readerFromConsole = new ReaderFromConsole(socket);
            readerFromConsole.start();
            String line;
            while (true) {
                line = in.readUTF();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
