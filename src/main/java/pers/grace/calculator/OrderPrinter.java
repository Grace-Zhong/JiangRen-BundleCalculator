package pers.grace.calculator;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class OrderPrinter {
    public void print(String mediaCode, HashMap<Integer, Integer> bundleRes, HashMap<Integer, BigDecimal> priceRes,
                      BigDecimal totalPrice) {
        try {
            FileOutputStream recordInFile = new FileOutputStream("./output.txt");
            String header = bundleRes.values().stream().reduce(0, (a, b) -> a + b).toString()
                    + " " + mediaCode + " $" + totalPrice.toString() + "\n";
            recordInFile.write(header.getBytes(StandardCharsets.UTF_8));

            for(Integer bundle : bundleRes.keySet()) {
                String item = "  " + bundle.toString() + " x "
                        + bundleRes.get(bundle).toString() + " " + priceRes.get(bundle) + "\n";
                recordInFile.write(item.getBytes(StandardCharsets.UTF_8));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
