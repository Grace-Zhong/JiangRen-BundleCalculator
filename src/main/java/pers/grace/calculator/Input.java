package pers.grace.calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    final static String IMAGE_CODE = "IMG";
    final static String AUDIO_CODE = "FLAC";
    final static String VIDEO_CODE = "VID";

    public int[] input() {
        System.out.println("Please type your request here...");
        ArrayList<String> inputList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String inputImg = sc.nextLine();
        String inputFlac = sc.nextLine();
        String inputVid = sc.nextLine();

        inputList.add(inputImg);
        inputList.add(inputFlac);
        inputList.add(inputVid);

        if (!checkInput(inputList)) {
            System.out.println("Invalid Input.");
            return null;
        }

        int inputImgNum = getInputNum(inputList.get(0));
        int inputFlacNum = getInputNum(inputList.get(1));
        int inputVidNum = getInputNum(inputList.get(2));
        int[] res = {inputImgNum, inputFlacNum, inputVidNum};
        return res;
    }

    public static int getInputNum(String string) {
        int res = 0;
        String[] arr = string.split("\\s+");
        return Integer.valueOf(arr[0]);
    }

    public static boolean checkInput(ArrayList<String> inputList) {
        if (inputList.size() != 3) {
            return false;
        }
        boolean res = true;
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).isEmpty()) {
                return false;
            }

            // check number
            int spaceIndex = -1;
            for (int j = 0; j < inputList.get(i).length(); j++) {
                if (inputList.get(i).charAt(j) >= 48 && inputList.get(i).charAt(j) <= 57) {
                    continue;
                }
                else {
                    spaceIndex = j;
                    break;
                }
            }

            // check space
            if (inputList.get(i).charAt(spaceIndex) != ' ') {
                return false;
            }

            // check media code name
            if (spaceIndex == inputList.get(i).length() - 1) {
                return false;
            }
            else {
                String mediaName = inputList.get(i).substring(spaceIndex + 1);
                if ((i == 0 && mediaName.compareTo(IMAGE_CODE) != 0)
                        || (i == 1 && mediaName.compareTo(AUDIO_CODE) != 0)
                        || (i == 2 && mediaName.compareTo(VIDEO_CODE) != 0)) {
                    return false;
                }
            }

        }
        return res;
    }
}
