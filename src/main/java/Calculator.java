import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

public class Calculator {

    public static void calTotal(int inputImgNum, int inputFlacNum, int inputVidNum, HashMap<String, TreeMap<Integer, BigDecimal>> table) {
        System.out.println("------------------");
        System.out.println("IMG");
        TreeMap<Integer, BigDecimal> subTableImg = table.get("IMG");
        CalculateSingleType cal1 = new CalculateSingleType();
        TreeMap<String, BigDecimal> resImg = cal1.calSingleType(inputImgNum, subTableImg);
        for (Map.Entry<String, BigDecimal> entry:resImg.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("FLAC");
        TreeMap<Integer, BigDecimal> subTableFlac = table.get("FLAC");
        CalculateSingleType cal2 = new CalculateSingleType();
        TreeMap<String, BigDecimal> resFlac = cal2.calSingleType(inputFlacNum, subTableFlac);
        for (Map.Entry<String, BigDecimal> entry:resFlac.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("VID");
        TreeMap<Integer, BigDecimal> subTableVid = table.get("VID");
        CalculateSingleType cal3 = new CalculateSingleType();
        TreeMap<String, BigDecimal> resVid = cal3.calSingleType(inputVidNum, subTableVid);
        for (Map.Entry<String, BigDecimal> entry:resVid.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("------------------");

    }


}
