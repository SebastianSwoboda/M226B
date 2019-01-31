package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverClientMessenger.controllers.ClientController;
import serverClientMessenger.controllers.ServerController;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  Either the clientController or the serverController will create an object of this class and start either a server or
 * a client. It provides a bridge for the ui to communicate with the logic. Example: When you decide to send a message as a
 * client and press send in the ui, this class will be called and this class will call the corresponding method to
 * send a message to the server.
 * @author Sebastian Swoboda and Silvio Merz
 * @since 1.0

 */
public class Control {

    private static final Logger LOGGER = LogManager.getLogger(Control.class);
    private  Client client;
    private  Server server;
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    public void startServer(int port, ServerController serverController) throws RuntimeException {
        server = new Server(port, serverController);
        executor.submit(server);
    }

    public void stopServer() throws IOException {
        server.closeServerSocket();
    }

    public void startClient(String address, int port, ClientController clientController) throws Exception {
        client = new Client(port, address, clientController);
        executor.submit(client);
    }

    public void stopClient() {
        try {
            if (client!=null) {


                client.closeServerSocket();
            }
        } catch (IOException e) {
            LOGGER.error("when trying to close client socket" + e, e);
        }
    }

    public void sendMessageToServer(String message) throws IOException {
        LOGGER.info("Client sending message to server");
        client.sendMessage(message);
    }

    public void sendMessageToClient(String message) throws IOException {
        LOGGER.info("Server sending message to client");
        for (ServerThread e : server.serverThreads) {
            e.sendMessage(message);

        }
    }
}
