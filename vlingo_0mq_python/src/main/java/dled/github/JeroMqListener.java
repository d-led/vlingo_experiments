package dled.github;

import io.vlingo.actors.Actor;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class JeroMqListener extends Actor implements SomeListener {

    private final SomeHandler handler;
    private boolean listening = true;
    private final int TIMEOUT = 2000/*ms*/;

    ZContext context = new ZContext();
    ZMQ.Socket socket;

    public JeroMqListener(SomeHandler handler) {
        this.handler = handler;
        socket = context.createSocket(ZMQ.PULL);
        socket.bind("tcp://*:5678");
        socket.setReceiveTimeOut(TIMEOUT);
    }

    @Override
    public void listen() {
        if (!listening)
            return;

        handler.handle("listening...");

        byte[] request;
        do {
            // returns null on timeout
            request = socket.recv(0);

            if (request == null)
                break;

            String message = new String(request, ZMQ.CHARSET);

            if (message.equalsIgnoreCase("stop")) {
                stop();
            }

            handler.handle("[0mq] " + message);

        } while (true);

        // be nice to other actors, give up thread control
        this.selfAs(SomeListener.class).listen();
    }

    @Override
    public void stop() {
        handler.handle("Stopping as commanded");
        listening = false;

        // stop listening
        super.stop();

        // forced exit for the demo only
        System.exit(0);
    }
}
