package serverClientMessenger;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverClientMessenger.controllers.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Functions as the client and is used to send messages to the server and receive messages from the server.
 * @author Silvio Merz
 * @since 1.0
 *
 */
public class Client extends Messaging {

    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private static final Logger MESSAGE_LOGGER = LogManager.getLogger("MY_MESSAGES");
    public Socket serverSocket;
    private ClientController clientController;

    public Client(int port, String address, ClientController clientController) throws Exception {
        this.clientController = clientController;
        this.serverSocket = new Socket(address, port);
        LOGGER.info("connected to server");
    }

    public Client(int port, String address) throws Exception {
        this.serverSocket = new Socket(address, port);
        LOGGER.info("connected to server");
    }

    public void run() {
        try {
            String messageFromServer;

            LOGGER.info("Is connected to server: " + serverSocket.isConnected());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));
            while (serverSocket.isConnected()) {
                messageFromServer = receiveMessage(in);
                Platform.runLater(new UpdateMessageLabel("client", messageFromServer, clientController));
            }
            LOGGER.info("client has disconnected from server");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            Platform.runLater(new UpdateMessageLabel("clientError", "Lost connection to server", clientController));
            LOGGER.error("when receiving message from Server" + e, e);
            Thread.currentThread().interrupt();

        }
    }

     void closeServerSocket() throws IOException {
        serverSocket.close();
    }

    @Override
    public void sendMessage(String message) throws IOException {
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        out.println(message);
        MESSAGE_LOGGER.info("Client: " + message);
    }

}
