package dled.github;

import io.vlingo.actors.Actor;
import io.vlingo.actors.Completes;

import java.math.BigDecimal;

public class ConsoleAccountListener extends Actor implements AccountListener {
    BigDecimal balanceSeen;

    @Override
    public void accountOpened(String id, BigDecimal initialBalance) {
        balanceSeen = initialBalance;
        System.out.println("AccountOpened id: " + id + ", initial balance: " + initialBalance);
    }

    @Override
    public void fundsDeposited(String id, BigDecimal amount, BigDecimal balance) {
        balanceSeen = balance;
        System.out.println("FundsDeposited id: " + id + ", amount" + amount + ", balance: " + balance);
    }

    @Override
    public void fundsWithdrawn(String id, BigDecimal amount, BigDecimal balance) {
        balanceSeen = balance;
        System.out.println("FundsWithdrawn id: " + id + ", amount" + amount + ", balance: " + balance);
    }

    @Override
    public Completes<BigDecimal> lastBalanceSeen() {
        return completes().with(balanceSeen);
    }
}
