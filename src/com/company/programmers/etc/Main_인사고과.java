package com.company.programmers.etc;

import java.util.Arrays;

public class Main_인사고과 {

    public static void main(String[] args) {

        int[][] scores = {{2,2},{1,4},{3,2},{3,2},{2,1}};
        System.out.println(solution(scores));

    }

    public static int solution(int[][] scores) {

        /**
         * 근무 태도 점수 + 동료 평가 점수
         *
         * 2,2
         * 1,4
         * 3,2
         * 3,2
         * 2,1
         *
         * row / column 기준으로 정렬
         * 1 4
         * 2 1
         * 2 2
         * 3 2
         * 3 2
         *
         *
         */
        // 정렬
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        for (int[] score: scores)
            System.out.println(Arrays.toString(score));

        return 0;

    }

}
