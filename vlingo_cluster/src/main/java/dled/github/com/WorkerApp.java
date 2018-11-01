package dled.github.com;

import io.vlingo.actors.World;

public class WorkerApp implements AppToRun {
    @Override
    public void run() {
        final World world = World.start("playground");

        try {
            System.out.println("starting the worker");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // waits for quiescence
            world.terminate();
        }
    }
}
