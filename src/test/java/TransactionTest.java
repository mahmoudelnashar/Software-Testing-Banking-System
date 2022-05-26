import com.example.banking_system.services.ObjectFinder;
import com.example.banking_system.services.Transaction;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    @Test
    public void AutoIdTest() throws IOException, URISyntaxException, CsvException {
        Transaction t = new Transaction("Water", 100, "123");
        CSVReader reader = new CSVReader(new FileReader(ObjectFinder.getTransactionPath("123")));
        List<String[]> arr = reader.readAll();
        assertEquals(String.valueOf(arr.size()), t.getId());
    }

}
