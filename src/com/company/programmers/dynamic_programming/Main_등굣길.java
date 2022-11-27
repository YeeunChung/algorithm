package com.company.programmers.dynamic_programming;

public class Main_등굣길 {

    public static void main(String[] args) {

        int[][] puddles = {{2, 2}};
        System.out.println(solution(4, 3, puddles));

    }

    public static int solution(int m, int n, int[][] puddles) {

        long[][] dynamic = new long[m][n];
        int[][] puddleInfo = new int[m][n];

        for (int i=0; i<puddles.length; ++i)
            puddleInfo[puddles[i][0]-1][puddles[i][1]-1] = 1;

        dynamic[0][0] = 1;

        for (int i=0; i<m; ++i) {
            for (int j=0; j<n; ++j) {
                if (i==0 && j==0) continue;
                if (puddleInfo[i][j] == 1) continue;

                // 위쪽 좌표
                long upperVal = i-1>=0 && puddleInfo[i-1][j] == 0? dynamic[i-1][j]: 0;
                long leftVal = j-1>=0 && puddleInfo[i][j-1] == 0? dynamic[i][j-1]: 0;

                dynamic[i][j] = (upperVal + leftVal) % 1000000007;
            }
        }

//        for (int i=0; i<dynamic.length; ++i)
//            System.out.println(Arrays.toString(dynamic[i]));

        return (int) dynamic[m-1][n-1];
    }



}
