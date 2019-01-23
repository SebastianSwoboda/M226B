package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private int port;

    public ServerThread serverThread;

    public Server(int port) {
        this.port = port;
    }

    public ServerSocket openServerSocket() throws IOException {
        return new ServerSocket(port);
    }

    public Socket acceptConnectionFromClient(ServerSocket serverSocket) throws IOException {
        Socket clientSocket;
        LOGGER.info("Waiting for client on port " + serverSocket.getLocalPort() + "...");
        clientSocket = serverSocket.accept();
        return clientSocket;
    }

    public void startServerThread(Socket clientSocket) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        serverThread = new ServerThread(clientSocket);
        executor.submit(serverThread);
    }

    public void run() {
        try {
            ServerSocket socket = openServerSocket();
            while (true) {
                startServerThread(acceptConnectionFromClient(socket));
            }
        } catch (IOException e) {
            LOGGER.error("error when creating server socket" + e);
            Thread.currentThread().interrupt();

        }
    }


}
