package pers.grace.calculator.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Calculator {

    public HashMap<Integer, Integer> calculate(int input, Set<Integer> bundleSet) {
        List<Integer> bundleList = bundleSet.stream().sorted().collect(Collectors.toList());
        HashMap<Integer, Integer> res = new HashMap<>();
        int bundleListSize = bundleList.size();
        if (input <= bundleList.get(0)) {
            res.put(bundleList.get(0), 1);
            return res;
        }

        HashMap<Integer, HashMap<Integer, Integer>> store = new HashMap<>();
        // base case
        for (int i = 1; i <= bundleList.get(0); i++) {
            store.put(i, generateBase(bundleListSize, bundleList));
        }

        HashSet<Integer> noRemainSet = new HashSet<>(bundleSet);
        for (int i = bundleList.get(0) + 1; i <= input; i++) {
            // no remain
            for (int j = bundleListSize - 1; j >= 0; j--) {
                int item = bundleList.get(j);
                if (i - item == 0) {
                    noRemainSet.add(i);
                    store.put(i, generate(i, bundleList));
                    break;
                } else if (noRemainSet.contains(i - item)) {
                    noRemainSet.add(i);
                    store.put(i, store.get(i - item));
                    HashMap<Integer, Integer> tmp = new HashMap<>(store.get(i));
                    tmp.put(item, tmp.get(item) + 1);
                    store.put(i, tmp);
                    break;
                }
            }

            // with remain
            if (!noRemainSet.contains(i)) {
                int min = store.get(i - bundleList.get(0)).values().stream().reduce(0, Integer::sum);
                HashMap<Integer, Integer> minMap = new HashMap<>(store.get(i - bundleList.get(0)));
                for (int curBundle : bundleList) {
                    if (i - curBundle > 0) {
                        int tmpMin = store.get(i - curBundle).values().stream().reduce(0, Integer::sum);
                        if (tmpMin <= min) {
                            minMap.putAll(store.get(i - curBundle));
                            store.put(i, minMap);
                            store.get(i).put(curBundle, store.get(i).get(curBundle) + 1);
                        }
                    } else {
                        store.put(i, generate(curBundle, bundleList));
                        break;
                    }
                }
            }
        }
        res.putAll(store.get(input));
        return res.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private static HashMap<Integer, Integer> generateBase(int bundleListSize, List<Integer> bundleList) {
        HashMap<Integer, Integer> res = new HashMap<>();
        for (int j = 1; j < bundleListSize; j++) {
            res.put(bundleList.get(j), 0);
        }
        res.put(bundleList.get(0), 1);
        return res;
    }

    private static HashMap<Integer, Integer> generate(int curBundle, List<Integer> bundleList) {
        HashMap<Integer, Integer> res = new HashMap<>();
        for (Integer integer : bundleList) {
            res.put(integer, 0);
        }
        res.put(curBundle, 1);
        return res;
    }
}
