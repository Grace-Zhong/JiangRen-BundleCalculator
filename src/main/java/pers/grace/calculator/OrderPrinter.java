package pers.grace.calculator;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class OrderPrinter {

    public void print(HashMap<Integer, String> order, ArrayList<String> mediaCodeList, ArrayList<HashMap<Integer,
            Integer>> bundleResList,
                      ArrayList<HashMap<Integer, BigDecimal>> priceResList,  ArrayList<BigDecimal> totalPriceList) {
        Object[] orderArray = order.keySet().toArray();
        try{
            FileOutputStream recordInFile = new FileOutputStream("./output.txt");
            for (int i = 0; i < mediaCodeList.size(); i++) {
                String header = orderArray[i].toString()
                        + " " + mediaCodeList.get(i) + " $" + totalPriceList.get(i).toString() + "\n";
                recordInFile.write(header.getBytes(StandardCharsets.UTF_8));
                for (Integer bundle : bundleResList.get(i).keySet()) {
                    String item = "  " + bundle.toString() + " x "
                            + bundleResList.get(i).get(bundle).toString() + " " + priceResList.get(i).get(bundle) +
                            "\n";
                    recordInFile.write(item.getBytes(StandardCharsets.UTF_8));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
