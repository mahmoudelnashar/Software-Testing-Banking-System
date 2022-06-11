package Integration_test_suite;

import com.example.banking_system.services.Client;
import com.example.banking_system.services.ObjectFinder;
import com.example.banking_system.services.Transaction;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectFinderTest {

    @Test
    public void initTest() throws IOException, URISyntaxException, CsvException {
        ObjectFinder.init();
        CSVReader reader = new CSVReader(new FileReader(ObjectFinder.getClient_path()));
        List<String []> arr = reader.readAll();
        reader.close();
        assertEquals(arr.size()-1, ObjectFinder.clients.size());
        assertTrue(ObjectFinder.clients.get(0) instanceof Client);
    }

    @Test
    public void getTansactionsTest() throws IOException, URISyntaxException, CsvException {
        ObjectFinder.getTransactions("123");
        CSVReader reader = new CSVReader(new FileReader(ObjectFinder.getTransactionPath("123")));
        List<String[]> arr = reader.readAll();
        assertEquals(arr.size()-1, ObjectFinder.transactions.size());
        assertTrue(ObjectFinder.transactions.get(0) instanceof Transaction);
    }

    @Nested
    public class WritingUpdatingEntriesTests{
        @BeforeEach
        public void before() throws IOException, URISyntaxException {
            FileWriter fw = new FileWriter(Paths.get(ObjectFinder.class.getResource("/com/example/banking_system/database/clients.csv")
                    .toURI()).toFile().getAbsolutePath(), true);
            fw.write("");
            ObjectFinder.init();
        }

        @Test
        public void findClientTest(){
            Client c = ObjectFinder.findClient("user1", "pass1");
            assertNotNull(c);
        }
        @Test
        public void findClientTestNoExist(){
            Client c = ObjectFinder.findClient("wehat", "bisdb");
            assertNull(c);
        }
        @Test
        public void findClientSSNTest(){
            Client c = ObjectFinder.findClient("123");
            assertNotNull(c);
        }
        @Test
        public void findClienSSNEqualsUserNamePass(){
            Client c1 = ObjectFinder.findClient("123");
            Client c2 = ObjectFinder.findClient("user1", "pass1");
            assertEquals(c1.getId(), c2.getId());
        }

        @Test
        public void updateClientTest() throws IOException, CsvException, URISyntaxException {
            Client c = ObjectFinder.clients.get(0);
            c.setTele("011111");
            ObjectFinder.update(c);
            ObjectFinder.init();
            Client c1 = ObjectFinder.clients.get(0);
            assertEquals(c.getTele(), c1.getTele());
        }
        @Test
        public void writeTransactionTest() throws IOException, URISyntaxException, CsvException {
            String source = Paths.get(ObjectFinder.class.getResource("/com/example/banking_system/database").toURI()).toFile().getAbsolutePath();
            String path = source+"/123.csv";
            Transaction t = new Transaction("Water", 200,"123");
            ObjectFinder.writeTransaction(List.of(new Transaction[]{t}), "123", false);
            List<Transaction> trs = ObjectFinder.getTransactions("123");
            assertEquals(t.getType(), trs.get(trs.size()-1).getType());
            assertEquals(t.getAmount(), trs.get(trs.size()-1).getAmount());
            CSVReader reader = new CSVReader(new FileReader(path));
            List<String[]> strings = reader.readAll();
            strings.remove(strings.size()-1);
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(path));
            writer.writeAll(strings);
            writer.close();

        }
        @Test
        public void writeClientTest() throws IOException, CsvException, URISyntaxException {
            Client c = new Client("1234", "user", "adadadad", "837614980", "user2", "pass2", "jdbf@hdoah.com");
            ObjectFinder.writeClient(List.of(new Client[]{c}));
            CSVReader reader = new CSVReader(new FileReader(ObjectFinder.getClient_path()));
            ArrayList<String[]> clis = new ArrayList<>(reader.readAll());
            String[] c1 = clis.get(clis.size() - 1);
            assertEquals(c1[0], c.getId());
            reader.close();
            CSVWriter wr = new CSVWriter(new FileWriter(ObjectFinder.getClient_path()));
            clis.remove(c1);
            wr.writeAll(clis);
            wr.close();
        }


    }


}