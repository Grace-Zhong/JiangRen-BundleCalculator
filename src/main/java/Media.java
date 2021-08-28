import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class Media {

    private String name;
    private TreeMap<Integer, BigDecimal> table;

    public String getMediaName() {
        return name;
    }

    public void setMediaName(String mediaName) {
        this.name = mediaName;
    }

    public TreeMap<Integer, BigDecimal> getTable() {
        return table;
    }

    public void setTable(TreeMap<Integer, BigDecimal> table) {
        this.table = table;
    }

    public Media() {
        this.name = "";
        this.table = null;
    }

    public Media(String name, TreeMap<Integer, BigDecimal> table) {
        setMediaName(name);
        setTable(table);
    }

    // return the cheapest solution of a single type social media
    public TreeMap<String, BigDecimal> calSingleType(int inputNum) {
        final String total = "TOTAL";
        BigDecimal totalCost;
        TreeMap<String, BigDecimal> res = new TreeMap<>();
        final Logger logger = Logger.getLogger("Logging single media...");

        // if inputNum is smaller than the cheapest bundle
        int cheapestBundle = smallestBundle(this.getTable());
        if (inputNum <= cheapestBundle) {
            res.put(String.valueOf(cheapestBundle), new BigDecimal(1));
            res.put(total, this.getTable().get(cheapestBundle));
            totalCost = this.getTable().get(cheapestBundle);
        }
        else {
            // find threshold
            int threshold = 0;              // threshold = sum of all keys
            for (Integer key:this.getTable().keySet()) {
                threshold = threshold + key;
            }

            // cut inputNum if it is large
            int cutBundleBase = 0;         // record the redundant number of bundle
            int cut = 0;
            if (inputNum > threshold) {
                cutBundleBase = smallestUnitBundle(this.getTable());       // the bundle name should be cut
                cut = (int)Math.ceil((double)(inputNum - threshold)/cutBundleBase);    // the number of bundle should be cut
                inputNum = inputNum - cutBundleBase * cut;
            }

            // calculate the cheapest bundle
            res = calBasicCase(inputNum, this.getTable());
            totalCost = res.get(total);

            // add back the redundant number of bundle
            if (cut != 0) {
                res.put(String.valueOf(cutBundleBase), res.get(String.valueOf(cutBundleBase)).add(new BigDecimal(cut)));
                totalCost = res.get(total).add((new BigDecimal(cut)).multiply(this.getTable().get(cutBundleBase)));
                res.put(total, totalCost);
            }
        }

        String output = printResult(inputNum, this.table, this.name, res, totalCost);
        logger.info(output);
        return res;
    }

    // calculate basic cases (input is not small and not large)
    private static TreeMap<String, BigDecimal> calBasicCase(int remain, TreeMap<Integer, BigDecimal> currentTable) {
        final String total = "TOTAL";
        TreeMap<String, BigDecimal> res = new TreeMap<>();

        // initialization: the number of all key (beside the last key) is 0
        BigDecimal minCost = (new BigDecimal(Math.ceil((double)remain/currentTable.lastKey())))
                .multiply(currentTable.lastEntry().getValue());
        for (Map.Entry<Integer, BigDecimal> entry:currentTable.entrySet()) {
            res.put(String.valueOf(entry.getKey()), new BigDecimal(0));
        }
        res.put(String.valueOf(currentTable.lastKey()), new BigDecimal(Math.ceil((double)remain/currentTable.lastKey())));
        res.put(total, minCost);

        // find the cheapest solution
        // base case
        if (currentTable.size() == 2) {
            int firstKey = currentTable.firstKey();
            int maxNumOfFirstKey = (int)Math.ceil((double)remain/firstKey);
            for (int i = 1; i <= maxNumOfFirstKey; i++) {
                BigDecimal numOfLastKey = new BigDecimal(Math.ceil((double)(remain-currentTable.firstKey() * i)
                        / currentTable.lastKey()));
                BigDecimal currentSum = (new BigDecimal(i)).multiply(currentTable.firstEntry().getValue())
                        .add(numOfLastKey.multiply(currentTable.lastEntry().getValue()));
                if (currentSum.compareTo(minCost) == -1) {
                    minCost = currentSum;
                    res.clear();
                    res.put(currentTable.firstKey().toString(), new BigDecimal(i));
                    res.put(currentTable.lastKey().toString(), numOfLastKey);
                    res.put(total, currentSum);
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
                    res.put(total, currentTotalCost);
                }
            }
        }
        return res;
    }

    // Return the bundle number with the lowest bundle price
    private static int smallestBundle(TreeMap<Integer, BigDecimal> subTable) {
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
    private static int smallestUnitBundle(TreeMap<Integer, BigDecimal> subTable) {
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

    private static String printResult(int inputNum, TreeMap<Integer, BigDecimal> table, String name,
                                      TreeMap<String, BigDecimal> res, BigDecimal totalCost) {
        final String total = "TOTAL";
        String output = "\n";
        output += inputNum + " " + name + " $" + totalCost.toString() + "\n";
        for (Map.Entry<String, BigDecimal> entry:res.entrySet()) {
            if (!entry.getKey().equals(total) && entry.getValue().compareTo(new BigDecimal(0)) != 0)
            output += "  " + entry.getValue().toString() + " x " + entry.getKey() + " $"
                    + table.get(Integer.valueOf(entry.getKey())).multiply(entry.getValue()) + "\n";
        }
        return output;
    }

}