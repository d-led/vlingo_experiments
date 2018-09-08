package dled.github;

import io.vlingo.actors.Actor;

public class Ticker extends Actor implements Ticking {
    int count = 0;

    @Override
    public void tickAway() {
        count++;
        System.out.println("Tick " + count);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            this.stop();
        }
        this.selfAs(Ticking.class).tickAway();
    }
}
