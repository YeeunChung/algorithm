package com.company.programmers.etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_양과늑대 {

    public static void main(String[] args) {

        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};

    }

    public static Map<Integer, List<Integer>> edgeMap;
    public static List<Integer> sheepList;
    public static int[] sheepCnt;

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
         * 부모를
         */

        edgeMap = new HashMap<>();
        sheepList = new ArrayList<>();
        sheepCnt = new int[2]; // index 0: 양, 1: 늑대

        for (int[] edgeInfo: edges)
            edgeMap.computeIfAbsent(edgeInfo[0], k -> new ArrayList<>())
                    .add(edgeInfo[1]);

        sheepList.add(0);
        sheepCnt[0]++;

    }


    void dfs(int parent, int[] info) {

        List<Integer> childList = edgeMap.get(parent);

        /**
         * child
         */
        for (Integer child: childList) {

        }

    }

}
