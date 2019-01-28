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

public class ServerThread extends Messaging {
    private static final Logger LOGGER = LogManager.getLogger(ServerThread.class);
    public static Socket clientSocket;
    static String messageFromClient;

    public ServerThread(Socket clientSocket) {
        ServerThread.clientSocket = clientSocket;
    }

    public void run() {
        LOGGER.info("Just connected to " + clientSocket.getRemoteSocketAddress());
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (clientSocket.isConnected()) {
                messageFromClient = receiveMessage(in);
                Platform.runLater(new UpdateMessageLabel(false));
            }
            LOGGER.info("client has disconnected from server, socket will be closed");
            Thread.currentThread().interrupt();
        } catch (SocketTimeoutException s) {
            LOGGER.error("Socket timed out!" + s);

        } catch (IOException e) {
            LOGGER.error("when getting input stream" + e);
        }
    }

    @Override
    public void sendMessage(String message) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        LOGGER.info("trying to send message to client " + message);
        out.println(message);
    }
}










