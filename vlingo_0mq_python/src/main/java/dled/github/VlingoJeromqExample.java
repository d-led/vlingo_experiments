package dled.github;

import io.vlingo.actors.*;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class VlingoJeromqExample {

    public static void main(String[] args) {
        final World world = World.startWithDefaults("playground");

        try {

            // dependency injection
            final SomeHandler handler= world.actorFor(
                    Definition.has(
                            ConsolePrinter.class,
                            Definition.NoParameters
                    ),
                    SomeHandler.class
            );

            final SomeListener listener = world.actorFor(
                Definition.has(
                    JeroMqListener.class,
                    Definition.parameters(
                        handler
                    )
                ),
                SomeListener.class
            );

            // start listening
            listener.listen();

            // CI timeout
            Thread.sleep(20000);
            handler.handle("Exiting due to timeout");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            world.terminate();
        }
    }
}
