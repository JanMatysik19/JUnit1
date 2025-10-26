import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Calculator;

import java.util.ArrayList;
import java.util.List;


public class CalculatorTest {
    Calculator calculator;
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    @AfterEach
    void tearDown() {
        calculator = null;
    }



    @Test
    public void sumTest() {
        var out = calculator.sum(1, 2);
        float exp = 3.0f;

        Assertions.assertEquals(exp, out, "Sum result check.");
    }

    @Test
    public void subtractTest() {
        var out = calculator.subtract(10, 2);
        float exp = 8.0f;

        Assertions.assertEquals(exp, out, "Subtraction result check.");
    }

    @Test
    public void multiplyTest() {
        var out = calculator.multiply(3, 11);
        float exp = 33.0f;

        Assertions.assertEquals(exp, out,  "Multiplication result check.");
    }

    @Test
    public void divideTest() {
        var out = calculator.divide(100, 25);
        float exp = 4.0f;

        Assertions.assertEquals(exp, out, "Division result check.");
    }

    @Test
    public void appendToHistoryTest() throws NoSuchFieldException, IllegalAccessException {
        var historyField = Calculator.class.getDeclaredField("history");
        historyField.setAccessible(true);

        var resultField = Calculator.class.getDeclaredField("result");
        resultField.setAccessible(true);


        // Triggers, Settings
        resultField.set(calculator, 2.0f);
        historyField.set(calculator, new ArrayList<>());

        calculator.appendToHistory();

        var history = (List<Float>) historyField.get(calculator);

        // Tests
        var lengthOut = history.size();
        var lengthExp = 1;
        Assertions.assertEquals(lengthExp, lengthOut, "History array size check.");

        var elemOut = history.getFirst();
        var elemExp = 2.0f;
        Assertions.assertEquals(elemExp, elemOut, "History array contents check.");
    }

    @Test
    public void displayHistoryTest() throws NoSuchFieldException, IllegalAccessException {
        var historyField = Calculator.class.getDeclaredField("history");
        historyField.setAccessible(true);

        var arrTmp = new ArrayList<Float>();
        arrTmp.add(2.0f);
        arrTmp.add(1.0f);
        arrTmp.add(3.1f);

        historyField.set(calculator, arrTmp);

        var out = calculator.displayHistory();
        var exp = "2.0 1.0 3.1";

        Assertions.assertEquals(exp, out, "History display contents check.");
    }
}