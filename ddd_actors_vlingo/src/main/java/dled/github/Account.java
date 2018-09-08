package dled.github;

import java.math.BigDecimal;

public interface Account {
    void depositFunds(BigDecimal amount);
    void withdrawFunds(BigDecimal amount);
}
