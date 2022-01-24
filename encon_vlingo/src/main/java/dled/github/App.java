package dled.github;

import io.vlingo.xoom.actors.World;

public class App {
    public static void main(String[] args) {

        final World world = World.start("playground");

        try {
            final Listener listener = world.actorFor(
                Listener.class,
                Joe.class
            );

            listener
                    .waitForCall()
                    .andThen(a->{
                        System.out.println("Early exit ...");
                        a.stop();
                        world.terminate();
                        System.exit(0);
                        return a;
                    });
            ;

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
