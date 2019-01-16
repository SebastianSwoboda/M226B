package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Server.class);

    public void run() {
        boolean listening = true;
        try {

            ServerSocket serverSocket = new ServerSocket(6606);
            while(listening){
                LOGGER.info("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");

                Socket clientSocket = serverSocket.accept();
                new ServerThread(clientSocket).start();
            }

        } catch (IOException e) {
            LOGGER.error("error when creating server socket" + e);
        }

    }


}
