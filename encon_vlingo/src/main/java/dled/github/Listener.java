package dled.github;

import io.vlingo.common.Completes;
import io.vlingo.actors.Stoppable;

public interface Listener {
    Completes<Stoppable> waitForCall();
}
