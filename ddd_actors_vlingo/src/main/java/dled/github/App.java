package dled.github;

import io.vlingo.actors.Definition;
import io.vlingo.actors.World;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        final World world = World.start("playground");

        try {
            final AccountListener listener = world.actorFor(
                    Definition.has(ConsoleAccountListener.class, Definition.NoParameters),
                    AccountListener.class
            );

            final Account account = world.actorFor(
                    Definition.has(SimpleAccount.class,
                            Definition.parameters(
                                    "A-1234",
                                    new BigDecimal(100),
                                    listener)),
                    Account.class
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
