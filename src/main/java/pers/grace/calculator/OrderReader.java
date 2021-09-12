package pers.grace.calculator;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import pers.grace.calculator.utils.Constants;

public class OrderReader {

    public IdentityHashMap<Integer, String> readOrder() {
        ArrayList<String> inputList = new ArrayList<>();

        System.out.println("Please type your order here...");
        System.out.println("Each item contains an positive integer and a media code separated by a space.");
        Scanner scanner = new Scanner(System.in);
        try{
            String nextLine = scanner.nextLine();
            while (nextLine != null && !nextLine.equals("")) {
                inputList.add(nextLine);
                nextLine = scanner.nextLine();
            }
        }
        catch (Exception e) {
            System.out.println("Reading input error");
        }
        finally {
            scanner.close();
        }

        IdentityHashMap<Integer, String> input = checkOrder(inputList);
        return input;
    }

    private IdentityHashMap<Integer, String> checkOrder(ArrayList<String> input) {
        IdentityHashMap<Integer, String> inputTable = new IdentityHashMap<>();
        for (int i = 0; i < input.size(); i++) {
            String [] arr = input.get(i).split("\\s+");
            if (arr.length == 2 && StringUtils.isNumeric(arr[0]) && Constants.infoTable.containsKey(arr[1])) {
                inputTable.put(Integer.valueOf(arr[0]), arr[1]);
            }
            else{
                System.out.println("Line " + i + " input Error");
            }
        }
        return inputTable;
    }

}
