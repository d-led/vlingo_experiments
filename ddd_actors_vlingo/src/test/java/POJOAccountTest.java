import dled.github.SimpleAccount;
import io.vlingo.actors.testkit.TestWorld;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class POJOAccountTest {

    private TestWorld world;

    @Test
    public void testAccountBalance() {
        SimpleAccount account = new SimpleAccount("a", new BigDecimal(10), null);
        assertEquals(new BigDecimal(10), account.getBalance());
        account.withdrawFunds(new BigDecimal(1));
        assertEquals(new BigDecimal(9), account.getBalance());
        account.depositFunds(new BigDecimal(2));
        assertEquals(new BigDecimal(11), account.getBalance());
    }

    @Before
    public void setUp() {
        world = TestWorld.start("test-world");
    }

    @After
    public void tearDown() {
        world.terminate();
    }
}
