import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;
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

        calTotal(10, 15, 19, data);
    }

    public static void calTotal(int inputImgNum, int inputFlacNum, int inputVidNum, HashMap<String, TreeMap<Integer, BigDecimal>> table) {
        System.out.println("------------------");
        System.out.println("VID");
        TreeMap<Integer, BigDecimal> subTable = table.get("VID");
        TreeMap<String, BigDecimal> resImg = calSingle(inputVidNum, subTable);
        for (Map.Entry<String, BigDecimal> entry:resImg.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("------------------");
    }

    public static TreeMap<String, BigDecimal> calSingle(int inputNum, TreeMap<Integer, BigDecimal> subTable) {
        TreeMap<String, BigDecimal> res = new TreeMap<>();

        // find the smallest value 的 key 是多少
        int cheapestBundle = smallestBundle(subTable);
        if (inputNum <= cheapestBundle) {
            res.put(String.valueOf(cheapestBundle), new BigDecimal(1));
            res.put("Total", subTable.get(cheapestBundle));
        }
        else {
            // find threshold
            int threshold = 0;      // threshold = sum of all keys
            for (Integer key:subTable.keySet()) {
                threshold = threshold + key;
            }

            // cut inputNum if it is large
            int cutBundleBase = 0;         // record the redundant number of bundle
            int cut = 0;
            if (inputNum > threshold) {
                // 需要 cut 的部分
                cutBundleBase = smallestUnitBundle(subTable);       // 需要 cut 的 bundle key
                cut = (int)Math.ceil((double)(inputNum - threshold)/cutBundleBase);    // 向上取整
                inputNum = inputNum - cutBundleBase * cut;
            }

            // calculate the cheapest bundle
            res = calBasic(inputNum, subTable);

            // add the redundant number of bundle
            res.put(String.valueOf(cutBundleBase), res.get(String.valueOf(cutBundleBase)).add(new BigDecimal(cut)));
            BigDecimal tmpBigDec = (new BigDecimal(cut)).multiply(subTable.get(cutBundleBase));
            res.put("Total", res.get("Total").add(tmpBigDec));
        }

        return res;
    }

    public static TreeMap<String, BigDecimal> calBasic(int remain, TreeMap<Integer, BigDecimal> currentTable) {
        TreeMap<String, BigDecimal> res = new TreeMap<>();
        // base case
        if (currentTable.size() == 2) {
            // first key 为 0
            BigDecimal minCost = new BigDecimal(Math.ceil((double)remain/currentTable.lastKey()))
                    .multiply(currentTable.lastEntry().getValue());
            res.put(currentTable.firstKey().toString(), new BigDecimal(0));
            res.put(currentTable.lastKey().toString(), new BigDecimal(Math.ceil((double)remain/currentTable.lastKey())));
            res.put("Total", minCost);

            int firstKey = currentTable.firstKey();
            int maxNumOfFirstKey = (int)Math.ceil((double)remain/firstKey);
            for (int i = 1; i <= maxNumOfFirstKey; i++) {
                BigDecimal numOfLastKey = new BigDecimal(Math.ceil((double)(remain-currentTable.firstKey() * i) / currentTable.lastKey()));
                BigDecimal currentSum = (new BigDecimal(i)).multiply(currentTable.firstEntry().getValue())
                        .add(numOfLastKey.multiply(currentTable.lastEntry().getValue()));
                //如果当前钱数小于 min，min 为新钱数
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
            // 全要最后一个 key
            BigDecimal minCost = (new BigDecimal(Math.ceil((double)remain/currentTable.lastKey())))
                    .multiply(currentTable.lastEntry().getValue());
            for (Map.Entry<Integer, BigDecimal> entry:currentTable.entrySet()) {
                res.put(String.valueOf(entry.getKey()), new BigDecimal(0));
            }
            res.put(String.valueOf(currentTable.lastKey()), new BigDecimal(Math.ceil((double)remain/currentTable.lastKey())));


            res.put("Total", minCost);

            int firstKey = currentTable.firstKey();
            int maxNumOfFirstKey = (int)Math.ceil((double)remain/firstKey);
            for (int i = 0; i <= maxNumOfFirstKey; i++) {
                TreeMap<Integer, BigDecimal> dynamicTable = new TreeMap<>();
                dynamicTable.putAll(currentTable);
                // 记录第一个要花多少钱， i * firstKey
                BigDecimal costOfFirstKey = currentTable.firstEntry().getValue().multiply(new BigDecimal(i));
                // record removed key & value
                String recordedKey = String.valueOf(currentTable.firstKey());

                dynamicTable.remove(dynamicTable.firstKey());
                TreeMap<String, BigDecimal> returnMap = calBasic(remain - currentTable.firstKey() * i, dynamicTable);
                BigDecimal currentTotalCost = returnMap.get(returnMap.lastKey()).add(costOfFirstKey);
                if (currentTotalCost.compareTo(minCost) == -1) {
                    minCost = currentTotalCost;
                    res.putAll(returnMap);
                    res.put(recordedKey, new BigDecimal(i));
                    res.put("Total", currentTotalCost);
                }
            }
        }
        return res;
    }

    // 返回总价最便宜的 bundle 数
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

    // 返回单价最低的 bundle 数
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
