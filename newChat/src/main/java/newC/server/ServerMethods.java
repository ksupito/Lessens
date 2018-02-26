package newC.server;

import java.util.*;

public class ServerMethods {
    static List<User> listAgents = new ArrayList<>();
    static List<User> listClients = new LinkedList<>();

    public List<User> getListClients() {
        return listClients;
    }

    public List<User> getListAgents() {
        return listAgents;
    }

    public void addAgent(User e) {
        listAgents.add(e);
    }

    public void addClient(User e) {
        listClients.add(e);
    }
}
