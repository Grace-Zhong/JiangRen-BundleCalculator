package pers.grace.calculator;

import org.junit.jupiter.api.Test;
import pers.grace.calculator.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderPrinterTest {

    @Test
    void printTest() {
        ArrayList<String> input = new ArrayList<>();
        input.add("10 " + Constants.IMAGE_CODE);
        OrderReader orderReader = new OrderReader();
        HashMap<Integer, String> order = orderReader.check(input);
        ArrayList<String> mediaCodeList = new ArrayList<>();
        ArrayList<HashMap<Integer, Integer>> bundleResList = new ArrayList<>();
        ArrayList<HashMap<Integer, BigDecimal>> priceResList = new ArrayList<>();
        ArrayList<BigDecimal> totalPriceList = new ArrayList<>();

        mediaCodeList.add(Constants.IMAGE_CODE);
        HashMap<Integer, Integer> bundle = new HashMap<>();
        bundle.put(5, 0);
        bundle.put(10, 1);
        bundleResList.add(bundle);
        HashMap<Integer, BigDecimal> price = new HashMap<>();
        price.put(5, new BigDecimal("0"));
        price.put(10, new BigDecimal("800"));
        priceResList.add(price);
        totalPriceList.add(new BigDecimal("800"));

        OrderPrinter orderPrinter = new OrderPrinter();
        orderPrinter.print(order, mediaCodeList, bundleResList, priceResList, totalPriceList);
    }
}
