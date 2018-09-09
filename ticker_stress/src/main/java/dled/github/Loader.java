package dled.github;

import io.vlingo.actors.Actor;

public class Loader extends Actor implements Ticking {
    int count = 0;
    int id;

    public Loader(int id) {
        this.id=id;
        System.out.println("Started task " + id);
    }

    @Override
    public void tickAway() {
        while (true) {
            if (count==0)
                System.out.println("          Hi from task " + id);
            count++;
        }
    }
}
