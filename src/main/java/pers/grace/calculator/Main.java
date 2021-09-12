package pers.grace.calculator;

import pers.grace.calculator.algorithm.Calculator;
import pers.grace.calculator.algorithm.PriceCalculator;
import pers.grace.calculator.utils.Constants;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // read order
        OrderReader test = new OrderReader();
        IdentityHashMap<Integer, String> order = test.readOrder();

        // process order
        for (Map.Entry<Integer, String> entry : order.entrySet()) {
            // get mediaCode and corresponding infoTable
            String mediaCode = entry.getValue();
            Set<Integer> bundleSet = Constants.infoTable.get(mediaCode).keySet();

            // calculate bundle
            Calculator bundleCalculator = new Calculator();
            HashMap<Integer, Integer> bundleRes = bundleCalculator.calculate(entry.getKey(), bundleSet);
            System.out.println(bundleRes.toString());

            // calculate price
            PriceCalculator priceCalculator = new PriceCalculator();
            HashMap<Integer, BigDecimal> priceRes = priceCalculator.calculate(bundleRes, mediaCode);
            // total price is the sum of priceRes.valueSet()
            BigDecimal totalPrice = priceRes.values().stream()
                    .reduce(new BigDecimal("0"), (first, second) -> first.add(second));

            // print
            OrderPrinter printer = new OrderPrinter();
            printer.print(mediaCode, bundleRes, priceRes, totalPrice);
        }

    }
}
