package pers.grace.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import pers.grace.calculator.model.Item;
import pers.grace.calculator.model.Order;
import pers.grace.calculator.utils.Constants;

@Slf4j
public class OrderReader {

    public Order read() {
        Order order = new Order();
        System.out.println("Please type your order here...");
        System.out.println("Each item contains an positive integer and a media code separated by a space.");
        try (Scanner scanner = new Scanner(System.in)) {
            String nextLine = scanner.nextLine();
            while (nextLine != null && !nextLine.equals("")) {
                String [] arr = nextLine.split("\\s+");
                if (arr.length == 2 && StringUtils.isNumeric(arr[0]) && Constants.infoTable.containsKey(arr[1])) {
                    Item item = new Item().setBundleNum(Integer.valueOf(arr[0])).setMediaCode(arr[1]);
                    order.add(item);
                }
                nextLine = scanner.nextLine();
            }
        } catch (Exception e) {
            log.info("Reading input error");
            throw e;
        }
        return order;
    }

}
