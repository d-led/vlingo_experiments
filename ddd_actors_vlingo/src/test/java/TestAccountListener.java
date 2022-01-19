import dled.github.AccountListener;
import io.vlingo.xoom.actors.Actor;
import io.vlingo.xoom.common.Completes;
import io.vlingo.xoom.actors.testkit.TestUntil;

import java.math.BigDecimal;

public class TestAccountListener extends Actor implements AccountListener {
    final TestUntil happenings;
    BigDecimal balanceSeen;

    public TestAccountListener(TestUntil happenings) {
        this.happenings = happenings;
    }

    @Override
    public void accountOpened(String id, BigDecimal initialBalance) {
        balanceSeen = initialBalance;
        happenings.happened();
    }

    @Override
    public void fundsDeposited(String id, BigDecimal amount, BigDecimal balance) {
        balanceSeen = balance;
        happenings.happened();
    }

    @Override
    public void fundsWithdrawn(String id, BigDecimal amount, BigDecimal balance) {
        balanceSeen = balance;
        happenings.happened();
    }

    @Override
    public Completes<BigDecimal> lastBalanceSeen() {
        return completes().with(balanceSeen);
    }
}
