package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Messaging implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Messaging.class);

    abstract void sendMessage(String message) throws IOException;

    public String receiveMessage(BufferedReader in) {
        String message = null;
        try {
            MessageLogger messageLogger = new MessageLogger();
            message = in.readLine();
            messageLogger.logMessages(message);
            LOGGER.info("received message " + message);
        } catch (IOException e) {
            LOGGER.error("when trying to receive message" + e);
        }
        return message;
    }


}
