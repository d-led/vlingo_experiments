package dled.github;

import io.vlingo.actors.Definition;
import io.vlingo.actors.World;

import java.math.BigDecimal;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        final World world = World.start("playground");

        try {
            final Account account = world.actorFor(
                    Definition.has(SimpleAccount.class, Definition.parameters("A-1234", new BigDecimal(100))),
                    Account.class
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // waits for quiescence
            world.terminate();
        }
    }
}
