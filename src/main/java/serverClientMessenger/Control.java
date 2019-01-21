package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Control {

    private static final Logger LOGGER = LogManager.getLogger(Control.class);


    private Client client;

    void startServer(int port) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(new Server(port));


    }

    void startClient(String address, int port) {

        try {


            Socket serverSocket = new Socket(address, port);
            client = new Client(serverSocket);
            Thread thread = new Thread(client);
            thread.start();


            //client = new Client(serverSocket);
            LOGGER.info("Client connected to server");


        } catch (IOException e) {
            LOGGER.error("when starting the client" + e);
        }
    }

    void sendMessage() {
        LOGGER.info("Client sending message to server");

        client.sendMessage();
    }

    void receiveMessage() {
        LOGGER.info("calling receive method for client");
        client.receiveMessage();
    }


}
