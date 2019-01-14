package studies.networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server implements Callable {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    DataInputStream inputStreamFromServer;
    Socket server;
    String clientMessage;
    private ServerSocket serverSocket;

    @Override
    public String call() throws Exception {




        return run();
    }

    public  String run() {
        String serverMessage = null;

            Boolean continueWaitng = true;
            while (continueWaitng) {
                try {
                    serverSocket = new ServerSocket(6606);
                    System.out.println("Waiting for client on port " +
                            serverSocket.getLocalPort() + "...");
                    server = serverSocket.accept();
                    continueWaitng = false;


                    System.out.println("Just connected to " + server.getRemoteSocketAddress());
                    serverMessage=getServerMessage();


                    //TODO new thread, use above method to fix maybe, try using new thread oly for serverSocket.accept, callable instead of runnable
                    /*
                    while (server.isConnected()) {
                        InputStream inFromServer = server.getInputStream();
                        inputStreamFromServer = new DataInputStream(inFromServer);
                        clientMessage = inputStreamFromServer.readUTF();
                        System.out.println(clientMessage);

                        //clientMessage = inputStreamFromServer.readUTF();

                    }*/
                    //DataInputStream in = new DataInputStream(server.getInputStream());

                    //System.out.println(in.readUTF());
                    //DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    //out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                    //      + "\nGoodbye!");
                    //server.close();

                } catch (SocketTimeoutException s) {
                    System.out.println("Socket timed out!");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            //return clientMessage;

        return serverMessage;
    }

    protected String getServerMessage() {
        try {


            InputStream inFromServer = server.getInputStream();
            inputStreamFromServer = new DataInputStream(inFromServer);
            clientMessage = inputStreamFromServer.readUTF();
            System.out.println(clientMessage);
        } catch (IOException e) {

        }return  clientMessage;

    }


}




/*
     static void main(String [] args) {

        try {
            Thread t = new Server();
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


