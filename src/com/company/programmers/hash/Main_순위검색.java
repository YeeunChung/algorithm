package com.company.programmers.hash;

import java.util.*;

public class Main_순위검색 {

    public static void main(String[] args) {

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(Arrays.toString(solution(info, query)));

    }

    public static int[] solution(String[] info, String[] query) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/72412
         * 교집합의 개념을 어떻게 쉽게 구현하느냐??? 가 아니라 map의 야매적인 활용...
         */

        Map<String, List<Integer>> infoScoresMap = new HashMap<>();
        int[] answer = new int[query.length];

        for (int i=0; i<info.length; ++i) {
            String[] spec = info[i].split(" ");

            // -와 입력값 번갈아가면서 더하기... 어떻게 하지???
            List<String> keyList = new ArrayList<>();

            keyList.add(spec[0]);
            keyList.add("-");

            for (int j=1; j<=3; ++j) {
                List<String> tempKeyList = new ArrayList<>();

                for (String key: keyList) {
                    String newKey = key + spec[j];
                    tempKeyList.add(newKey);
                    newKey = key + "-";
                    tempKeyList.add(newKey);
                }

                keyList = tempKeyList;
            }

            for (String key: keyList) {
                List<Integer> tempList = infoScoresMap.getOrDefault(key, new ArrayList<>());
                tempList.add(Integer.parseInt(spec[4]));
                infoScoresMap.put(key, tempList);
            }

//            System.out.println(infoScoresMap);
//            System.out.println(infoScoresMap.size());
        }

        /** Value에 의한 정렬 */
        for (String key: infoScoresMap.keySet())
            Collections.sort(infoScoresMap.get(key));

        /** 이분탐색 */
        for (int i=0; i< query.length; ++i) {
            String[] qSpl = query[i].split(" ");
            String key = qSpl[0] + qSpl[2] + qSpl[4] + qSpl[6];
            int score = Integer.parseInt(qSpl[7]);

            List<Integer> valueList = infoScoresMap.getOrDefault(key, new ArrayList<>());
//            System.out.println(Arrays.toString(sortedArray));

            answer[i] = binarySearch(valueList, score);
        }

        return answer;
    }

    public static int binarySearch(List<Integer> valueList, int value) {

        int low = 0, high = valueList.size() - 1, mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (valueList.get(mid) < value)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return valueList.size() - low;
    }

}
