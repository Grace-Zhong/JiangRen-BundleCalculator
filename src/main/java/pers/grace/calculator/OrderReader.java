package pers.grace.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import pers.grace.calculator.utils.Constants;

@Slf4j
public class OrderReader {

    public HashMap<Integer, String> read() {
        ArrayList<String> inputList = new ArrayList<>();

        System.out.println("Please type your order here...");
        System.out.println("Each item contains an positive integer and a media code separated by a space.");
        try (Scanner scanner = new Scanner(System.in)) {
            String nextLine = scanner.nextLine();
            while (nextLine != null && !nextLine.equals("")) {
                inputList.add(nextLine);
                nextLine = scanner.nextLine();
            }
        } catch (Exception e) {
            log.info("Reading input error");
        }

        return check(inputList);
    }

    public HashMap<Integer, String> check(ArrayList<String> input) {
        HashMap<Integer, String> inputTable = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            String [] arr = input.get(i).split("\\s+");
            if (arr.length == 2 && StringUtils.isNumeric(arr[0]) && Constants.infoTable.containsKey(arr[1])) {
                inputTable.put(Integer.valueOf(arr[0]), arr[1]);
            }
            else{
                log.info("Line " + i + " input Error");
            }
        }
        return inputTable;
    }

}
