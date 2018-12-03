package dled.github;

import io.vlingo.actors.Actor;

public class ConsolePrinter extends Actor implements SomeHandler {
    @Override
    public void handle(String message) {
        System.out.println("Received: " + message);
    }
}
