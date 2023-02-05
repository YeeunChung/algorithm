package com.company.programmers.hash;

import java.util.*;

public class Main_인사고과 {

    public static void main(String[] args) {

        int[][] scores = {{2,1},{1,4},{3,2},{3,2},{2,1}};
        System.out.println(solution(scores));

    }

    public static int solution(int[][] scores) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/152995
         *
         * 근무태도는 내림차순, 인사평가는 오름차순으로 정렬하여 인사평가만 비교하는 것이 핵심...!
         */

        // (인사고과 점수 합, 해당 점수 사람 수)
        Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder()); // TreeMap: 사전 순 정렬
        int answer = 0;
        int wanhoScore = scores[0][0] + scores[0][1];
        int wanhoAttribute = scores[0][0], wanhoPeer = scores[0][1];

        // 정렬: 근무태도는 내림차순, 인사평가는 오름차순
        Arrays.sort(scores, (o1, o2) -> o1[0] == o2[0]? o1[1] - o2[1]: o2[0] - o1[0]);

        int maxPeer = 0;
        for (int i=0; i<scores.length; ++i) {
            // 근무태도는 이미 비교되었으므로, 인사평가만 비교하면 됨
            if (maxPeer > scores[i][1]) {
                if (scores[i][0] == wanhoAttribute && scores[i][1] == wanhoPeer)
                    return -1;
            } else {
                maxPeer = scores[i][1];
                map.put(scores[i][0] + scores[i][1], map.getOrDefault(scores[i][0] + scores[i][1], 0) + 1);
            }
        }

        // 등수 구하기
        for (Integer score: map.keySet()) {
            if (score == wanhoScore) {
                answer++; break;
            } else answer += map.get(score);
        }

        return answer;
    }

}
