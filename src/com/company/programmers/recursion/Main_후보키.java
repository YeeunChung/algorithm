package com.company.programmers.recursion;

import java.util.*;
import java.util.stream.Collectors;

public class Main_후보키 {

    public static void main(String[] args) {

        String[][] relation = {{"100","ryan","music","2"}, {"200","apeach","math","2"}, {"300","tube","computer","3"}, {"400","con","computer","4"}, {"500","muzi","music","3"}, {"600","apeach","music","2"}};
//        String[][] relation = {
//                {"1", "양", "늑", "양"},
//                {"1", "양", "늑", "1"},
//                {"2", "늑", "양", "양"}};
        System.out.println(solution(relation));

    }

    public static int solution(String[][] relation) {
        /**
         *
         * https://school.programmers.co.kr/learn/courses/30/lessons/42890
         *
         * Combination을 구하고 구한 조합이 Candidate Key가 될 수 있는지 검사
         *  이 때, 이 전 turn (예를 들어 8C2라면, 8C1) 에 candidate key로 판명된 조합을 포함한다면 검사 X
         *
         */

        List<String> keys = new ArrayList<>(); // candidate key를 저장할 list

        for (int i=1; i<=relation[0].length; ++i) {
            /** 1. 가능한 조합 모두 구하기 */
            List<String> newKeys = new ArrayList<>(); // 가능한 모든 조합을 저장할 list
            combination(relation[0].length, i, 0, "", keys, newKeys);

            /** 2. 가능한 조합들에 대해서 candidate key가 될 수 있는지 조사하기 */
            keys.addAll(newKeys.stream().filter(newKey -> isCandidateKey(relation, newKey))
                    .collect(Collectors.toList()));
        }

        return keys.size();
    }

    /**
     * 조합 구하기. 단, 현재 구한 조합의 부분 집합이 이미 candidate key로 판명되었다면 return
     * @param n
     * @param m
     * @param idx
     * @param s
     * @param keys
     * @param newKeys
     */
    public static void combination(int n, int m, int idx, String s, List<String> keys, List<String> newKeys) {

        if (s.length() == m) {
            // 이전 turn에 이미 조사된 key가 있는지 검사하기
            for (String key: keys) {
                if (isBContainsA(key, s))
                    return;
            }
            newKeys.add(s);
            return;
        }

        for (int i=idx; i<n; ++i) {
            String newS = s.concat(Integer.toString(i));
            combination(n, m, i+1, newS, keys, newKeys);
        }

    }

    /**
     * A string의 모든 char 값들이 B string에 포함되어 있는지 검사
     * @param a
     * @param b
     * @return
     */
    public static boolean isBContainsA(String a, String b) {
        List<Character> aCharList = a.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> bCharList = b.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        if (aCharList.stream().allMatch(aChar -> bCharList.contains(aChar))) return true;
        return false;
    }

    /**
     * 조합이 candidate key인지 검사
     * @param relation
     * @param combi
     * @return
     */
    public static boolean isCandidateKey(String[][] relation, String combi) {

        Set<String> set = new HashSet<>();

        for (int i=0; i< relation.length; ++i) {
            String s = "";
            for (int j=0; j<combi.length(); ++j) {
                s = s.concat(relation[i][Integer.parseInt(String.valueOf(combi.charAt(j)))] + "-");
            }

            if (set.contains(s)) return false;
            set.add(s);
        }

        return true;
    }

}
