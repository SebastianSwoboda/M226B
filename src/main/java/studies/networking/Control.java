package studies.networking;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Control {
    Callable server = new Server();
    Future<String> future;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void startHost(){


        future = executor.submit(server);





    }

    public void getClientMessage() throws Exception{
        String clientMessage= future.get();
    }







}
