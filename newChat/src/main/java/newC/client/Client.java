package newC.client;

import newC.client.ReaderFromConsole;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] ar) {
        Socket socket = null;
        try {
            try {
                socket = new Socket(InetAddress.getLocalHost(), 1111);
                InputStream sin = socket.getInputStream();
                DataInputStream in = new DataInputStream(sin);
                ReaderFromConsole readerFromConsole = new ReaderFromConsole(socket);
                readerFromConsole.start();
                String line;
                while (true) {
                    line = in.readUTF();
                    if (line.endsWith("quit"))
                        break;
                    else {
                        System.out.println(line);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
