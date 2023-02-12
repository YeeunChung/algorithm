package com.company.programmers.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_이모티콘할인행사 {

    public static void main(String[] args) {

//        int[][] users = {{40, 10000}, {25, 10000}};
//        int[] emoticons = {7000, 9000};
//        System.out.println(Arrays.toString(solution(users, emoticons))); // result: 1, 5400

        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};

        System.out.println(Arrays.toString(solution(users, emoticons))); // result: 4, 13860

    }

    static List<int[]> discountPolicies = new ArrayList<>();

    public static int[] solution(int[][] users, int[] emoticons) {

        /**
         * 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것
         * 2. 이모티콘 판매액을 최대한 늘리는 것
         *
         * 4^7 = 16384
         * 16384 * 100 = 1638400
         * 견디려나... => 안될듯...;;
         *
         * 그럼 어떻게하나?
         * 완탐 -> 160회로 가능하다고 함...!!
         *
         * 1 1 1 1
         * 1 1 1 2
         * 1 1 1 3
         * 1 1 1 4
         * 1 1 2 1
         * 1 1 2 2
         * 1 1 2 3
         * 1 1 2 4
         * 1 1 3 1
         * ...
         *
         */

        bruteForce(0, emoticons.length, new int[emoticons.length]);

        int emoPlusCntMax = 0;
        int totalValueMax = 0;

        for (int[] policy: discountPolicies) {
            int emoPlusCnt = 0;
            int totalValue =0;

            for (int[] user: users) {
                int value = 0;

                for (int i=0; i<emoticons.length; ++i) {
                    if (policy[i] * 10 >= user[0]) { // user의 할인율 조건을 만족한다면
                        value += emoticons[i] * (1 - 0.1 * policy[i]);
                    }
                }

                if (value >= user[1]) emoPlusCnt++;
                else totalValue += value;
            }

            if (emoPlusCntMax < emoPlusCnt
                || emoPlusCntMax == emoPlusCnt && totalValueMax < totalValue) {
                emoPlusCntMax = emoPlusCnt;
                totalValueMax = totalValue;
            }
        }

        int[] answer = {emoPlusCntMax, totalValueMax};
        return answer;
    }

    static void bruteForce(int idx, int emoLen, int[] policy) {
        if (idx == emoLen) {
            discountPolicies.add(policy.clone());
            return;
        }

        for (int i=1; i<=4; ++i) {
            policy[idx] = i;
            bruteForce(idx + 1, emoLen, policy);
        }
    }

}
