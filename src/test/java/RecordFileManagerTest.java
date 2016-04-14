import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

/**
 * @Author Nicholas Treu
 */
public class RecordFileManagerTest {

    private RecordFileManager test;
    private static String originalContentsStr = "FuzzyBunny password 400.23\n" +
            "CatLuver<3 CATS4LYFE 200\n" +
            "PensFanFTW PITTSBURGH412 1013.65\n" +
            "BLACKJACKGOD IAMTHEGREATEST 5000.00\n";

    @Before
    // To set up every time,  add some stuff to a file so that we can use it to test the methods.
    public void setUp(){
       test = new RecordFileManager("BlackjackRecords.txt");
    }

    @After
    // Delete the file we meddled with during these test and rebuild the original version
    public void tearDown(){
        test.deleteFile();
        File file = new File("BlackjackRecords.txt");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(originalContentsStr);
            writer.close();

        } catch(IOException e){
            e.printStackTrace();
        }

        System.gc();
    }

    @Test
    public void testGetAmountInAccount() {
        double amount = test.getAmountInAccount("CatLuver<3", "CATS4LYFE");
        assertEquals(amount, 200, 0);
    }

    @Test
    public void testGetAmountInAccount2(){
        double amount = test.getAmountInAccount("BLACKJACKGOD", "IAMTHEGREATEST");
        assertEquals(amount, 5000, 0);
    }

    @Test
    public void testDoesUserExistYes() {
        assertTrue(test.doesUserExist("PensFanFTW"));
    }

    @Test
    public void testDoesUserExistNo(){
        assertFalse(test.doesUserExist("ILOSTALLMYMONEY!!"));
    }

    @Test
    public void testAddUserToFile() {
        test.addUserToFile("RiversCasinoBoss", "Get@MEBRO", 532.23);
        assertTrue(test.doesUserExist("RiversCasinoBoss"));
    }

    @Test
    public void testUpdateAmountInAccount() {
        test.addUserToFile("RiversCasinoBoss", "Get@MEBRO", 532.23);
        test.updateAmountInAccount("RiversCasinoBoss", 976.98);
        assertEquals(test.getAmountInAccount("RiversCasinoBoss", "Get@MEBRO"), 976.98, 0);
    }
}