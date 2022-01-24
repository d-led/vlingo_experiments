package dled.github;

import io.vlingo.xoom.actors.Definition;
import io.vlingo.xoom.actors.World;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        final World world = World.start("playground");

        try {
            final AccountListener listener = world.actorFor(
                AccountListener.class,
                ConsoleAccountListener.class
            );

            final Account account = world.actorFor(
                Account.class,
                Definition.has(SimpleAccount.class,
                        Definition.parameters(
                                "A-1234",
                                new BigDecimal(100),
                                listener))
            );

            account.depositFunds(new BigDecimal(50));
            account.withdrawFunds(new BigDecimal(75));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // waits for quiescence
            world.terminate();
        }
    }
}
