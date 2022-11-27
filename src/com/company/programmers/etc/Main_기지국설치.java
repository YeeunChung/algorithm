package com.company.programmers.etc;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main_기지국설치 {

    public static void main(String[] args) {

        int[] stations = {9};
        System.out.println(solution(16, stations, 2));

    }

    public static int solution(int n, int[] stations, int w) {

        /**
         * stations 돌면서
         *
         *             <--------->
         * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
         */
        int[] coverage = new int[n];
        int answer = 0;

        for (int i=0; i<stations.length; ++i) {
            int station = stations[i] - 1;

            coverage[station] = 1;
            for (int j=station+1; j<=station+w; ++j) {
                if (j < n) coverage[j] = 1;
                else break;
            }
            for (int j=station-1; j>=station-w; --j) {
                if (j >= 0) coverage[j] = 1;
                else break;
            }
        }

        System.out.println(Arrays.toString(coverage));

        int start = 0;
        int end = 0;
        int count;
        int cov = 2 * w + 1;

        for (int i=0; i<n; ++i) {
            if (coverage[i] == 0) ++end;
            else {
                count = end - start;
                answer += (count / cov);
                if (count % cov != 0) ++answer;
                start = i+1;
                end = i+1;
            }

            if (i == n-1) {
                count = end - start;
                answer += (count / cov);
                if (count % cov != 0) ++answer;
            }
        }

        return answer;
    }

}
