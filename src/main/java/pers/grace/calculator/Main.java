package pers.grace.calculator;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Main {
    final static String imageCode = "IMG";
    final static String audioCode = "FLAC";
    final static String videoCode = "VID";

    public static void main(String[] args) {
        File file = new File("./input.txt");
        ArrayList<String> inputList = readFile(file);
        if (checkInputFile(inputList)) {
            int inputImg = getInputNum(inputList.get(0));
            int inputFlac = getInputNum(inputList.get(1));
            int inputVid = getInputNum(inputList.get(2));

            ThreeMediaCalculator calculator = storeInfo();
            calculator.calTotal(inputImg, inputFlac, inputVid);
        }
        else {
            System.out.println("Invalid input。");
        }
    }

    /**
     * Get the first number in a line in the input file
     * @param string
     * @return
     */
    public static int getInputNum(String string) {
        int res = 0;
        String[] arr = string.split("\\s+");
        return Integer.valueOf(arr[0]);
    }

    /**
     * Check inputList is valid or not
     * @param inputList
     * @return
     */
    public static boolean checkInputFile(ArrayList<String> inputList) {
        boolean res = true;
        for (int i = 0; i < inputList.size(); i++) {
            String[] arr = inputList.get(i).split("\\s+");

            // check input number
            Pattern pattern = Pattern.compile("[0-9]*");
            res = pattern.matcher(arr[0]).matches();

            // check input media type
            if (arr[1].compareTo("IMG") != 0 && arr[1].compareTo("FLAC") != 0 && arr[1].compareTo("VID") != 0) {
                res = false;
            }
        }
        return res;
    }

    /***
     * Read the first three lines in file
     * @param file
     * @return first three lines
     */
    public static ArrayList<String> readFile (File file) {
        // read file
        final int inputLength = 3;
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            if (file.isFile() && file.exists())
            {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;
                while ((tempString = reader.readLine()) != null && line <= inputLength) {
                    list.add(tempString);
                    line++;
                }
                reader.close();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot find input file.");
        }
        return list;
    }
    public static ThreeMediaCalculator storeInfo() {

        TreeMap<Integer, BigDecimal> imageTable = new TreeMap<>();
        TreeMap<Integer, BigDecimal> audioTable = new TreeMap<>();
        TreeMap<Integer, BigDecimal> videoTable = new TreeMap<>();

        imageTable.put(5, new BigDecimal(450));
        imageTable.put(10, new BigDecimal(800));

        audioTable.put(3, new BigDecimal(427.5));
        audioTable.put(6, new BigDecimal(810));
        audioTable.put(9, new BigDecimal(1147.5));

        videoTable.put(3, new BigDecimal(570));
        videoTable.put(5, new BigDecimal(900));
        videoTable.put(9, new BigDecimal(1530));

        ThreeMediaCalculator calculator = new ThreeMediaCalculator(
                new Media(imageCode, imageTable),
                new Media(audioCode, audioTable),
                new Media(videoCode, videoTable)
        );
        return calculator;
    }
}
