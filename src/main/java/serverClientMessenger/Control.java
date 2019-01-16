package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Control  {

    private static final Logger LOGGER = LogManager.getLogger(Control.class);


    Client client;

    public void startServer(){
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(new Server());







    }

    public void startClient(){

        try {


            Socket serverSocket = new Socket("localhost", 6606);
            client = new Client(serverSocket);
            LOGGER.info("Client connected to server");



    }catch (IOException e){
        }
    }

    public void sendMessage(){
        LOGGER.info("Client sending message to server");

        client.sendMessage();
    }

    public void receiveMessage(){
        client.receiveMessage();
    }










}
