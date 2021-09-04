package pers.grace.calculator.algorithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pers.grace.calculator.model.Media;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

public class MediaCalculatorTest {
    private static final String imageCode = "IMG";
    private static final String audioCode = "FLAC";
    private static final String videoCode = "VID";
    private static final String total = "TOTAL";


    @Test
    @DisplayName("Calculate a single media")
    void calSingleTypeAssertion() {
        // create a media
        TreeMap<Integer, BigDecimal> mediaTable = new TreeMap<>();
        mediaTable.put(5, new BigDecimal(450));
        mediaTable.put(10, new BigDecimal(800));
        Media media = new Media(imageCode, mediaTable);

        MediaCalculator mediaCalculator = new MediaCalculator();
        TreeMap<String, BigDecimal> calResult = mediaCalculator.calSingleMedia(media, 10);

        TreeMap<String, BigDecimal> expected = new TreeMap<>();
        expected.put("10", new BigDecimal("1"));
        expected.put("5", new BigDecimal("0"));
        expected.put(total, new BigDecimal("800"));

        Assertions.assertArrayEquals(expected.toString().getBytes(StandardCharsets.UTF_8),
                calResult.toString().getBytes(StandardCharsets.UTF_8));
    }
}
