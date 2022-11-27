package com.company.programmers.greedy;

import java.util.*;

public class Main_단속카메라 {

    public static void main(String[] args) {

        int[][] routes = {{-20,-15},{-18,15},{-18,3},{-14,-5},{-18,-13},{-5,-3}};
        System.out.println(solution(routes));

    }

    public static int solution(int[][] routes) {

        /**
         * -20   -15
         *   -18     -13
         *   -18                 3
         *   -18                          15
         *         -14    -5
         *                -5  -3
         *
         *  근데 greedy가 어떻게 하는 거더라...?
         *  작은 순으로 정렬한 후 해당 상황에서 제일 큰 값만 비교?
         *  안 되네~~
         */

        Stack<Integer> cameraStack = new Stack<>();
        List<Route> routeList = new ArrayList<>();

        for (int[] route: routes)
            routeList.add(new Route(route[0], route[1]));

        routeList.sort((o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });

        for (Route route: routeList) {
            // 맨 처음에는 그냥 넣기
            if (cameraStack.isEmpty()) {
                cameraStack.add(route.end);
                continue;
            }

            int camera = cameraStack.peek();

            if (camera >= route.start && camera <= route.end)
                continue;

            if (camera > route.end) {
                cameraStack.pop();
                cameraStack.add(route.end);
            } else {
                cameraStack.add(route.end);
            }

        }

        return cameraStack.size();
    }

    static public class Route {
        int start;
        int end;

        public Route(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Route{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    // ------------------ 더 간단한 풀이 -----------------
    public int solution2(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]); // 맨 끝 값으로 정렬
        int answer = 0;
        int lastCam = Integer.MIN_VALUE;

        for (int[] route: routes) {
            if (lastCam < route[0]) { // 마지막 캠이 내 지금 거에 해당되는지 검사,, 안 되면 업데이트!
                ++answer;
                lastCam = route[1];
            }
        }

        return answer;
    }

}
