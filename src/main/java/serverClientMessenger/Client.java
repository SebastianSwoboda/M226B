package serverClientMessenger;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    static String messageFromServer;
    static String messageForServer;
    private Socket serverSocket;

    public Client(Socket serverSocket) {


        this.serverSocket = serverSocket;

    }

    public void run() {
        try {


            LOGGER.info("Connection to server: " + serverSocket.isConnected());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));

            while (serverSocket.isConnected()) {

                messageFromServer = in.readLine();
                Platform.runLater(new UpdateMessageLabel(true));

            }
            LOGGER.info("client has disconnected from server");
            Thread.currentThread().interrupt();


        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    void sendMessageToServer() {
        try {
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println(messageForServer);

        } catch (IOException e) {
            LOGGER.error("when sending message to server" + e);
        }
    }


}
