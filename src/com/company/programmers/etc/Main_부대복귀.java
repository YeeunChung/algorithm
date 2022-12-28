package com.company.programmers.etc;

import java.util.*;
import java.util.stream.Collectors;

public class Main_부대복귀 {

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
         * 3차 시도: 다익스트라 알고리즘 써보려다가 갑자기 bfs 탐색 다시 도전 => 시간 초과 (4개)
         * 4차 시도: 다익스트라 알고리즘 진짜진짜 써보기
         */

        Map<Integer, List<Integer>> roadMap = new HashMap<>(); // source, dest list
        List<Integer> valueList;
        int[] answer = new int[sources.length];
        int[] dynamic = new int[n+1];
        Arrays.fill(answer, -1);
        Arrays.fill(answer, -1);

        for (int i=0; i<roads.length; ++i) {
            valueList = roadMap.getOrDefault(roads[i][0], new ArrayList<>());
            valueList.add(roads[i][1]);
            roadMap.put(roads[i][0], valueList);

            if (roads[i][1] == destination) dynamic[roads[i][0]] = 1;

            valueList = roadMap.getOrDefault(roads[i][1], new ArrayList<>());
            valueList.add(roads[i][0]);
            roadMap.put(roads[i][1], valueList);

            if (roads[i][0] == destination) dynamic[roads[i][1]] = 1;
        }

//        Set<Integer> keySet = roadMap.keySet();
//        for (int key: keySet)
//            System.out.println(key + " " + roadMap.get(key));

        for (int i=0; i<sources.length; ++i) {
            if (sources[i] == destination) {
                answer[i] = 0; continue;
            }
            if (! roadMap.containsKey(sources[i]))
                continue;

            valueList = roadMap.get(sources[i]);
            if (valueList.contains(destination)) {
                answer[i] = 1;
            } else {
                Queue<SourceLevel> queue = new LinkedList<>(valueList.stream()
                        .map(value -> new SourceLevel(value, 2))
                        .collect(Collectors.toList()));

                while (! queue.isEmpty()) {
                    SourceLevel sourceLevel = queue.poll();
                    valueList = roadMap.getOrDefault(sourceLevel.getSource(), new ArrayList<>());

                    if (valueList.contains(destination)) {
                        answer[i] = sourceLevel.getLevel(); break;
                    } else {
                        queue.addAll(roadMap.getOrDefault(sourceLevel.getSource(), new ArrayList<>()).stream()
                                .map(value -> new SourceLevel(value, sourceLevel.level + 1))
                                .collect(Collectors.toList()));
                    }
                }
            }
        }

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

        public void setSource(int source) {
            this.source = source;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "SourceLevel{" +
                    "source=" + source +
                    ", level=" + level +
                    '}';
        }
    }


    public static int[] solution_bfs(int n, int[][] roads, int[] sources, int destination) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/132266
         *
         * 1차 시도: roads 돌면서 정보 저장하고 bfs로 탐색 => 시간초과 남
         * 2차 시도: dp 써서 저장해뒀다가 돌면서 계산하기 (floyd 알고리즘) => 시간 초과, 메모리 초과
         * 3차 시도: 다익스트라 알고리즘 써보려다가 갑자기 bfs 탐색 다시 도전 => 시간 초과 (4개)
         * 4차 시도: 다익스트라 알고리즘 진짜진짜 써보기
         */

        int[] answer = new int[sources.length];
        int[][] dynamic = new int[n+1][n+1]; // 길 정보 저장
        for (int i=0; i< dynamic.length; ++i)
            Arrays.fill(dynamic[i], n + 1); // 무한대로 저장
        for (int i=1; i<=n; ++i)
            dynamic[i][i] = 0;

        // 정보 저장
        for (int i=0; i< roads.length; ++i) {
            dynamic[roads[i][0]][roads[i][1]] = 1;
            dynamic[roads[i][1]][roads[i][0]] = 1;
        }

        for (int i=0; i<sources.length; ++i) {
            if (dynamic[sources[i]][destination] != n + 1) { // 자기 자신이거나 목적지 한 큐에 갈 수 있으면 바로 저장
                answer[i] = dynamic[sources[i]][destination]; continue;
            }

            for (int j=1; j<=n; ++j) {
                if (j != i && dynamic[j])
            }
        }
    }


    public static int[] solution_floyd(int n, int[][] roads, int[] sources, int destination) {

        int[] answer = new int[sources.length];
        int[][] dynamic = new int[n+1][n+1]; // 길 정보 저장
        for (int i=0; i< dynamic.length; ++i)
            Arrays.fill(dynamic[i], n + 1); // 무한대로 저장
        for (int i=1; i<=n; ++i)
            dynamic[i][i] = 0;

        // 정보 저장
        for (int i=0; i< roads.length; ++i) {
            dynamic[roads[i][0]][roads[i][1]] = 1;
            dynamic[roads[i][1]][roads[i][0]] = 1;
        }

        // 돌면서 최소 거리 찾기
        for (int i=1; i<=n; ++i) {
            for (int j=1; j<=n; ++j) {
                for (int k=1; k<=n; ++k) {
                    if (dynamic[i][k] + dynamic[k][j] < dynamic[i][j])
                        dynamic[i][j] = dynamic[i][k] + dynamic[k][j];
                }
            }
        }

        // 정답 넣어주기
        for (int i=0; i< sources.length; ++i)
            answer[i] = dynamic[sources[i]][destination] == n+1? -1 : dynamic[sources[i]][destination];

        return answer;

    }
}
