package dled.github;

import io.vlingo.actors.Definition;
import io.vlingo.actors.World;

public class App {
    public static void main(String[] args) {

        final World world = World.start("playground");

        try {
            final Listener listener = world.actorFor(
                    Definition.has(Joe.class, Definition.NoParameters),
                    Listener.class
            );

            listener.waitForCall();

            exitIfHung();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        world.terminate();
    }

    private static void exitIfHung() {
        new Thread(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Exiting ...");
                System.exit(0);
            }
        }).start();
    }
}
