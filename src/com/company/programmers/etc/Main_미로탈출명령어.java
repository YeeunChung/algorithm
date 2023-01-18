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
    static Character[][] map;
    static String shortest;

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {

        /**
         * https://school.programmers.co.kr/learn/courses/30/lessons/150365
         *
         * (x, y) -> (r, c)
         *
         * . . . .
         * . . S .
         * E . . .
         *
         * 왼쪽 오른쪽: rl
         * 위쪽 아래쪽: ud
         *
         * dlru 이 순서!
         * du
         * lr
         *
         * 답: dll, dllrl
         *
         * du
         * lr
         * 1 <= k <= 2500
         *
         * l
         *
         */

        map = new Character[n][m];

        for (Character[] row: map)
            Arrays.fill(row, '.');
        map[x-1][y-1] = 'S';
        map[r-1][c-1] = 'E';

        boolean[][] visited = new boolean[n][m];

        dfs(x-1, y-1, visited, "");
        System.out.println(shortest);

        // 홀수만큼 남으면 impossible
        if (shortest.length() < k || (k - shortest.length()) % 2 != 0) return "impossible";

        // 짝수면 계산
        int remain = (k - shortest.length()) / 2; // remain * 2만큼 더 추가해줘야 한다.

        return "even";
    }

    // k 상관 없이 최단 이동 찾기
    static void dfs(int x, int y, boolean[][] visited, String name) {

        if (map[x][y] == 'E') {
            if (shortest == null) shortest = name;
            else shortest = name.length() < shortest.length()? name: shortest;
            return;
        }

        for (int i=0; i<4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length && ! visited[newX][newY]) {
                visited[newX][newY] = true;
                String newName = name.concat(String.valueOf(alp[i]));
                dfs(newX, newY, visited, newName);
                visited[newX][newY] = false;
            }
        }
    }

}
