import dled.github.Account;
import dled.github.AccountListener;
import dled.github.SimpleAccount;
import io.vlingo.actors.Definition;
import io.vlingo.actors.testkit.TestActor;
import io.vlingo.actors.testkit.TestUntil;
import io.vlingo.actors.testkit.TestWorld;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ActorAccountTest {
    protected TestWorld world;

    public TestUntil until;

    BigDecimal balanceSeen;

    @Test
    public void testOpeningAccount() {
        TestUntil test = TestUntil.happenings(1);
        TestActor<AccountListener> l = testListenerForHappenings(test);

        TestActor<Account> a = testAccount(new BigDecimal(10), l.actor());

        l.actor().lastBalanceSeen().andThen((b) -> {
            setBalance(b);
            return b;
        });

        assertEquals(new BigDecimal(11), balanceSeen);
        assertTrue("account should have been created", test.completesWithin(1000));
    }

    @Test
    public void testUsingTheAccount() {
        TestUntil test = TestUntil.happenings(3);
        BigDecimal balance = new BigDecimal(0);
        TestActor<AccountListener> l = testListenerForHappenings(test);

        TestActor<Account> a = testAccount(new BigDecimal(10), l.actor());
        a.actor().depositFunds(new BigDecimal(1));
        a.actor().withdrawFunds(new BigDecimal(2));

        l.actor().lastBalanceSeen().andThen((b) -> {
            setBalance(b);
            return b;
        });

        assertEquals(new BigDecimal(9), balanceSeen);
        assertTrue("account should have been created", test.completesWithin(1000));
    }

    @Before
    public void setUp() {
        world = TestWorld.start("test-world");
    }

    @After
    public void tearDown() {
        world.terminate();
    }

    TestActor<Account> testAccount(BigDecimal initialBalance, AccountListener listener) {
        return world.actorFor(
                Definition.has(SimpleAccount.class, Definition.parameters("_", initialBalance, listener)),
                Account.class
        );
    }

    TestActor<AccountListener> testListenerForHappenings(TestUntil happenings) {
        return world.actorFor(
                Definition.has(TestAccountListener.class, Definition.parameters(happenings)),
                AccountListener.class
        );
    }

    void setBalance(BigDecimal balance) {
        balanceSeen = balance;
    }
}
