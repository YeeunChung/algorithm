package com.company.programmers.dfs;

import java.util.*;

public class Main_양과늑대 {

    public static void main(String[] args) {

        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1}, {1,2}, {1,4}, {0,8}, {8,7}, {9,10}, {9,11}, {4,3}, {6,5}, {4,6}, {8,9}};
        System.out.println(solution(info, edges));

//        int[] info2 = {0,1,0,1,1,0,1,0,0,1,0};
//        int[][] edges2 = {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}, {3,7}, {4,8}, {6,9}, {9,10}};
//        System.out.println(solution(info2, edges2));

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

        List<Integer> nextList = new ArrayList<>(Arrays.asList(0));
        dfs(0, 0, 0, info, nextList);

        return answer;
    }


    static void dfs(int parent, int sheepCnt, int wolfCnt, int[] info, List<Integer> nextList) {

        if (info[parent] == 0) {
            ++sheepCnt;
        } else {
            if (wolfCnt + 1 == sheepCnt)
                return;
            else
                wolfCnt++;
        }

        answer = Math.max(answer, sheepCnt);

        List<Integer> newNextList = new ArrayList<>();
        newNextList.addAll(nextList); // 깊은 복사

        newNextList.remove(nextList.indexOf(parent));
        newNextList.addAll(edgeMap.getOrDefault(parent, new ArrayList<>()));
        int finalSheepCnt = sheepCnt;
        int finalWolfCnt = wolfCnt;
        newNextList.forEach(next -> dfs(next, finalSheepCnt, finalWolfCnt, info, newNextList));

    }

}
