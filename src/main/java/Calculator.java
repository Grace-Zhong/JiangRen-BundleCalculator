import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

public class Calculator {
    public static void main(String[] args) {
        HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<String, TreeMap<Integer, BigDecimal>>();
        TreeMap<Integer, BigDecimal> image = new TreeMap<>();
        image.put(5,new BigDecimal(450));
        image.put(10,new BigDecimal(800));
        data.put("IMG", image);
        TreeMap<Integer, BigDecimal> audio = new TreeMap<>();
        audio.put(3,new BigDecimal(427.5));
        audio.put(6,new BigDecimal(810));
        audio.put(9,new BigDecimal(1147.5));
        data.put("FLAC", audio);
        TreeMap<Integer, BigDecimal> video = new TreeMap<>();
        video.put(3,new BigDecimal(570));
        video.put(5,new BigDecimal(900));
        video.put(9,new BigDecimal(1530));
        data.put("VID", video);

        calTotal(10, 15, 13, data);
    }

    public static void calTotal(int inputImgNum, int inputFlacNum, int inputVidNum, HashMap<String, TreeMap<Integer, BigDecimal>> table) {
        System.out.println("------------------");
        System.out.println("IMG");
        TreeMap<Integer, BigDecimal> subTableImg = table.get("IMG");
        TreeMap<String, BigDecimal> resImg = calSingleType(inputImgNum, subTableImg);
        for (Map.Entry<String, BigDecimal> entry:resImg.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("FLAC");
        TreeMap<Integer, BigDecimal> subTableFlac = table.get("FLAC");
        TreeMap<String, BigDecimal> resFlac = calSingleType(inputFlacNum, subTableFlac);
        for (Map.Entry<String, BigDecimal> entry:resFlac.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("VID");
        TreeMap<Integer, BigDecimal> subTableVid = table.get("VID");
        TreeMap<String, BigDecimal> resVid = calSingleType(inputVidNum, subTableVid);
        for (Map.Entry<String, BigDecimal> entry:resVid.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("------------------");

    }

    // return the cheapest solution of a single type social media
    public static TreeMap<String, BigDecimal> calSingleType(int inputNum, TreeMap<Integer, BigDecimal> subTable) {
        TreeMap<String, BigDecimal> res = new TreeMap<>();

        // if inputNum is smaller than the cheapest bundle
        int cheapestBundle = smallestBundle(subTable);
        if (inputNum <= cheapestBundle) {
            res.put(String.valueOf(cheapestBundle), new BigDecimal(1));
            res.put("Total", subTable.get(cheapestBundle));
        }
        else {
            // find threshold
            int threshold = 0;              // threshold = sum of all keys
            for (Integer key:subTable.keySet()) {
                threshold = threshold + key;
            }

            // cut inputNum if it is large
            int cutBundleBase = 0;         // record the redundant number of bundle
            int cut = 0;
            if (inputNum > threshold) {
                cutBundleBase = smallestUnitBundle(subTable);       // the bundle name should be cut
                cut = (int)Math.ceil((double)(inputNum - threshold)/cutBundleBase);    // the number of bundle should be cut
                inputNum = inputNum - cutBundleBase * cut;
            }

            // calculate the cheapest bundle
            res = calBasicCase(inputNum, subTable);

            // add the redundant number of bundle
            if (cut != 0) {
                res.put(String.valueOf(cutBundleBase), res.get(String.valueOf(cutBundleBase)).add(new BigDecimal(cut)));
                BigDecimal tmpBigDec = (new BigDecimal(cut)).multiply(subTable.get(cutBundleBase));
                res.put("Total", res.get("Total").add(tmpBigDec));
            }

        }

        return res;
    }

    public static TreeMap<String, BigDecimal> calBasicCase(int remain, TreeMap<Integer, BigDecimal> currentTable) {
        TreeMap<String, BigDecimal> res = new TreeMap<>();

        // initialization: the number of all key (beside the last key) is 0
        BigDecimal minCost = (new BigDecimal(Math.ceil((double)remain/currentTable.lastKey())))
                .multiply(currentTable.lastEntry().getValue());
        for (Map.Entry<Integer, BigDecimal> entry:currentTable.entrySet()) {
            res.put(String.valueOf(entry.getKey()), new BigDecimal(0));
        }
        res.put(String.valueOf(currentTable.lastKey()), new BigDecimal(Math.ceil((double)remain/currentTable.lastKey())));
        res.put("Total", minCost);

        // find the cheapest solution
        // base case
        if (currentTable.size() == 2) {
            int firstKey = currentTable.firstKey();
            int maxNumOfFirstKey = (int)Math.ceil((double)remain/firstKey);
            for (int i = 1; i <= maxNumOfFirstKey; i++) {
                BigDecimal numOfLastKey = new BigDecimal(Math.ceil((double)(remain-currentTable.firstKey() * i) / currentTable.lastKey()));
                BigDecimal currentSum = (new BigDecimal(i)).multiply(currentTable.firstEntry().getValue())
                        .add(numOfLastKey.multiply(currentTable.lastEntry().getValue()));
                if (currentSum.compareTo(minCost) == -1) {
                    minCost = currentSum;
                    res.clear();
                    res.put(currentTable.firstKey().toString(), new BigDecimal(i));
                    res.put(currentTable.lastKey().toString(), numOfLastKey);
                    res.put("Total", currentSum);
                }
            }
        }
        else {
            int firstKey = currentTable.firstKey();
            int maxNumOfFirstKey = (int)Math.ceil((double)remain/firstKey);
            for (int i = 0; i <= maxNumOfFirstKey; i++) {
                TreeMap<Integer, BigDecimal> dynamicTable = new TreeMap<>();
                dynamicTable.putAll(currentTable);
                BigDecimal costOfFirstKey = currentTable.firstEntry().getValue().multiply(new BigDecimal(i));
                dynamicTable.remove(dynamicTable.firstKey());
                TreeMap<String, BigDecimal> returnMap = calBasicCase(remain - currentTable.firstKey() * i, dynamicTable);
                BigDecimal currentTotalCost = returnMap.get(returnMap.lastKey()).add(costOfFirstKey);
                if (currentTotalCost.compareTo(minCost) == -1) {
                    minCost = currentTotalCost;
                    res.putAll(returnMap);
                    res.put(String.valueOf(currentTable.firstKey()), new BigDecimal(i));
                    res.put("Total", currentTotalCost);
                }
            }
        }
        return res;
    }

    // Return the bundle number with the lowest bundle price
    public static int smallestBundle(TreeMap<Integer, BigDecimal> subTable) {
        int res = 0;
        BigDecimal minValue = subTable.firstEntry().getValue();
        for (Map.Entry<Integer, BigDecimal> entry:subTable.entrySet()) {
            if (entry.getValue().compareTo(minValue) == -1) {
                minValue = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }

    // Return the bundle number with the lowest unit price
    public static int smallestUnitBundle(TreeMap<Integer, BigDecimal> subTable) {
        int res = 0;
        BigDecimal minValue =subTable.firstEntry().getValue().divide(new BigDecimal(subTable.firstKey()));
        for (Map.Entry<Integer, BigDecimal> entry:subTable.entrySet()) {
            if ((entry.getValue().divide(new BigDecimal(entry.getKey()))).compareTo(minValue) == -1) {
                minValue = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }


}
