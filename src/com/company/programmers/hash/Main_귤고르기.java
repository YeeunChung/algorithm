package com.company.programmers.hash;

import java.util.*;

public class Main_귤고르기 {

    public static void main(String[] args) {

        int[] tangerine = {1, 1, 1, 1, 2, 2, 2, 3};
        System.out.println(solution(5, tangerine));

    }

    public static int solution(int k, int[] tangerine) {

        int count = 0;
        Map<Integer, Integer> tanCnt = new HashMap<>();

        for (int i=0; i<tangerine.length; ++i)
            tanCnt.put(tangerine[i], tanCnt.getOrDefault(tangerine[i], 0) + 1);

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(tanCnt.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        for (Map.Entry<Integer, Integer> entry: entryList) {
            k -= entry.getValue();
            ++count;

            if (k <= 0) break;
        }

        return count;
    }

}
