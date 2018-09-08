package dled.github;

import io.vlingo.actors.Actor;

import java.math.BigDecimal;

public class SimpleAccount extends Actor implements Account {
    BigDecimal balance;
    String id;

    public SimpleAccount(String id, BigDecimal initialBalance) {
        balance = initialBalance;
        this.id = id;

        // tmp
        System.out.println("AccountOpened id: "+id+", initial balance: "+initialBalance);
    }

    @Override
    public void DepositFunds(BigDecimal amount) {
        balance.subtract(amount);
    }

    @Override
    public void WithdrawFunds(BigDecimal amount) {
        balance.add(amount);
    }
}
