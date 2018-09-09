package dled.github;

import io.vlingo.actors.Completes;
import io.vlingo.actors.Stoppable;

public interface Listener {
    Completes<Stoppable> waitForCall();
}
