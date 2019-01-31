package serverClientMessenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Is implemented by the ServerThread and Client class, which need this class to send messages and receive them.
 * @author Silvio Merz and Sebastian Swoboda
 * @since 1.0
 *
 *
 */
public abstract class Messaging implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Messaging.class);

    abstract void sendMessage(String message) throws IOException;

   public String receiveMessage(BufferedReader in) {
        String message;
        try {
            message = in.readLine();
            LOGGER.info("received message " + message);
            if (message.equals(null) || message.equals(";)")) {
                throw new RuntimeException();
            }
        } catch (IOException e) {

            LOGGER.error("when trying to receive message" + e, e);
            throw new RuntimeException();
        }
        return message;
    }
}
