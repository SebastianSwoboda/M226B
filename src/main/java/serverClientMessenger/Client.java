package serverClientMessenger;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mortbay.util.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Messaging {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    static String messageFromServer;
    private Socket serverSocket;

    public Client(int port, String address) throws IOException {
        serverSocket = new Socket(address, port);
    }

    public void run() {
        try {
            LOGGER.info("Is connected to server: " + serverSocket.isConnected());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));
            while (serverSocket.isConnected()) {
                messageFromServer = receiveMessage(in);
                Platform.runLater(new UpdateMessageLabel(true));
            }
            LOGGER.info("client has disconnected from server");
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            LOGGER.error("when receiving message from Server" + e);
        }
    }

    @Override
    void sendMessage(String message) throws IOException {
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        out.println(message);
    }
}
