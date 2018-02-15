package chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientChat {

    public static void main(String[] args) {
        ClientChat client = new ClientChat();
        client.start();
    }

    private void start() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 8887);
        } catch (UnknownHostException ex) {
            System.out.println("тут будут логи");
        } catch (IOException ex) {
            System.out.println("тут будут логи");
        }
    }

}
