package pers.grace.calculator.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pers.grace.calculator.algorithm.Calculator;
import pers.grace.calculator.utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class CalculatorTest {
    @Test
    void calculateTest() {
        Calculator calculator = new Calculator();
        String mediaCode = Constants.VIDEO_CODE;
        HashMap<Integer, Integer> actual = calculator.calculate(13, Constants.infoTable.get(mediaCode).keySet());
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(5, 2);
        expected.put(3, 1);
        expected.put(9, 0);
        Assertions.assertArrayEquals(
                expected.toString().getBytes(StandardCharsets.UTF_8),
                actual.toString().getBytes(StandardCharsets.UTF_8));
    }
}
