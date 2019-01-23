package serverClientMessengerTests;
import org.junit.Test;
import serverClientMessenger.MessageLogger;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class MessageLoggerTest {
    @Test
    public void shouldWriteStringToTextFile()throws IOException {
        MessageLogger messageLogger = new MessageLogger();
        messageLogger.logMessages("lol");
        messageLogger.logMessages("trollololo");
    }
}

