package com.company.programmers.two_pointer;

import java.util.*;

public class Main_보석쇼핑 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/67258
     */

    public static void main(String[] args) {

        String[] gems = {"A", "A", "A"};
        System.out.println(Arrays.toString(solution(gems)));

    }

    public static int[] solution(String[] gems) {
        /**
         * A B B C A A B C
         * 첫 번째 window를 기준으로 쭉 훑기...?
         *  => 뒤에서 또 나오면 window - 1 해서 해당 부분 검사
         *  => 또 되면 window - 2 해서 또 해당 부분 검사
         *  ...ing
         *  투포인터 알고리즘 쓰면 된다고 함!!!
         *  투포인터 알고리즘: https://github.com/WooVictory/Ready-For-Tech-Interview/blob/master/Algorithm/%ED%88%AC%ED%8F%AC%EC%9D%B8%ED%84%B0%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98.md
         */
        int[] answer = new int[2];
        Set<String> gemSet = new HashSet<>();
        for (int i=0; i<gems.length; ++i)
            gemSet.add(gems[i]);

        if (gemSet.size() == 1) {
            answer[0] = 1; answer[1] = 1;
            return answer;
        }

        int start = 0, end = 0;
        int minSize = Integer.MAX_VALUE;
        Map<String, Integer> gemMap = new HashMap<>();

        gemMap.put(gems[0], 1);

        while (start <= end && end < gems.length - 1) {
            end++;
            gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);

            while (gemMap.size() == gemSet.size()) {
                if (minSize > end - start) {
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                    minSize = end - start;
                }

                // start 한 칸씩 뒤로 이동하기
                int cnt = gemMap.get(gems[start]) - 1;
                if (cnt == 0) gemMap.remove(gems[start]);
                else gemMap.put(gems[start], cnt);
                start++;
                continue;
            }
        }

        return answer;
    }

}
