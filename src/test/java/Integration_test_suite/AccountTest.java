package Integration_test_suite;

import com.example.banking_system.services.Account;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    Account acc;
    @Test
    public void deductfromEmptyBalance(){
        acc = new Account(0, "0");
        assertFalse(acc.decrease_balance(100));
    }
    @Test
    public void deductfrombalance(){
        acc = new Account(100, "0");
        assertTrue(acc.decrease_balance(5));
        assertEquals(95, acc.getBalance());
    }
    @Test
    public void deductMoreThanBalance(){
        acc = new Account(100, "0");
        assertFalse(acc.decrease_balance(1000));
    }
    @Test
    public void deductnegativeamnt(){
        acc = new Account(100, "0");
        assertFalse(acc.decrease_balance(-11));
    }

    @Test
    public void increase_balanceTest(){
        acc = new Account(100, "0");
        assertTrue(acc.increase_balance(10));
        assertEquals(110, acc.getBalance());
    }

    @Test
    public void increaseBalancenegative(){
        acc = new Account(0, "0");
        assertFalse(acc.increase_balance(-11));
    }

}
