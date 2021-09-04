package pers.grace.calculator.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pers.grace.calculator.model.ThreeMedia;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

public class ThreeMediaCalculatorTest {
    private static final String imageCode = "IMG";
    private static final String audioCode = "FLAC";
    private static final String videoCode = "VID";
    private static final String total = "TOTAL";

    @Test
    public void testCalTotal() {
        ThreeMedia threeMedia = new ThreeMedia();
        ThreeMediaCalculator threeMediaCalculator = new ThreeMediaCalculator();
        TreeMap<String, TreeMap<String, BigDecimal>> res = threeMediaCalculator.calTotal(threeMedia, 10,
                15, 13);

        TreeMap<String, TreeMap<String, BigDecimal>> expected = new TreeMap<>();
        TreeMap<String, BigDecimal> imgTable = new TreeMap<>();
        imgTable.put("5", new BigDecimal("0"));
        imgTable.put("10", new BigDecimal("1"));
        imgTable.put(total, new BigDecimal("800"));
        expected.put(imageCode, imgTable);
        TreeMap<String, BigDecimal> audioTable = new TreeMap<>();
        audioTable.put("3", new BigDecimal("0"));
        audioTable.put("6", new BigDecimal("1"));
        audioTable.put("9", new BigDecimal("1"));
        audioTable.put(total, new BigDecimal("1957.5"));
        expected.put(audioCode, audioTable);
        TreeMap<String, BigDecimal> vidTable = new TreeMap<>();
        vidTable.put("3", new BigDecimal("1"));
        vidTable.put("5", new BigDecimal("2"));
        vidTable.put("9", new BigDecimal("0"));
        vidTable.put(total, new BigDecimal("2370"));
        expected.put(videoCode, vidTable);

        Assertions.assertArrayEquals(expected.toString().getBytes(StandardCharsets.UTF_8),
                res.toString().getBytes(StandardCharsets.UTF_8));

    }
}
