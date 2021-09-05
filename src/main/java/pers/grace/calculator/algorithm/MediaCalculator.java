package pers.grace.calculator.algorithm;

import lombok.extern.slf4j.Slf4j;
import pers.grace.calculator.model.Media;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class MediaCalculator {

    /***
     * Calculate the cheapest solution of a single media (all possible cases)
     * @param inputNum  the number of bundle users needed
     * @return the cheapest solution of a single type social media
     */
    public TreeMap<String, BigDecimal> calSingleMedia(Media media, int inputNum) {
        final String total = "TOTAL";
        BigDecimal totalCost;
        TreeMap<String, BigDecimal> res = new TreeMap<>();

        // if inputNum is smaller than the cheapest bundle
        int cheapestBundle = cheapestBundle(media.table());
        if (inputNum <= cheapestBundle) {
            res.put(String.valueOf(cheapestBundle), new BigDecimal(1));
            res.put(total, media.table().get(cheapestBundle));
            totalCost = media.table().get(cheapestBundle);
        } else {
            // find threshold, threshold = sum of all keys
            int threshold = media.table().keySet().stream().reduce(0, Integer::sum);

            // cut inputNum if it is large
            int cutBundleBase = 0;         // record the redundant number of bundle
            int cut = 0;
            if (inputNum > threshold) {
                cutBundleBase = cheapestUnitBundle(media.table());       // the bundle name should be cut
                cut = (int) Math.ceil((double) (inputNum - threshold) / cutBundleBase);    // the number of bundle should be cut
                inputNum = inputNum - cutBundleBase * cut;
            }

            // calculate the cheapest bundle
            res = calBasicCase(inputNum, media.table());
            totalCost = res.get(total);

            // add back the redundant number of bundle
            if (cut != 0) {
                res.put(String.valueOf(cutBundleBase), res.get(String.valueOf(cutBundleBase)).add(new BigDecimal(cut)));
                totalCost = res.get(total).add((new BigDecimal(cut)).multiply(media.table().get(cutBundleBase)));
                res.put(total, totalCost);
            }
        }
        String output = printResult(inputNum, media.table(), media.name(), res, totalCost);
        log.info(output);
        return res;
    }

    // Calculate the cheapest solution of a single media (basic cases only, input is not small and not large)
    private static TreeMap<String, BigDecimal> calBasicCase(int remain, TreeMap<Integer, BigDecimal> currentTable) {
        final String total = "TOTAL";
        TreeMap<String, BigDecimal> res = new TreeMap<>();

        // initialization: the number of all key (beside the last key) is 0
        BigDecimal minCost = (new BigDecimal(Math.ceil((double) remain / currentTable.lastKey())))
                .multiply(currentTable.lastEntry().getValue());
        for (Map.Entry<Integer, BigDecimal> entry : currentTable.entrySet()) {
            res.put(String.valueOf(entry.getKey()), new BigDecimal(0));
        }
        res.put(String.valueOf(currentTable.lastKey()), new BigDecimal(Math.ceil((double) remain / currentTable.lastKey())));
        res.put(total, minCost);

        // find the cheapest solution
        // base case
        if (currentTable.size() == 2) {
            int firstKey = currentTable.firstKey();
            int maxNumOfFirstKey = (int) Math.ceil((double) remain / firstKey);
            for (int i = 1; i <= maxNumOfFirstKey; i++) {
                BigDecimal numOfLastKey = new BigDecimal(Math.ceil((double) (remain - currentTable.firstKey() * i)
                        / currentTable.lastKey()));
                BigDecimal currentSum = (new BigDecimal(i)).multiply(currentTable.firstEntry().getValue())
                        .add(numOfLastKey.multiply(currentTable.lastEntry().getValue()));
                if (currentSum.compareTo(minCost) < 0) {
                    minCost = currentSum;
                    res.clear();
                    res.put(currentTable.firstKey().toString(), new BigDecimal(i));
                    res.put(currentTable.lastKey().toString(), numOfLastKey);
                    res.put(total, currentSum);
                }
            }
        } else {
            int firstKey = currentTable.firstKey();
            int maxNumOfFirstKey = (int) Math.ceil((double) remain / firstKey);
            for (int i = 0; i <= maxNumOfFirstKey; i++) {
                TreeMap<Integer, BigDecimal> dynamicTable = new TreeMap<>();
                dynamicTable.putAll(currentTable);
                BigDecimal costOfFirstKey = currentTable.firstEntry().getValue().multiply(new BigDecimal(i));
                dynamicTable.remove(dynamicTable.firstKey());
                TreeMap<String, BigDecimal> returnMap = calBasicCase(remain - currentTable.firstKey() * i, dynamicTable);
                BigDecimal currentTotalCost = returnMap.get(returnMap.lastKey()).add(costOfFirstKey);
                if (currentTotalCost.compareTo(minCost) < 0) {
                    minCost = currentTotalCost;
                    res.putAll(returnMap);
                    res.put(String.valueOf(currentTable.firstKey()), new BigDecimal(i));
                    res.put(total, currentTotalCost);
                }
            }
        }
        return res;
    }

    private static int cheapestBundle(TreeMap<Integer, BigDecimal> subTable) {
        Map.Entry<Integer, BigDecimal> minEntry = subTable.entrySet().stream()
                .reduce(subTable.firstEntry(), (firstEntry, secondEntry) ->
                        firstEntry.getValue().compareTo(secondEntry.getValue()) < 0 ? firstEntry : secondEntry);
        return minEntry.getKey();
    }

    private static int cheapestUnitBundle(TreeMap<Integer, BigDecimal> subTable) {
        Map.Entry<Integer, BigDecimal> minEntry = subTable.entrySet().stream()
                .reduce(subTable.firstEntry(), (firstEntry, secondEntry) ->
                        firstEntry.getValue().divide(new BigDecimal(firstEntry.getKey()))
                                .compareTo(secondEntry.getValue().divide(new BigDecimal(secondEntry.getKey()))) < 0 ?
                                firstEntry : secondEntry
                );
        return minEntry.getKey();


//        Map.Entry<Integer, BigDecimal> minEntry = subTable.entrySet().stream()
//                .reduce(subTable.firstEntry(), (firstEntry, secondEntry) ->
//                        firstEntry.getValue().divide(new BigDecimal(firstEntry.getKey()))
//                                .compareTo(secondEntry.getValue().divide(new BigDecimal(secondEntry.getKey()))) < 0 ?
//                                firstEntry : secondEntry
//                );
//        return minEntry.getKey();
    }

    /***
     * Return the cheapest solution of this media by string
     * @param inputNum the number of bundle users need for this media
     * @param table  the data information of this media
     * @param name  the name code of this media
     * @param res  the cheapest solution of this media
     * @param totalCost  the total cost of the cheapest solution
     * @return the string should be print by logger
     */
    private static String printResult(int inputNum, TreeMap<Integer, BigDecimal> table, String name,
                                      TreeMap<String, BigDecimal> res, BigDecimal totalCost) {
        final String total = "TOTAL";
        StringBuilder output = new StringBuilder("\n");
        output.append(inputNum).append(" ").append(name).append(" $").append(totalCost.toString()).append("\n");

        for (Map.Entry<String, BigDecimal> entry : res.entrySet()) {
            if (!entry.getKey().equals(total) && entry.getValue().compareTo(new BigDecimal(0)) != 0)
                output.append("  ").append(entry.getValue()).append(" x ").append(entry.getKey()).append(" $").append(table.get(Integer.valueOf(entry.getKey())).multiply(entry.getValue())).append("\n");
        }
        return output.toString();
    }

}
