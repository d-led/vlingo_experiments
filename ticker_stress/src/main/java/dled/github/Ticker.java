package dled.github;

import io.vlingo.actors.Actor;
import io.vlingo.common.Scheduled;

public class Ticker extends Actor implements Ticking, Scheduled {
    int count = 0;

    @Override
    public void tickAway() {
        count++;
        System.out.println("Tick " + count);
        scheduler().scheduleOnce(this,null, 0L, 1000L);
    }

    @Override
    public void intervalSignal(Scheduled scheduled, Object data) {
        ((Ticking)scheduled).tickAway();
    }
}
