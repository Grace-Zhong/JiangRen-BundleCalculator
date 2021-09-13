package pers.grace.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pers.grace.calculator.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderReaderTest {
    @Test
    void checkTest() {
        ArrayList<String> input = new ArrayList<>();
        input.add("10 " + Constants.IMAGE_CODE);
        input.add("xxx");

        OrderReader orderReader = new OrderReader();
        HashMap<Integer, String> actualOrder = orderReader.check(input);

        HashMap<Integer, String> expectedOrder = new HashMap<>();
        expectedOrder.put(10, Constants.IMAGE_CODE);
        Assertions.assertArrayEquals(actualOrder.entrySet().toArray(), expectedOrder.entrySet().toArray());
    }

}
