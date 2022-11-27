package com.company.programmers.hash;

import java.util.HashMap;
import java.util.Map;

public class Main_롤케이크 {

    public static void main(String[] args) {

        int[] topping = {1, 2, 3, 1};
        System.out.println(solution(topping));

    }

    public static int solution(int[] topping) {

        /**
         * link: https://school.programmers.co.kr/learn/courses/30/lessons/132265#
         * 두 조각으로 자를 것
         * 그냥 처음부터 훑으면서 철수한테 다 주고 하나씩 내려오면서 철수한테는 뺏고 동생한테는 추가하는 게 나을 것 같은데? ㅋㅋㅋㅋ
         */
        int answer = 0;

        Map<Integer, Integer> toppingChul = new HashMap<>();
        Map<Integer, Integer> toppingBro = new HashMap<>();

        for (int t: topping)
            toppingChul.put(t, toppingChul.getOrDefault(t, 0) + 1);

        int idx = topping.length - 1;

        while (idx >= 0) {
            // 철수한테 지우기
            if (toppingChul.get(topping[idx]) == 1) toppingChul.remove(topping[idx]);
            else toppingChul.put(topping[idx], toppingChul.get(topping[idx]) - 1);

            // 동생한테 추가하기
            toppingBro.put(topping[idx], toppingBro.getOrDefault(topping[idx], 0) + 1);

            System.out.println(idx + " " + toppingChul + " " + toppingBro);

            if (toppingChul.size() == toppingBro.size()) ++answer;
            else if (toppingChul.size() < toppingBro.size()) break; /// 뒤집혀서 이제 검사할 필요 X
            --idx;
        }

        return answer;
    }

}
