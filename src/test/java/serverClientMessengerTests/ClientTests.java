package serverClientMessengerTests;

import org.junit.Test;
import serverClientMessenger.Client;
import serverClientMessenger.Server;
import serverClientMessenger.ServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class ClientTests {

    private Server server = new Server(8889);
    private ServerSocket socket;
    private Socket clientSocket;

    public ClientTests() throws IOException {
        socket = server.openServerSocket();
    }


    @Test
    public void shouldReceiveMessageFromServer() throws Exception {
        Runnable runnable = () -> {
            try {
                clientSocket = server.acceptConnectionFromClient(socket);
            } catch (IOException e) {
                System.err.println(e);
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(runnable);
        Thread.sleep(1000);
        Client client = new Client(8889, "localhost");
        ServerThread serverThread = new ServerThread(clientSocket);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.serverSocket.getInputStream()));
        serverThread.sendMessage("hello");
        assertEquals("message from server hello", "hello", client.receiveMessage(in));
        socket.close();
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfUnableToConnectToServer() throws Exception {
        new Client(8889, "123");
    }
}
