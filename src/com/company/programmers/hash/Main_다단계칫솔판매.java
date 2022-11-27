package com.company.programmers.hash;

import java.util.*;

public class Main_다단계칫솔판매 {

    public static void main(String[] args) {

//        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
//        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
//        String[] seller = {"young", "john", "tod", "emily", "mary"};
//        int[] amount = {12, 4, 2, 5, 10};
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"sam", "emily", "jaimie", "edward"};
        int[] amount = {2, 3, 5, 4};

        System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));

    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        /**
         * Map을 만들되, 밑에서 위로 올라가는 구조로 만들어야 할 것 같은데???
         * ex) mary가 edward를 포섭했으면, edward -> mary 이런 식...
         * 근데
         */
        Map<String, String> referralMap = new HashMap<>();
        Map<String, Integer> sellCountMap = new HashMap<>();
        int[] answer = new int[enroll.length];

        for (int i=0; i<referral.length; ++i) {
            String slave = enroll[i];
            String master = referral[i];
            referralMap.put(slave, master);
        }

        System.out.println(referralMap);

        for (int i=0; i<seller.length; ++i) {
            String slave = seller[i];
            String master = referralMap.get(slave);
            int slaveMoney = amount[i] * 90;
            int masterMoney = amount[i] * 10;

            sellCountMap.put(slave, sellCountMap.getOrDefault(slave, 0) + slaveMoney);
            sellCountMap.put(master, sellCountMap.getOrDefault(master, 0) + masterMoney);

            while (referralMap.containsKey(master) && masterMoney != 0) {
                slave = master;
                master = referralMap.get(slave);

                slaveMoney = (int) (masterMoney * 0.9);
                masterMoney = (int) (masterMoney * 0.1);

                sellCountMap.put(slave, sellCountMap.getOrDefault(slave, 0) - masterMoney);
                sellCountMap.put(master, sellCountMap.getOrDefault(master, 0) + masterMoney);
            }

            System.out.println(sellCountMap);
        }

        for (int i=0; i<enroll.length; ++i)
            answer[i] = sellCountMap.getOrDefault(enroll[i], 0);

        return answer;
    }

}
