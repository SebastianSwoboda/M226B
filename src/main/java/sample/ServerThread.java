package sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ServerThread.class);
    public static String messageFromClient;
    public static String messageForClient;
    Socket clientSocket;


    public ServerThread(Socket clientSocket) {
        super("ServerThread");
        this.clientSocket = clientSocket;
    }

    public void run() {

        try (

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (clientSocket.isConnected()) {

                LOGGER.info("Just connected to " + clientSocket.getRemoteSocketAddress());

                out.println(messageForClient);

                messageFromClient = in.readLine();
                LOGGER.info(messageFromClient);


            }
        } catch (SocketTimeoutException s) {
            LOGGER.error("Socket timed out!");

        } catch (IOException e) {
            e.printStackTrace();

        }


    }


}







/*
     static void main(String [] args) {

        try {
            Thread t = new ServerThread();
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


