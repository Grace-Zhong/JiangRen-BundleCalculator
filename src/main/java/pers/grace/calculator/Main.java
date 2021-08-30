package pers.grace.calculator;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.TreeMap;

public class Main {
    final static String IMAGE_CODE = "IMG";
    final static String AUDIO_CODE = "FLAC";
    final static String VIDEO_CODE = "VID";

    public static void main(String[] args) {
        // read input
        Input in = new Input();
        int[] input = in.input();

        if (input != null) {
            // read information table
            ThreeMediaCalculator calculator = setInfoTable();

            // calculate
            calculator.calTotal(input[0], input[1], input[2]);
        }
    }

    public static ThreeMediaCalculator setInfoTable() {

        TreeMap<Integer, BigDecimal> imageTable = new TreeMap<>();
        TreeMap<Integer, BigDecimal> audioTable = new TreeMap<>();
        TreeMap<Integer, BigDecimal> videoTable = new TreeMap<>();

        imageTable.put(5, new BigDecimal("450"));
        imageTable.put(10, new BigDecimal("800"));

        audioTable.put(3, new BigDecimal("427.5"));
        audioTable.put(6, new BigDecimal("810"));
        audioTable.put(9, new BigDecimal("1147.5"));

        videoTable.put(3, new BigDecimal("570"));
        videoTable.put(5, new BigDecimal("900"));
        videoTable.put(9, new BigDecimal("1530"));

        ThreeMediaCalculator calculator = new ThreeMediaCalculator(
                new Media(IMAGE_CODE, imageTable),
                new Media(AUDIO_CODE, audioTable),
                new Media(VIDEO_CODE, videoTable)
        );
        return calculator;
    }
}
