package Integration_test_suite;

import com.example.banking_system.services.Client;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void toStringTest() throws FileNotFoundException {
        Client c = new Client("123", "omar", "iksdbaod dsgfho", "01883883132", "omar1111", "pass123", "osn2211@eng.com");
        assertEquals("123,omar,osn2211@eng.com,omar1111,01883883132,null,iksdbaod dsgfho,null,0,pass123,123,0,null\n", c.toString());
    }
    @Test
    public void setAutoAccTest() throws FileNotFoundException {
        Client c = new Client("123", "omar", "iksdbaod dsgfho", "01883883132", "omar1111", "pass123", "osn2211@eng.com");
        assertEquals(0, c.getAccount().getBalance());
        assertEquals("123", c.getAccount().getId());

    }
    @Test
    public void NullexceptionTest() throws FileNotFoundException {
        assertThrows(NullPointerException.class, () -> new Client("123", "", "iksdbaod dsgfho", "01883883132", "omar1111", "pass123", "osn2211@eng.com"));
    }

    public static void main(String[] args) {

    }
}
