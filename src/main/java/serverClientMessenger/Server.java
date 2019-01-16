package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        boolean listening = true;
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            while (listening) {
                LOGGER.info("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");

                Socket clientSocket = serverSocket.accept();
                new ServerThread(clientSocket).start();
                listening = false;
            }
            LOGGER.info("server socket has been closed");
            Thread.currentThread().interrupt();
            return;

        } catch (IOException e) {
            LOGGER.error("error when creating server socket" + e);
        }

    }


}
