package dled.github;
import io.vlingo.actors.Definition;
import io.vlingo.actors.World;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {

        final World world = World.start("playground");

        try {
            final Listener listener = world.actorFor(
                    Definition.has(Joe.class, Definition.NoParameters),
                    Listener.class
            );

            listener.waitForCall();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // waits for quiescence
            world.terminate();
        }

    }
}
