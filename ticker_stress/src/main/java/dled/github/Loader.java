package dled.github;

import io.vlingo.actors.Actor;

public class Loader extends Actor implements Ticking {
    int count = 0;

    public Loader(int id) {
        System.out.println("Started task "+ id);
    }

    @Override
    public void tickAway() {
        while(true)
            count++;

    }
}
