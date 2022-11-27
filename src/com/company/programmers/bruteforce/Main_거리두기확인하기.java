package com.company.programmers.bruteforce;

import java.util.Arrays;

public class Main_거리두기확인하기 {

    public static void main(String[] args) {

        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(solution(places)));

    }

    public static int[] solution(String[][] places) {

        /**
         * [P, O, O, O, P]
         * [O, X, X, O, X]
         * [O, P, X, P, X]
         * [O, O, X, O, X]
         * [P, O, X, X, P]
         *
         * POOOP
         * OXXOX
         * OXXPX
         * OPXOX
         * PXXXP
         */

        int[] answer = new int[places.length];

        for (int idx =0; idx < places.length; ++idx) {
            Character[][] charArray = new Character[5][5];
            boolean flag = true;

            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 5; ++j) {
                    charArray[i][j] = places[idx][i].charAt(j);
                }
            }

            for (int i=0; i<5; ++i) {
                for (int j = 0; j < 5; ++j) {
                    if (charArray[i][j] == 'P') {
                        // 0. 오른쪽, 아래 확인
                        if (i+1 < 5 && charArray[i+1][j] == 'P') {
                            flag = false; break;
                        }
                        if (j+1 < 5 && charArray[i][j+1] == 'P') {
                            flag = false; break;
                        }
                        // 1. 오른쪽 두 칸 확인
                        if (i+2 < 5 && charArray[i+2][j] == 'P' && charArray[i+1][j] != 'X') {
                            flag = false; break;
                        }
                        // 2. 아래 두 칸 확인
                        if (j+2 < 5 && charArray[i][j+2] == 'P' && charArray[i][j+1] != 'X') {
                            flag = false; break;
                        }
                        // 3. 위쪽 대각선 확인
                        if (i-1 >= 0 && j+1 < 5 && charArray[i-1][j+1] == 'P'
                            && (charArray[i-1][j] != 'X' || charArray[i][j+1] != 'X')) {
                            flag = false; break;
                        }
                        // 4. 아래쪽 대각선 확인
                        if (i+1 < 5 && j+1 < 5 && charArray[i+1][j+1] == 'P'
                            && (charArray[i+1][j] != 'X' || charArray[i][j+1] != 'X')) {
                            flag = false; break;
                        }
                    }
                }
                if (! flag) break;
            }

            if (flag) answer[idx] = 1;
            else answer[idx] = 0;
        }

        return answer;
    }

}
