import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests not running in intended order, please see line 33 for relevant info

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountTest {

    BankAccount account1;
    BankAccount account2;


    @BeforeAll
    void setUp(){
        account1 = new BankAccount(1, 500);
        account2 = new BankAccount(2, 500);
    }

    @Test
    @Order(1)
    void testBalance(){
        assertAll(
                () -> assertEquals(500, account1.getBalance()),
                () -> assertEquals(500, account2.getBalance())

        );
    }
    //Tests are not running in intended order.
    //intent was for the transfer to occur in this block, however for some reason testHistory is running first
    //as such, I have left the transfer in the history statement.
    @Test
    @Order(2)
    void testTransfer(){
        assertAll(
                () -> assertEquals(200,account1.getBalance()),
                () -> assertEquals(800, account2.getBalance())
        );
    }

    @Test
    @Order(3)
    void testHistory(){
        account1.transferTo(account2, 300);
        String expected1 = "You paid $300.0 to account number: 2.";
        String expected2 = "You were paid $300.0 by account number: 1.";
        assertAll(
                () -> assertEquals(expected1, account1.getHistory()),
                () -> assertEquals(expected2, account2.getHistory())
        );
    }
}
