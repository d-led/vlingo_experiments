package dled.github;

import io.vlingo.xoom.actors.Actor;

import java.math.BigDecimal;

public class SimpleAccount extends Actor implements Account {
    BigDecimal balance;
    String id;
    private AccountListener listener;

    public SimpleAccount(String id, BigDecimal initialBalance, AccountListener listener) {
        balance = initialBalance;
        this.id = id;
        this.listener = listener;

        if (listener != null) {
            listener.accountOpened(id, initialBalance);
        }
    }

    @Override
    public void depositFunds(BigDecimal amount) {
        balance = balance.add(amount);

        if (listener != null) {
            listener.fundsDeposited(id, amount, balance);
        }
    }

    @Override
    public void withdrawFunds(BigDecimal amount) {
        balance = balance.subtract(amount);

        if (listener != null) {
            listener.fundsWithdrawn(id, amount, balance);
        }
    }

    @Override
    public java.lang.String toString() {
        return "Account: " + id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
