package pers.grace.calculator;

import pers.grace.calculator.model.Media;
import pers.grace.calculator.algorithm.ThreeMediaCalculator;
import pers.grace.calculator.model.ThreeMedia;

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
            ThreeMedia threeMedia = new ThreeMedia();

            // calculate
            ThreeMediaCalculator threeMediaCalculator = new ThreeMediaCalculator();
            TreeMap<String, TreeMap<String, BigDecimal>> res = threeMediaCalculator.calTotal(threeMedia, input[0],
                    input[1], input[2]);

            // print result to file
            Output print = new Output();
            print.outputToFile(input[0], input[1], input[2],threeMedia.data() ,res);
        }
    }

}
