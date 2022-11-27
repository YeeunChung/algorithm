package com.company.programmers.bruteforce;

import java.util.*;

public class Main_전력망을둘로나누기 {

    public static void main(String[] args) {
        int[][] wired = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solution(9, wired));
    }

    static int[][] tree;

    public static int solution(int n, int[][] wires) {
        /** 1. 인접 행렬 / 인접 리스트 통해 그래프 표현
         *         => 이거 어떻게 함??
         * 2. 선 하나씩 끊어보며 나눠진 두 전력망 차이 구하기
         *  2-1. 선 하나 끊기
         *  2-2. bfs 이용해서 송전탑 개수 구하기
         *  2-3. 선 복구
         */
        int min = 101;
        tree = new int[n+1][n+1];

        // 인접 행렬로 그래프 표현하기
        for (int i=0; i<wires.length; ++i) {
            tree[wires[i][0]][wires[i][1]] = 1;
            tree[wires[i][1]][wires[i][0]] = 1;
        }

        // 선 하나씩 끊어보기
        for (int i=0; i<wires.length; ++i) {
            tree[wires[i][0]][wires[i][1]] = 0;
            tree[wires[i][1]][wires[i][0]] = 0;

            // bfs 탐색
            int cnt = bfs(wires[i][0]);
            min = Math.min(min, Math.abs(cnt * 2 - n));

            tree[wires[i][0]][wires[i][1]] = 1;
            tree[wires[i][1]][wires[i][0]] = 1;
        }

        return min;
    }

    public static int bfs(int idx) {

        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx);

        while (! queue.isEmpty()) {
            int num = queue.poll();
            set.add(num);

            for (int i=1; i< tree.length; ++i) {
                if (tree[num][i] == 1 && ! set.contains(i)) // 연결되어 있다면
                    queue.add(i);
            }
        }

        return set.size();
    }

}
