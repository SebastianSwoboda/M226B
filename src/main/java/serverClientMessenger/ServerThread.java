package serverClientMessenger;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverClientMessenger.controllers.ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class is used to maintain the connection between the client. It will constantly listen for any new messages and
 * is also responsible for sending messages to a client.
 * @author Sebastian Swoboda
 * @since 1.0
 * This class is used to maintain the connection between the client. It will constantly listen for any new messages and
 * is also responsible for sending messages to a client.
 */
public class ServerThread extends Messaging {
    private static final Logger LOGGER = LogManager.getLogger(ServerThread.class);
    private static final Logger MESSAGE_LOGGER = LogManager.getLogger("MY_MESSAGES");
    private Socket clientSocket;
    private ServerController serverController;

    public ServerThread(Socket clientSocket, ServerController serverController) {
        this.serverController = serverController;
        this.clientSocket = clientSocket;
    }

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        String messageFromClient;
        LOGGER.info("Just connected to " + clientSocket.getRemoteSocketAddress());
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (clientSocket.isConnected()) {
                messageFromClient = receiveMessage(in);
                Platform.runLater(new UpdateMessageLabel("server", messageFromClient, serverController));
                MESSAGE_LOGGER.info("Client: " + messageFromClient);
            }
            LOGGER.info("client has disconnected from server, socket will be closed");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            Platform.runLater(new UpdateMessageLabel("serverError", "Lost Connection to Client", serverController));
            LOGGER.error("Client socket has been closed" + e, e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void sendMessage(String message) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        LOGGER.info("trying to send message to client " + message);
        out.println(message);
        MESSAGE_LOGGER.info("Server: " + message);
    }
}










