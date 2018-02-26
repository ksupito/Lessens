package newC.server;

import java.util.*;

public class ServerMethods {
    static List<ServerThread> list = new ArrayList<>();
    Map<ServerThread, ServerThread> mapAgent = new HashMap<>();
    static List<ServerThread> listClients = new LinkedList<>();

    public List<ServerThread> getListClients() {
        return listClients;
    }

    public List<ServerThread> getList() {
        return list;
    }

    public void addAgent(ServerThread e) {
        list.add(e);
    }

    public void addClient(ServerThread e) {
        listClients.add(e);
    }
}
