package com.company.programmers.etc;

import java.util.*;
import java.util.stream.Collectors;

public class Main_부대복귀 {

    public static void main(String[] args) {

//        int[][] roads = {{1,2},{2,3}};
//        int[] source = {2,3};
//        System.out.println(Arrays.toString(solution(3, roads, source, 1)));

        int[][] roads = {{1,2},{1,4},{2,4},{2,5},{4,5}};
        int[] source = {1,3,5};
        System.out.println(Arrays.toString(solution(5, roads, source, 5)));

    }

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/132266
         *
         * 1차 시도: roads 돌면서 정보 저장하고 bfs로 탐색 => 시간초과 남
         * 2차 시도: dp 써서 저장해뒀다가 돌면서 계산하기 (floyd 알고리즘) => 시간 초과, 메모리 초과
         * 3차 시도: roads 돌면서 정보 저장하고 floyd처럼 탐색하기
         *  - 근데 진짜 뒤에부터 탐색해야하나? ㅠㅠ
         */
        Map<Integer, List<Integer>> roadMap = new HashMap<>();
        List<Integer> valueList;
        int[] dynamic = new int[n+1];
        int[] answer = new int[sources.length];

        for (int i=0; i< roads.length; ++i) {
            valueList = roadMap.getOrDefault(roads[i][0], new ArrayList<>());
            valueList.add(roads[i][1]);
            roadMap.put(roads[i][0], valueList);

            valueList = roadMap.getOrDefault(roads[i][1], new ArrayList<>());
            valueList.add(roads[i][0]);
            roadMap.put(roads[i][1], valueList);
        }

        if (! roadMap.containsKey(destination)) {
            Arrays.fill(answer, -1);
            return answer;
        }

        valueList = roadMap.get(destination);


        return answer;
    }
}
