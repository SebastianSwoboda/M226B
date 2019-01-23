package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Control {

    private static final Logger LOGGER = LogManager.getLogger(Control.class);
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    private Client client;
    private Server server;

    void startServer(int port) {
        server = new Server(port);
        executor.submit(server);
    }

    void startClient(String address, int port) throws Exception {
        client = new Client(port, address);
        executor.submit(client);
    }

    void sendMessageToServer(String message) throws IOException {
        LOGGER.info("Client sending message to server");
        client.sendMessage(message);
    }

    void sendMessageToClient(String message) throws IOException {
        LOGGER.info("Server sending message to client");
        server.serverThread.sendMessage(message);
    }
}
