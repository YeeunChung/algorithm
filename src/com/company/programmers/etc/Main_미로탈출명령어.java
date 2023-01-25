package com.company.programmers.etc;

import java.util.Arrays;

public class Main_미로탈출명령어 {

    public static void main(String[] args) {

        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
//        System.out.println(solution(2, 2, 1, 1, 2, 2, 2));
//        System.out.println(solution(3, 3, 1, 2, 3, 3, 4));

    }

    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] alp = {'d', 'l', 'r', 'u'};
    static String shortest;

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/150365
         * 왼쪽 오른쪽: rl
         * 위쪽 아래쪽: ud
         *
         * dlru 이 순서!
         * du
         * lr
         *
         * 답: dll, dllrl
         */

        

        // 홀수만큼 남으면 impossible
        if (shortest.length() > k || (k - shortest.length()) % 2 != 0) return "impossible1";

        // 짝수면 계산
        int remain = (k - shortest.length()) / 2; // remain * 2만큼 더 추가해줘야 한다.
        int[] count = new int[4]; // dlru count
        for (int i=0; i<shortest.length(); ++i) {
            switch (shortest.charAt(i)) {
                case 'd': count[0]++; break;
                case 'l': count[1]++; break;
                case 'r': count[2]++; break;
                case 'u': count[3]++; break;
            }
        }

        int currentX = x-1;
        int currentY = y-1;
        String answer = "";

        for (int i=0; i<k; ++i) {
            int newX = -1, newY = -1;

            for (int j=0; j<4; ++j) {
                newX = currentX + dx[j];
                newY = currentY + dy[j];

                // 이동할 수 있으면 이동
                if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length) {
                    if (count[j] > 0) {
                        count[j]--;
                        answer.concat(String.valueOf(alp[j]));
                        break;
                    } else {
                        if (remain == 0) continue;

                        switch (j) {
                            case 0: count[3]++; break;
                            case 1: count[2]++; break;
                            case 2: count[1]++; break;
                            case 3: count[0]++; break;
                        }

                        remain--;
                        answer.concat(String.valueOf(alp[j]));
                        break;
                    }
                }
            }

            if (newX != -1) { // 다음 칸으로 넘어갔다
                currentX = newX;
                currentY = newY;
            } else { // 어느 방향으로도 이동할 수 없었다
                return "impossible2";
            }
        }

        return answer;
    }

}
