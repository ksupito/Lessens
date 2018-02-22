import chat.classes.Agent;
import chat.classes.Client;
import chat.server.ServerChat;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TestServerChat {
    ServerChat serverChat = new ServerChat();
    Client client = Mockito.mock(Client.class);
    Agent agent = Mockito.mock(Agent.class);


    @Test
    public void testExitClientTrue() {
        serverChat.getMapAgents().put(agent, client);
        assertTrue(serverChat.exitClient(client));
    }

    @Test
    public void testExitClientFalse() {
        assertFalse(serverChat.exitClient(client));
    }

    @Test
    public void testExitClientIncorrectClient() {
        serverChat.getMapAgents().put(agent, client);
        Client newClient = Mockito.mock(Client.class);
        assertFalse(serverChat.exitClient(newClient));
    }


}
