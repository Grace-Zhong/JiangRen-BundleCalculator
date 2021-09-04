package pers.grace.calculator;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Output {
    public void outputToFile(int inImg, int inFlac, int inVid,
                               HashMap<String, TreeMap<Integer, BigDecimal>> data,
                               TreeMap<String, TreeMap<String, BigDecimal>> res) {
        try {
            FileOutputStream recordInFile = new FileOutputStream("./output.txt");

            final String total = "TOTAL";
            String output = "";

            // add printing output timestamp
            SimpleDateFormat time = new SimpleDateFormat();
            time.applyPattern("MM-dd-yyyy HH:mm:ss a");
            Date date = new Date();
            output += time.format(date) + "\n";

            for (Map.Entry<String, TreeMap<String, BigDecimal>> entry:res.entrySet()) {
                output += String.valueOf(inImg) + " " + entry.getKey()
                        + " $" + res.get(entry.getKey()).get(total) + "\n";
                for (Map.Entry<String, BigDecimal> innerEntry:res.get(entry.getKey()).entrySet()) {
                    if (!innerEntry.getKey().equals(res.get(entry.getKey()).lastKey())
                            && innerEntry.getValue().compareTo(new BigDecimal(0)) != 0) {
                        BigDecimal cost = innerEntry.getValue()
                                .multiply(data.get(entry.getKey()).get(Integer.valueOf(innerEntry.getKey())));

                        output += "  " + innerEntry.getValue() + " x " + innerEntry.getKey()
                                + " $" + cost + "\n";
                    }
                }
            }

            recordInFile.write(output.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
