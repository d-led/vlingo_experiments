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
    public void testOpeningAccount() throws InterruptedException{
        TestUntil test = TestUntil.happenings(1);
        TestActor<AccountListener> l = testListenerForHappenings(test);

        final TestActor<Account> a = testAccount(new BigDecimal(10), l.actor());

        a.actor().depositFunds(new BigDecimal(1));

        l.actor().lastBalanceSeen().andThen(b -> {
            assertEquals(new BigDecimal(11), b);
            return b;
        });

        assertTrue("account should have been created", test.completesWithin(1000));
    }

    @Test
    public void testUsingTheAccount() throws InterruptedException {
        TestUntil test = TestUntil.happenings(3);
        TestActor<AccountListener> l = testListenerForHappenings(test);

        final TestActor<Account> a = testAccount(new BigDecimal(10), l.actor());
        a.actor().depositFunds(new BigDecimal(1));
        a.actor().withdrawFunds(new BigDecimal(2));

        l.actor().lastBalanceSeen().andThen(b -> {
            assertEquals(new BigDecimal(9), b);
            return b;
        });

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
