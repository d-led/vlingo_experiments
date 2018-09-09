package dled.github;

import io.vlingo.actors.Definition;
import io.vlingo.actors.World;

public class App {
    public static void main(String[] args) {
        final World world = World.start("playground");
        try {
            final Ticking ticker = world.actorFor(
                Definition.has(Ticker.class, Definition.NoParameters),
                Ticking.class
            );

            ticker.tickAway();

            int badActorCount = 10;
            for (int i = 0; i < badActorCount; i++) {
                final Ticking badActor = world.actorFor(
                        Definition.has(Loader.class, Definition.parameters(i+1)),
                        Ticking.class
                );
                badActor.tickAway();
                Thread.sleep(2000);
            }

            Thread.sleep(30000);
            System.out.println("Forcing an exit");
            System.exit(0);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            // waits for quiescence
            world.terminate();
        }
    }
}
