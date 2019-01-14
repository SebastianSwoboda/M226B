package sample;

import java.io.*;
import java.net.Socket;

public class Client {

    Socket client = new Socket();

    protected void connectToServer(){
        String serverName = "localhost";
        int serverPort =6606;
        //Socket client=null;

        try{

            client = new Socket(serverName, serverPort);







            //client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return client;
    }


    protected void sendDataToServer(String message){

        try {

            client = new Socket("localhost", 6606);
            System.out.println(client.isConnected());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(message);


        } catch (IOException e) {
            System.err.println("Error when sending message to host" + e);

        }

    }


}
