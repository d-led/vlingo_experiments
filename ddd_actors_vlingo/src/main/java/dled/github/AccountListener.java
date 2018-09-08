package dled.github;

import io.vlingo.actors.Completes;

import java.math.BigDecimal;

public interface AccountListener {
    void accountOpened(String id, BigDecimal initialBalance);

    void fundsDeposited(String id, BigDecimal amount, BigDecimal balance);

    void fundsWithdrawn(String id, BigDecimal amount, BigDecimal balance);

    Completes<BigDecimal> lastBalanceSeen();
}
