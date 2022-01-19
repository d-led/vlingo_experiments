package dled.github;

import io.vlingo.xoom.actors.Actor;
import io.vlingo.xoom.common.Scheduled;

public class Ticker extends Actor implements Ticking, Scheduled<Boolean> {
    int count = 0;

    @Override
    public void tickAway() {
        count++;
        System.out.println("Tick " + count);
        scheduler().scheduleOnce(this,null, 0L, 1000L);
    }

    @Override
    public void intervalSignal(Scheduled<Boolean> scheduled, Boolean _data) {
        ((Ticking)scheduled).tickAway();
    }
}
