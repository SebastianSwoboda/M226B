package serverClientMessenger;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ServerThread.class);
    static String messageFromClient;
    static String messageForClient;
    private static Socket clientSocket;

    ServerThread(Socket clientSocket) {
        super("ServerThread");
        ServerThread.clientSocket = clientSocket;
    }

    static void sendMessageToClient() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            LOGGER.info("trying to send message to client " + messageForClient);
            out.println(ServerThread.messageForClient);
        } catch (IOException e) {
            LOGGER.error("while sending message to client" + e);
        }
    }

    public void run() {
        LOGGER.info("Just connected to " + clientSocket.getRemoteSocketAddress());
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (clientSocket.isConnected()) {
                messageFromClient = in.readLine();
                Platform.runLater(new UpdateMessageLabel(false));
                LOGGER.info("received client message " + messageFromClient);
            }
            LOGGER.info("client has disconnected from server, socket will be closed");
            Thread.currentThread().interrupt();
        } catch (SocketTimeoutException s) {
            LOGGER.error("Socket timed out!" + s);

        } catch (IOException e) {
            LOGGER.error("while reading message from client" + e);
        }
    }
}










