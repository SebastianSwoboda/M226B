package serverClientMessenger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MessageLogger {

    FileWriter writer;

   public MessageLogger() throws IOException {
        writer = new FileWriter(new File("messagesLog.txt"));
    }

    public void logMessages(String message) throws IOException {
        writer.append(message);

    }

    public void flushMessages()throws IOException{
       writer.flush();
    }

}
