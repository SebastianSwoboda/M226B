package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
   Socket serverSocket;
    public Client(Socket serverSocket){


this.serverSocket=serverSocket;

    }


    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    public static String messageFromServer;
    public static String messageForServer;


    public void run() {
        try {


            Socket serverSocket = new Socket("localhost", 6606);
            LOGGER.info("Connection to server: " + serverSocket.isConnected());

            while(serverSocket.isConnected()){
                PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(serverSocket.getInputStream()));


                messageFromServer = in.readLine();
                out.println(messageForServer);
            }


        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public void sendMessage(){
        try {
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println(messageForServer);

        }catch (IOException e){

        }
    }

    public void receiveMessage(){
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));
            //LOGGER.info(in.readLine());
            messageFromServer=in.readLine();

        }catch (IOException e){

    }

/*
    protected void sendDataToServer(String message) {

        try {

            Socket serverSocket = new Socket("localhost", 6606);
            LOGGER.info("Connection to server: "+ serverSocket.isConnected());

            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));



            messageFromServer=in.readLine();
            out.println(message);






        } catch (IOException e) {
            LOGGER.error("Error when sending message to host" + e);

        }



    }*/


}}
