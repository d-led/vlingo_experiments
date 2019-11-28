package dled.github;

import io.vlingo.actors.Definition;
import io.vlingo.actors.World;

public class App {
    public static void main(String[] args) {
        final World world = World.start("playground");
        try {
            final Ticking ticker = world.actorFor(
                Ticking.class,
                Ticker.class
            );

            ticker.tickAway();

            int badActorCount = 12;
            for (int i = 0; i < badActorCount; i++) {
                final Ticking badActor = world.actorFor(
                    Ticking.class,
                    Definition.has(Loader.class, Definition.parameters(i+1))
                );
                badActor.tickAway();
                Thread.sleep(2000);
            }

            Thread.sleep(40000);
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
