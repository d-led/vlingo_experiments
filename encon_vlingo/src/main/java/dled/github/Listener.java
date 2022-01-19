package dled.github;

import io.vlingo.xoom.common.Completes;
import io.vlingo.xoom.actors.Stoppable;

public interface Listener {
    Completes<Stoppable> waitForCall();
}
