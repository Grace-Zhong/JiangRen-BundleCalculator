package pers.grace.calculator.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Calculator {

    public HashMap<Integer, Integer> calculate(int input, Set<Integer> bundleSet) {
        HashMap<Integer, HashMap<Integer, Integer>> store = new HashMap<>();

        List<Integer> bundleList = bundleSet.stream().sorted().collect(Collectors.toList());
        HashMap<Integer, Integer> res = new HashMap<>();

        // small case
        int bundleListSize = bundleList.size();
        if (input <= bundleList.get(0)) {
            res.put(bundleList.get(0), 1);
            return res;
        }

        // base case
        for (int i = 1; i <= bundleList.get(0); i++) {
            HashMap<Integer, Integer> tmp = new HashMap<>();
            for (int j = 1; j < bundleListSize; j++) {
                tmp.put(bundleList.get(j), 0);
            }
            tmp.put(bundleList.get(0), 1);
            store.put(i, tmp);
        }
        HashSet<Integer> noRemainSet = new HashSet<>(bundleSet);

        for (int i = bundleList.get(0) + 1; i <= input; i++) {
            // 判断能不能被整除
            for (int j = bundleListSize - 1; j >= 0; j--) {
                int item = bundleList.get(j);
                if (i - item == 0 || noRemainSet.contains(i - item)) {
                    noRemainSet.add(i);
                    if (i - item == 0) {
                        HashMap<Integer, Integer> tmp = new HashMap<>();
                        for (Integer integer : bundleList) {
                            tmp.put(integer, 0);
                        }
                        tmp.put(i, 1);
                        store.put(i, tmp);
                    }
                    else {
                        store.put(i, store.get(i - item));
                        HashMap<Integer, Integer> tmp = new HashMap<>(store.get(i));
                        tmp.put(item, tmp.get(item) + 1);
                        store.put(i, tmp);
                    }
                    break;
                }
            }

            // 除不尽
            if (!noRemainSet.contains(i)) {
                // min 初始值
                int min = store.get(i - bundleList.get(0)).values().stream().reduce(0, Integer::sum);
                HashMap<Integer, Integer> minMap = new HashMap<>(store.get(i - bundleList.get(0)));

                for (int curBundle : bundleList) {
                    if (i - curBundle > 0) {
                        int tmpMin = store.get(i - curBundle).values().stream().reduce(0, Integer::sum);
                        if (tmpMin <= min) {
                            minMap.clear();
                            minMap.putAll(store.get(i - curBundle));
                            store.put(i, minMap);
                            store.get(i).put(curBundle, store.get(i).get(curBundle) + 1);
                        }
                    } else {
                        HashMap<Integer, Integer> tmp = new HashMap<>();
                        for (Integer integer : bundleList) {
                            tmp.put(integer, 0);
                        }
                        tmp.put(curBundle, 1);
                        store.put(i, tmp);
                        break;
                    }
                }
            }
//            log.info(store.toString());
        }

        res.putAll(store.get(input));
        return res.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
