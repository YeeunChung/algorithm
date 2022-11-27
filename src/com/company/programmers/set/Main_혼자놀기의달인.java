package com.company.programmers.set;

import java.util.*;

public class Main_혼자놀기의달인 {

    public static void main(String[] args) {

        int[] cards = {8,6,3,7,2,5,1,4};
        System.out.println(solution(cards));

    }

    public static int solution(int[] cards) {
        /**
         * 뭔가 처음부터 세면서...? list에 넣기?
         * set 찾기인가? 그래서 Set 중에서 가장 큰 놈 두 개 가져다가 곱하기
         * Set은 어떻게 찾나? => 말 그대로 Set에 넣기?
         */
        List<Set<Integer>> setList = new ArrayList<>();
        Set<Integer> totalSet = new HashSet<>();

        for (int i=0; i<cards.length; ++i) {
            if (totalSet.contains(i+1)) continue;

            Set<Integer> newSet = new HashSet<>();
            newSet.add(i+1);
            totalSet.add(i+1);

            int idx = i;
            while (cards[idx] != i + 1) {
                newSet.add(cards[idx]);
                totalSet.add(cards[idx]);
                idx = cards[idx] - 1;
            }

            setList.add(newSet);
        }

        // set size가 큰 순서대로 정렬
        setList.sort((o1, o2) -> o2.size() - o1.size());

        System.out.println(setList);

        if (setList.get(0).size() == cards.length) return 0;
        return setList.get(0).size() * setList.get(1).size();
    }

}
