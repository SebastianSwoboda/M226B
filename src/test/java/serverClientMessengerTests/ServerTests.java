package serverClientMessengerTests;

import org.junit.Test;
import serverClientMessenger.Client;
import serverClientMessenger.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ServerTests {


    private Server server1 = new Server(8888);
    private Server server2 = new Server(8118);
    private ServerSocket socket1;
    private ServerSocket socket2;
    private Socket clientSocket1;
    private Socket clientSocket2;

    public ServerTests() throws IOException {
        socket1 = server1.openServerSocket();
        socket2 = server2.openServerSocket();
    }

    @Test
    public void shouldOpenAServerSocket() throws Exception {


        assertEquals("server1 with port 8888", 8888, socket1.getLocalPort());
        assertEquals("server1 with port 8118", 8118, socket2.getLocalPort());
        socket1.close();
        socket2.close();

    }

    @Test
    public void clientShouldConnectToServer() throws Exception {
        Runnable runnable = () -> {
            try {
                clientSocket1 = server1.acceptConnectionFromClient(socket1);
                clientSocket2 = server2.acceptConnectionFromClient(socket2);
            } catch (IOException e) {
                System.err.println(e);
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(runnable);
        Thread.sleep(1000);
        new Client(8888, "localhost");
        new Client(8118, "localhost");
        assertTrue("client with port 8888", clientSocket1.isConnected());
        assertTrue("client with port 8118", clientSocket2.isConnected());
        socket1.close();
        socket2.close();
    }

    @Test
    public void shouldReceiveMessageFromClient() throws Exception {

        Runnable runnable = () -> {
            try {
                clientSocket1 = server1.acceptConnectionFromClient(socket1);
            } catch (IOException e) {
                System.err.println(e);
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(runnable);
        Thread.sleep(1000);
        server1.startServerThread(clientSocket1);
        Client client1 = new Client(8888, "localhost");
        client1.sendMessage("hello");
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
        assertEquals("client 1 sending message hello", "hello", server1.serverThreads.get(0).receiveMessage(in));
        socket1.close();
        socket2.close();
    }
}
