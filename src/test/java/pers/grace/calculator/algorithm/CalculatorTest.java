package pers.grace.calculator.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pers.grace.calculator.algorithm.Calculator;
import pers.grace.calculator.utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class CalculatorTest {
    @Test
    void calculateImgTest() {
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

    @Test
    void calculateAudioTest() {
        Calculator calculator = new Calculator();
        String mediaCode = Constants.AUDIO_CODE;
        HashMap<Integer, Integer> actual = calculator.calculate(15, Constants.infoTable.get(mediaCode).keySet());
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(3, 0);
        expected.put(9, 1);
        expected.put(6, 1);
        Assertions.assertArrayEquals(
                expected.toString().getBytes(StandardCharsets.UTF_8),
                actual.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void calculateVideoTest() {
        Calculator calculator = new Calculator();
        String mediaCode = Constants.VIDEO_CODE;
        HashMap<Integer, Integer> actual = calculator.calculate(13, Constants.infoTable.get(mediaCode).keySet());
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(3, 1);
        expected.put(5, 2);
        expected.put(9, 0);
        Assertions.assertArrayEquals(
                expected.toString().getBytes(StandardCharsets.UTF_8),
                actual.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void calculateSmallOrderTest() {
        Calculator calculator = new Calculator();
        String mediaCode = Constants.IMAGE_CODE;
        HashMap<Integer, Integer> actual = calculator.calculate(2, Constants.infoTable.get(mediaCode).keySet());
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(5, 1);
        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertArrayEquals(
                expected.toString().getBytes(StandardCharsets.UTF_8),
                actual.toString().getBytes(StandardCharsets.UTF_8));
    }
}
