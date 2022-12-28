package com.company.programmers.etc;

import java.util.*;
import java.util.stream.Collectors;

public class Main_부대복귀2 {

    public static void main(String[] args) {

//        int[][] roads = {{1,2},{2,3}};
//        int[] source = {2,3};
//        System.out.println(Arrays.toString(solution(3, roads, source, 1)));

        int[][] roads = {{1,2},{1,4},{2,4},{2,5},{4,5}};
        int[] source = {1,3,5};
        System.out.println(Arrays.toString(solution(5, roads, source, 5)));

    }

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/132266
         *
         * 1차 시도: roads 돌면서 정보 저장하고 bfs로 탐색 => 시간초과 남
         * 2차 시도: dp 써서 저장해뒀다가 돌면서 계산하기 (floyd 알고리즘) => 시간 초과, 메모리 초과
         * 3차 시도: 다익스트라 알고리즘 써보깅...
         * ...
         * 5차 시도: bfs를 거꾸로 한 번만 하기...!
         */
        Map<Integer, List<Integer>> roadMap = new HashMap<>();
        List<Integer> valueList;
        int[] length = new int[n+1]; // index i부터 destination까지의 거리
        int[] answer = new int[sources.length];
        Arrays.fill(length, n+1); // 최댓값으로 우선 채우기
        length[destination] = 0;

        for (int i=0; i< roads.length; ++i) {
            valueList = roadMap.getOrDefault(roads[i][0], new ArrayList<>());
            valueList.add(roads[i][1]);
            roadMap.put(roads[i][0], valueList);

            valueList = roadMap.getOrDefault(roads[i][1], new ArrayList<>());
            valueList.add(roads[i][0]);
            roadMap.put(roads[i][1], valueList);
        }

        valueList = roadMap.getOrDefault(destination, new ArrayList<>());
        Queue<SourceLevel> queue = new LinkedList<>(valueList.stream()
                .map(value -> new SourceLevel(value, 1))
                .collect(Collectors.toList()));

        Set<Integer> set = new HashSet<>(); // 검사한 길 표시
        set.add(destination);

        while (! queue.isEmpty()) {
            SourceLevel sourceLevel = queue.poll();
            int source = sourceLevel.getSource();
            int level = sourceLevel.getLevel();

            if (level < length[source])
                length[source] = level;

            set.add(source);

            valueList = roadMap.getOrDefault(source, new ArrayList<>());
            queue.addAll(valueList.stream()
                    .filter(value -> ! set.contains(value))
                    .map(value -> new SourceLevel(value, level + 1))
                    .collect(Collectors.toList()));
        }

        System.out.println(Arrays.toString(length));

        for (int i=0; i< sources.length; ++i)
            answer[i] = length[sources[i]] == n+1? -1: length[sources[i]];

        return answer;
    }

    public static class SourceLevel {
        private int source;
        private int level;

        public SourceLevel(int source, int level) {
            this.source = source;
            this.level = level;
        }

        public int getSource() {
            return source;
        }

        public int getLevel() {
            return level;
        }
    }

    public static int[] solution_dijstra(int n, int[][] roads, int[] sources, int destination) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/132266
         *
         * 1차 시도: roads 돌면서 정보 저장하고 bfs로 탐색 => 시간초과 남
         * 2차 시도: dp 써서 저장해뒀다가 돌면서 계산하기 (floyd 알고리즘) => 시간 초과, 메모리 초과
         * 3차 시도: 다익스트라 알고리즘 써보깅...
         */
        int[][] dynamic = new int[n+1][n+1];
        int[] answer = new int[sources.length];

        for (int i=0; i<n+1; ++i)
            Arrays.fill(dynamic[i], n+1);

        for (int i=0; i< roads.length; ++i) {
            dynamic[roads[i][0]][roads[i][1]] = 1;
            dynamic[roads[i][1]][roads[i][0]] = 1;
        }
        for (int i=1; i<=n; ++i)
            dynamic[i][i] = 0;

        for (int i=0; i< sources.length; ++i) {
            int source = sources[i];

            if (dynamic[source][destination] == n+1) { // 아직 길이 없는 경우
                for (int j=1; j<=n; ++j) {
                    for (int k=1; k<=n; ++k) {
                        // source: 출발노드, j: 중간 노드, k: 도착 노드
                        if (dynamic[source][k] > dynamic[source][j] + dynamic[j][k]) {
                            dynamic[source][k] = dynamic[source][j] + dynamic[j][k];
                            dynamic[k][source] = dynamic[source][j] + dynamic[j][k];
                        }
                    }
                }
            }

            answer[i] = dynamic[source][destination];
        }

        for (int i=0; i<answer.length; ++i)
            if (answer[i] == n+1) answer[i] = -1;

        return answer;
    }

}
