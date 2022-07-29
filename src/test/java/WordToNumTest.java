import org.example.converter.WordToNum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class WordToNumTest {
    private WordToNum wordToNum;

    @Before
    public void init() {
        this.wordToNum = new WordToNum();
    }

    @Test
    public void badInputTestForInt() {
        String charString = "qwerty";
        String symbolString = "!@#$%^&%**(";
        String emptyString = " ";
        String doubleString = "12.3";

        try {
            wordToNum.convertToInt(charString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }

        try {
            wordToNum.convertToInt(symbolString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }

        try {
            wordToNum.convertToInt(emptyString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }

        try {
            wordToNum.convertToInt(doubleString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }
    }

    @Test
    public void badInputTestForDouble() {
        String charString = "qwerty";
        String symbolString = "!@#$%^&%**(";
        String emptyString = " ";
        String intString = "123";

        try {
            wordToNum.convertToDouble(charString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }

        try {
            wordToNum.convertToDouble(symbolString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }

        try {
            wordToNum.convertToDouble(emptyString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }

        try {
            wordToNum.convertToDouble(intString);
        } catch (NumberFormatException thrown) {
            Assert.assertEquals("Bad string!", thrown.getMessage());
        }
    }

    @Test
    public void overflowTest() {
        String overflowInt = "9999999999999999999999";
        try {
            wordToNum.convertToInt(overflowInt);
        } catch (ArithmeticException thrown) {
            Assert.assertEquals("Overflow occurred", thrown.getMessage());
        }
    }

    @Test
    public void intTest() {
        int[] numberPositive = IntStream.rangeClosed(1, 15_000_000).toArray();
        int[] numberNegative = IntStream.rangeClosed(-15_000_000, -1).toArray();

        for (int j : numberPositive) {
            int result = wordToNum.convertToInt(String.valueOf(j));
            Assert.assertEquals(result, j);
        }

        for (int j : numberNegative) {
            int result = wordToNum.convertToInt(String.valueOf(j));
            Assert.assertEquals(result, j);
        }
    }

    @Test
    public void doubleTest() {
        String[] numbers = {"2323.211212", "12.23233", "-6555.5555", "212.233", "323.333", "-66.6666", "134.88",
                "228.222", "323.2323", "-1323.23", "1233.233", "1323.23", "-15423.2354", "12354.23233", "132323.23"};

        for(String number : numbers) {
            double result = wordToNum.convertToDouble(number);
            String doubleResult = String.valueOf(result);
            Assert.assertEquals(number, doubleResult);
        }
    }

}