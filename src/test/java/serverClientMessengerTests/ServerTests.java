package serverClientMessengerTests;

import org.junit.Test;
import serverClientMessenger.Client;
import serverClientMessenger.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;


public class ServerTests {
    Server server1 = new Server(8888);
    Server server2 = new Server(8118);
    ServerSocket socket1;
    ServerSocket socket2;

    Socket clientSocket1;
    Socket clientSocket2;


    public ServerTests() throws IOException {
        socket1 = server1.openServerSocket();
        socket2 = server2.openServerSocket();
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {


             clientSocket1 = server1.acceptConnectionFromClient(socket1);
            // clientSocket2 = server2.acceptConnectionFromClient(socket2);

        }catch (IOException e){}
    }};

    @Test
    public void shouldOpenAServerSocket() throws IOException {


        assertEquals("server1 with port 8888", 8888,socket1.getLocalPort());
        assertEquals("server1 with port 8118", 8118, socket2.getLocalPort());
    }

    @Test
    public void clientShouldConnectToServer() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(runnable);
        /*
        Socket clientSocket1 = server1.acceptConnectionFromClient(socket1);
        Socket clientSocket2 = server2.acceptConnectionFromClient(socket2);*/

        Client client1 = new Client(8888, "localhost");
        Client client2 = new Client(8118, "localhost");

        assertEquals("client 1", true, clientSocket1.isConnected());
      //  assertEquals("client 2", true, clientSocket2.isConnected());


    }


}
