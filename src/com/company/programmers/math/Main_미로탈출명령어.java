package com.company.programmers.math;

public class Main_미로탈출명령어 {

    public static void main(String[] args) {

//        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
//        System.out.println(solution(2, 2, 1, 1, 2, 2, 2));
        System.out.println(solution(3, 3, 1, 2, 3, 3, 4));

    }

    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] alp = {'d', 'l', 'r', 'u'};

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
         *
         * . . . .
         * . . S .
         * E . . .
         *
         * 1 2 0 0
         */

        int[] count = new int[4]; // dlru count
        String answer = "";

        int width = c - y;
        int height = r - x;

        if (width > 0) count[2] += width;
        else count[1] -= width;
        if (height > 0) count[0] += height;
        else count[3] -= height;

        int shortest = Math.abs(width) + Math.abs(height);

        // 홀수만큼 남으면 impossible
        if (shortest > k || (k - shortest) % 2 != 0) return "impossible";

        // 짝수면 계산
        int remain = (k - shortest) / 2; // remain * 2만큼 더 추가해줘야 한다.

        int currentX = x-1;
        int currentY = y-1;

        for (int i=0; i<k; ++i) {
            int newX = -1, newY = -1;

            for (int j=0; j<4; ++j) {
                newX = currentX + dx[j];
                newY = currentY + dy[j];

                // 이동할 수 있으면 이동
                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    if (count[j] > 0) {
                        count[j]--;
                        answer = answer.concat(String.valueOf(alp[j]));
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
                        answer = answer.concat(String.valueOf(alp[j]));
                        break;
                    }
                }
            }

            if (newX != -1) { // 다음 칸으로 넘어갔다
                currentX = newX;
                currentY = newY;
            } else { // 어느 방향으로도 이동할 수 없었다
                return "impossible";
            }
        }

        return answer;
    }

}
