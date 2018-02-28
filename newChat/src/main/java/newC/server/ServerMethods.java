package newC.server;

import org.apache.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ServerMethods {
    static Map<AgentUser, ClientUser> mapAgents = new HashMap<>();
    static List<ClientUser> listClients = new LinkedList<>();
    static BlockingQueue<ClientUser> userQueue = new ArrayBlockingQueue<ClientUser>(1000);
    String chatName = "------------->";
    private static final Logger log = Logger.getLogger(ServerMethods.class.getSimpleName());


    public String getChatName() {
        return chatName;
    }

    public Map<AgentUser, ClientUser> getMapAgents() {
        return mapAgents;
    }

    public void addAgentToMap(AgentUser agent) {
        mapAgents.put(agent, null);
    }

    public void addClientToQueue(ClientUser client) {
        userQueue.add(client);
    }

    public BlockingQueue<ClientUser> getUserQueue() {
        return userQueue;
    }

    public void addClient(ClientUser e) {
        listClients.add(e);
    }

    public List<ClientUser> getListClients() {
        return listClients;
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
                    try {
                        ClientUser clientFromQueue = userQueue.remove();
                        clientFromQueue.setAgentUser(agent);
                        agent.setClientUser(clientFromQueue);
                        mapAgents.put(agent, clientFromQueue);
                        send("new chat!", agent.getDos(), chatName);
                        send("new chat!", clientFromQueue.getDos(), chatName);
                        if (clientFromQueue.getMessages().size() != 0) {
                            sendMessages(agent.getDos(), clientFromQueue.name, clientFromQueue);
                        }
                        log.info("new chat started");
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        System.out.println(e);
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

    public synchronized boolean exitClient(ClientUser cl) throws IOException {
        for (Map.Entry<AgentUser, ClientUser> entry : mapAgents.entrySet()) {
            AgentUser agent = entry.getKey();
            ClientUser client = entry.getValue();
            if (client == cl) {                                            // && agent != null
                send("client exited", agent.getDos(), chatName);
                client.getDos().writeUTF("1");
                mapAgents.replace(agent, null);
                log.info("client exited");
                return true;
            }
        }
        return false;
    }

    public synchronized boolean exitClientFromQueue(ClientUser cl) throws IOException {
        if (userQueue.contains(cl)) {
            userQueue.remove(cl);
            cl.getDos().writeUTF("1");
            return true;
        }
        return false;
    }

    public synchronized boolean exitClientFromList(ClientUser cl) throws IOException {
        for (ClientUser client : listClients) {
            if (client == cl) {
                client.getDos().writeUTF("1");
                listClients.remove(client);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean exitAgent(AgentUser ag) throws IOException {
        for (Map.Entry<AgentUser, ClientUser> entry : mapAgents.entrySet()) {
            AgentUser agent = entry.getKey();
            ClientUser client = entry.getValue();
            if (agent == ag) {
                if (client != null) {
                    listClients.add(client);
                    send("agent exited", client.getDos(), chatName);
                }
                agent.getDos().writeUTF("1");
                mapAgents.remove(agent);
                log.info("agent exited");
                return true;
            }
        }
        return false;
    }

    public synchronized boolean leaveClient(ClientUser cl) throws IOException {
        for (Map.Entry<AgentUser, ClientUser> entry : mapAgents.entrySet()) {
            AgentUser agent = entry.getKey();
            ClientUser client = entry.getValue();
            if (client == cl) {     //&& agent != null
                listClients.add(client);
                entry.setValue(null);
                send("client lieved", agent.getDos(), chatName);
                log.info("client lieved");
                return true;
            }
        }
        return false;
    }

    public synchronized boolean leaveClientFromQueue(ClientUser cl) throws IOException {
        if (userQueue.contains(cl)) {
            userQueue.remove(cl);
            listClients.add(cl);
            return true;
        }
        return false;
    }
}
