package pers.grace.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pers.grace.calculator.model.Media;
import pers.grace.calculator.algorithm.ThreeMediaCalculator;

import java.math.BigDecimal;
import java.util.TreeMap;

public class ThreeMediaCalculatorTest {
        private static final String imageCode = "IMG";
        private static final String audioCode = "FLAC";
        private static final String videoCode = "VID";

        private static TreeMap<Integer, BigDecimal> imageTable = new TreeMap<>();
        private static TreeMap<Integer, BigDecimal> audioTable = new TreeMap<>();
        private static TreeMap<Integer, BigDecimal> videoTable = new TreeMap<>();

        @BeforeEach
        void init() {
            imageTable.put(5, new BigDecimal(450));
            imageTable.put(10, new BigDecimal(800));

            audioTable.put(3, new BigDecimal(427.5));
            audioTable.put(6, new BigDecimal(810));
            audioTable.put(9, new BigDecimal(1147.5));

            videoTable.put(3, new BigDecimal(570));
            videoTable.put(5, new BigDecimal(900));
            videoTable.put(9, new BigDecimal(1530));
        }

        @Test
        public void testCalTotal() {
            ThreeMediaCalculator calculator = new ThreeMediaCalculator(
                    new Media(imageCode, imageTable),
                    new Media(audioCode, audioTable),
                    new Media(videoCode, videoTable)
            );
            int inputImg = 10;
            int inputFlac = 15;
            int inputVid = 13;

            calculator.calTotal(inputImg, inputFlac, inputVid);
        }

}
