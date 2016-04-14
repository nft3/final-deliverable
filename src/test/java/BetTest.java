import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 * @Author Nicholas Treu
 */
public class BetTest {

    Bet bet;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUP(){
        bet = new Bet();
    }

    @Test
    public void testSetBetPositive() {
        double positiveBet = 1;
        try{
            bet.setBet(positiveBet);
        } catch(IllegalArgumentException e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSetBetZero(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("You cannot enter a negative or 0 dollar bet!");

        double zeroBet = 0;
        bet.setBet(zeroBet);
    }

    @Test
    public void testSetBetNegative(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("You cannot enter a negative or 0 dollar bet!");

        double negativeBet = -1;
        bet.setBet(negativeBet);
    }

    @Test
    public void getPositiveBet1(){
        double positiveBet = 67.45;
        try{
            bet.setBet(positiveBet);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            fail();
        }

        assertEquals(positiveBet, bet.getBet(), 0);
    }

    @Test
    public void getPositiveBet2(){
        double positiveBet = 143;
        try{
            bet.setBet(positiveBet);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            fail();
        }

        assertEquals(positiveBet, bet.getBet(), 0);
    }

    @Test
    public void getPositiveBet3(){
        double positiveBet = 187.03;
        try{
            bet.setBet(positiveBet);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            fail();
        }

        assertEquals(positiveBet, bet.getBet(), 0);
    }

    @Test
    public void testClearBet(){
        double tempBet = 435;
        bet.setBet(tempBet);

        double gotBet = bet.getBet();
        bet.clearBet();

        assertNotEquals(bet.getBet(), gotBet);
    }
}