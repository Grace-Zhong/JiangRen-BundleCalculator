package pers.grace.calculator.algorithm;

import pers.grace.calculator.utils.Constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceCalculator {
    public static HashMap<Integer, BigDecimal> calculate(HashMap<Integer, Integer> res, String mediaCode) {
        HashMap<Integer, BigDecimal> priceMap = new HashMap<>();
        Map<Integer, BigDecimal> infoTable = Constants.infoTable.get(mediaCode);
        for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
            priceMap.put(entry.getKey(), infoTable.get(entry.getKey()).multiply(new BigDecimal(res.get(entry.getKey()))));
        }
        return priceMap;
    }
}
