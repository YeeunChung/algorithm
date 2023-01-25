package com.company.programmers.stack;

import java.util.*;

public class Main_택배상자 {

    public static void main(String[] args) {
        int[] order = {1, 3, 2, 5, 4};
        System.out.println(solution(order));
    }

    public static int solution(int[] order) {
        /**
         *
         * https://school.programmers.co.kr/learn/courses/30/lessons/131704
         * 1 3 2 5 4
         * 3 5 4 1 2
         */

        Stack<Integer> stack = new Stack<>();

        int answer = 0;
        int idx = 1;

        for (int o : order) {

            if (stack.isEmpty()) {
                for (int j = idx; j < o; ++j)
                    stack.push(j);

            } else if (stack.peek() == o)
                stack.pop();
            else if (!stack.contains(o)) {
                for (int j = idx; j < o; ++j)
                    stack.push(j);
            } else break;

            answer++;
            idx = Math.max(idx, o + 1);
        }

        return answer;
    }
}
