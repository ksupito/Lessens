package newC.server;

import java.util.*;

public class ServerMethods {
    static List<User> listAgents = new LinkedList<>();
    static List<User> listClients = new LinkedList<>();
    static List<AgentUser> listAgents1 = new LinkedList<>();
    static List<ClientUser> listClients1 = new LinkedList<>();

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

    public List<ClientUser> getListClients1() {
        return listClients1;
    }

    public List<AgentUser> getListAgents1() {
        return listAgents1;
    }

    public void addAgent(AgentUser e) {
        listAgents1.add(e);
    }

    public void addClient(ClientUser e) {
        listClients1.add(e);
    }


    public void findAgent(ClientUser client,NewVersionThread my) {
        for (AgentUser us : listAgents1) {
            if (us.getChatter() == null) {
                us.setChatter(my);
                client.setChatter(us.getMy());
            }
        }
    }

    public void findClient (AgentUser agent,NewVersionThread my) {
        for (ClientUser us : listClients1) {
            if (us.getChatter() == null) {
                us.setChatter(my);
                agent.setChatter(us.getMy());
            }
        }
    }

    /*public void findChatter() {
        if (user.role.equals("client")) {
            for (User us : serverMethods.getListAgents()) {
                if (us.getChatter() == null) {
                    us.setChatter(this);
                    user.setChatter(us.getMy());
                }
            }
        } else if (user.role.equals("agent")) {
            for (User us : serverMethods.getListClients()) {
                if (us.getChatter() == null) {
                    us.setChatter(this);
                    user.setChatter(us.getMy());
                }
            }
        }
    }*/


}
