package dled.github;

import io.vlingo.xoom.actors.Actor;

public class ConsolePrinter extends Actor implements SomeHandler {
    @Override
    public void handle(String message) {
        System.out.println("Received: " + message);
    }
}
