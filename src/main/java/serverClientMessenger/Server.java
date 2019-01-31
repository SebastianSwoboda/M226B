package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverClientMessenger.controllers.ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class will create a server socket and wait for a client to connect to this socket, when connected it will create
 * a ServerThread. This server class will create a new ServerThread for each client that connects to it.
 * @author Sebastian Swoboda
 * @since 1.0
 */
public class Server implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    public List<ServerThread> serverThreads = new ArrayList<>();
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    private int counter = 0;
    private int port;
    private ServerController serverController;
    private ServerSocket socket;

    public Server(int port, ServerController serverController) {
        this.port = port;
        this.serverController = serverController;
    }

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

    public void startServerThread(Socket clientSocket) throws RuntimeException {
        try {
            serverThreads.add(new ServerThread(clientSocket, serverController));

            executor.submit(serverThreads.get(counter));
            counter++;
        } catch (RuntimeException e) {
            LOGGER.error("server lost connection to client" + e, e);
            throw new RuntimeException();
        }
    }

    public void run() {
        try {

            socket = openServerSocket();
            while (true) {
                startServerThread(acceptConnectionFromClient(socket));
            }
        } catch (IOException e) {

            LOGGER.error("error when creating server socket" + e, e);
            Thread.currentThread().interrupt();
            throw new RuntimeException();

        } catch (RuntimeException e) {

            LOGGER.error("when trying to receive a message from a client" + e, e);
            throw new RuntimeException();
        }
    }

    void closeServerSocket() throws IOException {
        for (ServerThread thread : serverThreads
        ) {
            thread.sendMessage(";)");
        }
        socket.close();
    }
}
