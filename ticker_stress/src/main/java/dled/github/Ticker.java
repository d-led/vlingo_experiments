package dled.github;

import io.vlingo.actors.Actor;
import io.vlingo.actors.Scheduled;

public class Ticker extends Actor implements Ticking, Scheduled {
    int count = 0;

    @Override
    public void tickAway() {
        count++;
        System.out.println("Tick " + count);
        scheduler().scheduleOnce(this,null, 0L, 1000L);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            this.stop();
//        }
//        this.selfAs(Ticking.class).tickAway();
    }

    @Override
    public void intervalSignal(Scheduled scheduled, Object data) {
        ((Ticking)scheduled).tickAway();
    }
}
