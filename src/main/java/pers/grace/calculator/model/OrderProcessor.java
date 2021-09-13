package pers.grace.calculator.model;

import lombok.extern.slf4j.Slf4j;
import pers.grace.calculator.algorithm.Calculator;
import pers.grace.calculator.algorithm.PriceCalculator;
import pers.grace.calculator.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class OrderProcessor {
    public final ArrayList<String> mediaCodeList = new ArrayList<>();
    public final ArrayList<HashMap<Integer, Integer>> bundleResList = new ArrayList<>();
    public final ArrayList<HashMap<Integer, BigDecimal>> priceResList = new ArrayList<>();
    public final ArrayList<BigDecimal> totalPriceList = new ArrayList<>();

    public void process(Order order) {
        for (Item item : order.orderList) {
            // get mediaCode and corresponding infoTable
            String mediaCode = item.mediaCode;
            mediaCodeList.add(mediaCode);
            Set<Integer> bundleSet = Constants.infoTable.get(mediaCode).keySet();

            // calculate bundle
            Calculator bundleCalculator = new Calculator();
            HashMap<Integer, Integer> bundleRes = bundleCalculator.calculate(item.bundleNum, bundleSet);
            bundleResList.add(bundleRes);
//            log.info(bundleRes.toString());

            // calculate price
            PriceCalculator priceCalculator = new PriceCalculator();
            HashMap<Integer, BigDecimal> priceRes = priceCalculator.calculate(bundleRes, mediaCode);
            priceResList.add(priceRes);

            BigDecimal totalPrice = priceRes.values().stream().reduce(new BigDecimal("0"), BigDecimal::add);
            totalPriceList.add(totalPrice);
        }
    }

}
