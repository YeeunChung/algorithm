package com.company.programmers.etc;

import java.lang.reflect.Array;
import java.util.*;

public class Main_기지국설치 {

    public static void main(String[] args) {

        int[] stations = {9};
        System.out.println(solution(16, stations, 2));

    }

    public static int solution(int n, int[] stations, int w) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/12979
         *
         * 1. 단순하게 coverage 모두 표시 -> 답은 통과하는데 시간 초과 남
         * 2. 커버 안 된 구간 구해서 계산하기 -> 답은 통과하는데 시간 초과 (더 느려짐...)
         **/
        int answer = 0, start = 0;
        Map<Integer, Integer> uncoveredMap = new HashMap<>();

        for (int i=0; i< stations.length; ++i) {
            int s = stations[i] - 1 - w < 0? 0: stations[i] - 1 - w;
            int e = stations[i] - 1 + w >= n? n - 1: stations[i] - 1 + w;
            System.out.println(s + " " + e);

            if (start <= s-1) uncoveredMap.put(start, s-1);
            start = e+1;
        }

        if (start != n) uncoveredMap.put(start, n-1);
        System.out.println(uncoveredMap);

        Set<Integer> keySet = uncoveredMap.keySet();
        for (Integer key: keySet) {
            int length = uncoveredMap.get(key) - key + 1; // 커버 안 된 길이
            answer += length % (2*w + 1) == 0? length / (2*w + 1): length / (2*w + 1) + 1;
        }

        return answer;
    }

    static void cover(boolean[] coverage, int mid, int w) {
        for (int i=mid; i>=0 && i>=mid-w; --i)
            coverage[i] = true;

        for (int i=mid; i< coverage.length && i<=mid+w; ++i)
            coverage[i] = true;
    }

}
