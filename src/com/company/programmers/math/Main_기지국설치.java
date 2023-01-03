package com.company.programmers.math;

public class Main_기지국설치 {

    public static void main(String[] args) {

//        int[] stations = {4, 11};
//        System.out.println(solution_timeout(11, stations, 1));
//        int[] stations = {9};
//        System.out.println(solution_timeout(16, stations, 2));
        int[] stations = {4};
        System.out.println(solution_timeout(6, stations, 1));

    }

    public static int solution(int n, int[] stations, int w) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/12979
         *
         * 1. 단순하게 coverage 모두 표시 -> 답은 통과하는데 시간 초과 남
         * 2. 커버 안 된 구간 구해서 계산하기
         *  웃겼던 거: 2 * w + 1 을 변수로 저장하니까 시간초과 안 나고, 계속 계산하게 하니까 시간초과 남~!
         **/
        int answer = 0;
        int begin = 1;
        int length = 2 * w + 1;

        for (int i=0; i<stations.length; ++i) {
            if (begin < stations[i] - w)
                answer += (int) Math.ceil((double) (stations[i] - w - begin) / length);
            begin = stations[i] + w + 1;
        }

        if (begin != 1 && begin <= n)
            answer += (int) Math.ceil((double)(n - begin + 1) / length);

        return answer;
    }



    public static int solution_timeout(int n, int[] stations, int w) {
        boolean[] coverage = new boolean[n];
        int answer = 0;

        for (int i=0; i<stations.length; ++i) {
            int mid = stations[i]-1;

            for (int j=mid; j>=0 && j>=mid-w; --j)
                coverage[j] = true;
            for (int j=mid; j<n && j<=mid+w; ++j)
                coverage[j] = true;
        }

//        System.out.println(Arrays.toString(coverage));

        int i=0;
        while (i < n) {
            if (!coverage[i]) {
                ++answer;
                i += (2 * w + 1);
            } else ++i;
        }

        return answer;
    }

}
