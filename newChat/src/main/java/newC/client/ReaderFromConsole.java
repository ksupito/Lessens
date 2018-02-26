package newC.client;

import java.io.*;
import java.net.Socket;

public class ReaderFromConsole extends Thread {
    Socket socket;

    public ReaderFromConsole(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            OutputStream sout = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(sout);
            String line;
            System.out.println("register");
            line = keyboard.readLine();
            out.writeUTF(line);
            out.flush();
            System.out.println("Type in something and press enter");
            System.out.println();
            while (true) {
                line = keyboard.readLine();
                out.writeUTF(line);
                out.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
