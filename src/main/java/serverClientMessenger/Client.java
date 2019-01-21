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
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));


            while (serverSocket.isConnected()) {


                messageFromServer = in.readLine();
                Platform.runLater(new UpdateMessageLabel(true));
                // out.println(messageForServer);
            }
            LOGGER.info("client has disconnected from server");
            Thread.currentThread().interrupt();
            return;


        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    void sendMessage() {
        try {
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println(messageForServer);

        } catch (IOException e) {
            LOGGER.error("when sending message to server" + e);
        }
    }

    void receiveMessage() {

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));
            LOGGER.info("trying to update message from server");

            //LOGGER.info(in.readLine());


            messageFromServer = in.readLine();

        } catch (IOException e) {
            LOGGER.error("when receiving message from server" + e);
        }


    }
}
