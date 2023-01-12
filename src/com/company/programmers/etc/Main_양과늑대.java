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
         * 1. 양이면 일단 고 하고 info를 바꿔준다 (비워준다. 2로)
         * 2. 늑대고 더해도 양 수 안 넘으면 일단 진행...
         *    더했는데 양 수랑 같아지면 뒤로 빽도 수행... 양 수 - 1 == 늑대수 일 때까지 (while문으로)
         *      그러다가 root까지 와버리면...?
         *          다른 쪽 한 번 더 돌아보고 또 다시 돌아오면 끝
         *      뺵도하다가 양수 - 1 == 늑대수인 지점 오면 안 방문한 쪽으로 다시 가보기
         *    진행하다가 맨 끝 도달하면 빽도하기
         *
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

        if (info[parent] == 0) {
            sheepCnt[0]++;
        } else {
            if (sheepCnt[1] + 1 == sheepCnt[0])
                return;
            else
                sheepCnt[1]++;
        }

        nextList.remove(parent);
        nextList.addAll(edgeMap.getOrDefault(parent, new ArrayList<>()));
        nextList.forEach(next -> );


    }

}
