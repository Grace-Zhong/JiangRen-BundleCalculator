package pers.grace.calculator.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pers.grace.calculator.utils.Constants;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PriceCalculatorTest {
    @Test
    void calculateTest() {
        PriceCalculator priceCalculator = new PriceCalculator();
        HashMap<Integer, Integer> res = new HashMap<>();
        String mediaCode = Constants.IMAGE_CODE;
        res.put(5, 0);
        res.put(10, 1);
        HashMap<Integer, BigDecimal> actual = priceCalculator.calculate(res, mediaCode);

        HashMap<Integer, BigDecimal> expected = new HashMap<>();
        expected.put(5, new BigDecimal("0"));
        expected.put(10, new BigDecimal("800"));
        Assertions.assertArrayEquals(
                expected.toString().getBytes(StandardCharsets.UTF_8),
                actual.toString().getBytes(StandardCharsets.UTF_8));
    }
}
