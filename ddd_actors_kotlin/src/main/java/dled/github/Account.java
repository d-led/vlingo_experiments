package dled.github;

import java.math.BigDecimal;

public interface Account {
    void DepositFunds(BigDecimal amount);
    void WithdrawFunds(BigDecimal amount);
}
