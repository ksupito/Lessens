package newC.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public class ServerMethods {
    static Map<AgentUser, ClientUser> mapAgents = new HashMap<>();
    static List<ClientUser> listClients = new LinkedList<>();
    static Queue<ClientUser> userQueue = new LinkedList<>();

    public Map<AgentUser, ClientUser> getMapAgents() {
        return mapAgents;
    }

    public void addAgentToMap(AgentUser agent) {
        mapAgents.put(agent, null);
    }

    public List<ClientUser> getListClients1() {
        return listClients;
    }

    public void addClient(ClientUser e) {
        listClients.add(e);
    }

    public void changeQueue(ClientUser client) {
        for (ClientUser us : listClients) {
            if (us.equals(client)) {
                userQueue.add(client);
                listClients.remove(client);
            }
        }
    }

    public synchronized boolean searchChat() {
        for (Map.Entry<AgentUser, ClientUser> entry : mapAgents.entrySet()) {
            if (userQueue.size() != 0) {
                AgentUser agent = entry.getKey();
                ClientUser client = entry.getValue();
                if (agent != null && client == null) {
                    ClientUser clientFromQueue = userQueue.remove();
                    clientFromQueue.setAgentUser(agent);
                    agent.setClientUser(clientFromQueue);
                    mapAgents.put(agent, clientFromQueue);
                    if (clientFromQueue.getMessages().size() != 0) {
                        try {
                            sendMessages(agent.getDos(), clientFromQueue.name, clientFromQueue);
                        } catch (IOException e) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized void sendMessages(DataOutputStream dos, String name, ClientUser clientUser) throws IOException {

        for (String mes : clientUser.getMessages()) {
            dos.writeUTF(name + " : " + mes + "\n");
            dos.flush();
        }
        clientUser.getMessages().clear();
    }

    public synchronized void send(String line, DataOutputStream dos, String name) throws IOException {
        dos.writeUTF(name + " : " + line + "\n");
        dos.flush();
    }

    public synchronized void exitClient(ClientUser cl) {
        for (Map.Entry<AgentUser, ClientUser> entry : mapAgents.entrySet()) {
            AgentUser agent = entry.getKey();
            ClientUser client = entry.getValue();
            if (client == cl && agent != null) {
                mapAgents.replace(agent, null);
            }
        }
    }

    public synchronized boolean exitAgent(AgentUser ag) {
        for (Map.Entry<AgentUser, ClientUser> entry : mapAgents.entrySet()) {
            AgentUser agent = entry.getKey();
            ClientUser client = entry.getValue();
            if (agent == ag) {
                if (client != null) {
                    listClients.add(client);
                }
                mapAgents.remove(agent);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean disconnectClient(ClientUser cl) {
        for (Map.Entry<AgentUser, ClientUser> entry : mapAgents.entrySet()) {
            AgentUser agent = entry.getKey();
            ClientUser client = entry.getValue();
            if (client == cl && agent != null) {
                listClients.add(client);
                entry.setValue(null);
                return true;
            }
        }
        return false;
    }

}
