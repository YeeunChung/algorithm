package com.company.programmers.dfs;

import java.util.*;

public class Main_부대복귀 {

    public static void main(String[] args) {

        int[][] roads = {{1, 2}, {1,4},{2,4},{2,5},{4,5}};
        int[] sources = {1, 3, 5};
//        int[][] roads = {{1,2},{2,3}};
//        int[] sources = {2,3};
        System.out.println(Arrays.toString(solution(5, roads, sources, 5)));

    }

    static Map<Integer, List<Integer>> map;
    static int[] dynamic;

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {

        /**
         * roads
         * sources => destination
         * 1->2,4
         * 2->4,5
         * 4->5
         * 아니면 그냥 뒤에부터 카운트하는 건 어때??? => 이게 맞는 듯...?
         * 근데 시간 초과 남... 너무 다 계산해서 그런 것 같기도? ㅠㅠㅠㅠ
         * => sources 다 계산하고 나면 멈추기.../??? 가능한가?ㄹ
         */

        map = new HashMap<>();
        dynamic = new int[n+1];
        Arrays.fill(dynamic, -1);
        int[] answer = new int[sources.length];

        for (int i=0; i<roads.length; ++i) {
            if (map.containsKey(roads[i][0])) {
                List<Integer> list = map.get(roads[i][0]);
                list.add(roads[i][1]);
                map.put(roads[i][0], list);
            } else {
                map.put(roads[i][0], new ArrayList<>(Arrays.asList(roads[i][1])));
            }

            if (map.containsKey(roads[i][1])) {
                List<Integer> list = map.get(roads[i][1]);
                list.add(roads[i][0]);
                map.put(roads[i][1], list);
            } else {
                map.put(roads[i][1], new ArrayList<>(Arrays.asList(roads[i][0])));
            }
        }
        System.out.println(map);

        dynamic[destination] = 0;
        dfs(destination, 0);
        System.out.println(Arrays.toString(dynamic));

        for (int i=0; i< sources.length; ++i)
            answer[i] = dynamic[sources[i]];

        return answer;
    }

    static void dfs (int src, int cnt) {

        if (! map.containsKey(src)) return;
        List<Integer> nextList = map.get(src);

        ++cnt;
        for (Integer next: nextList) {
            if (dynamic[next] == -1)
                dynamic[next] = cnt;
            else if (dynamic[next] > cnt)
                dynamic[next] = cnt;
            else continue;

            System.out.println(next + " " + Arrays.toString(dynamic));
            dfs(next, cnt);
        }

    }

}
