package com.company.programmers.etc;

import java.util.*;

public class Main_양과늑대 {

    public static void main(String[] args) {

//        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
//        int[][] edges = {{0,1}, {1,2}, {1,4}, {0,8}, {8,7}, {9,10}, {9,11}, {4,3}, {6,5}, {4,6}, {8,9}};
//        System.out.println(solution(info, edges));

        int[] info2 = {0,1,0,1,1,0,1,0,0,1,0};
        int[][] edges2 = {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}, {3,7}, {4,8}, {6,9}, {9,10}};
        System.out.println(solution(info2, edges2));

    }

    public static Map<Integer, List<Integer>> edgeMap = new HashMap<>();
    public static int answer = 0; // 최댓값

    public static int solution(int[] info, int[][] edges) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/92343
         *
         * 지나갈 수 있는 상태인지 확인하고, 지나갈 수 있으면 자기 자손을 방문할 리스트에 넣어준다.
         */

        for (int[] edgeInfo: edges)
            edgeMap.computeIfAbsent(edgeInfo[0], k -> new ArrayList<>())
                    .add(edgeInfo[1]);

        System.out.println(edgeMap);

        List<Integer> nextList = new ArrayList<>(Arrays.asList(0));
        int[] sheepCnt = new int[2]; // index 0: 양의 수, 1: 늑대의 수

        dfs(0, info, sheepCnt, nextList);

        return sheepCnt[0];
    }


    static void dfs(int parent, int[] info, int[] sheepCnt, List<Integer> nextList) {

        System.out.println(parent);
        if (info[parent] == 0) {
            sheepCnt[0]++;
        } else {
            if (sheepCnt[1] + 1 == sheepCnt[0])
                return;
            else
                sheepCnt[1]++;
        }

        System.out.println("sheepCnt: " + Arrays.toString(sheepCnt));

        List<Integer> newNextList = new ArrayList<>();

        for (Integer next: nextList) // 깊은 복사
            newNextList.add(next);

        newNextList.remove(nextList.indexOf(parent));
        newNextList.addAll(edgeMap.getOrDefault(parent, new ArrayList<>()));
        System.out.println("nextList: " + newNextList);
        newNextList.forEach(next -> dfs(next, info, sheepCnt, newNextList));

        answer = Math.max(answer, sheepCnt[0]);

    }

}
