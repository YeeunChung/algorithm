package com.company.programmers.bfs;
import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87694
 */

public class Main_아이템줍기 {

    public static void main(String[] args) {

        int[][] rectangle = {{1,1,7,4}, {3,2,5,5}, {4,3,6,9}, {2,6,8,8}};
        System.out.println(solution(rectangle, 1, 3, 7, 8));

    }

    static int[] dx = {0, 0, -2, 2};
    static int[] dy = {-2, 2, 0, 0};
    static int[] ddx = {0, 0, -1, 1};
    static int[] ddy = {-1, 1, 0, 0};
    static int[][] map = new int[104][104]; // 계산하기 쉽게 두 배로 계산, 테두리는 1, 내부는 2
    static boolean[][] visited = new boolean[104][104];
    static int[][] depth = new int[104][104];

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int i=0; i<rectangle.length; ++i) {
            int leftUnderX = rectangle[i][0] * 2;
            int leftUnderY = rectangle[i][1] * 2;
            int rightUpperX = rectangle[i][2] * 2;
            int rightUpperY = rectangle[i][3] * 2;

            for (int j=leftUnderX; j<=rightUpperX; ++j) {
                for (int k=leftUnderY; k<=rightUpperY; ++k) {
                    if (map[j][k] != 2 && (j == leftUnderX || j == rightUpperX || k == leftUnderY || k == rightUpperY)) {
                        map[j][k] = 1;
                    } else
                        map[j][k] = 2;
                }
            }
        }

        visited[characterX * 2][characterY * 2] = true;
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);

        return depth[itemX * 2][itemY * 2];
    }

    static void bfs(int x, int y, int itemX, int itemY) {

        Queue<int[]> queue = new LinkedList<>();
        int[] coord = {x, y};
        queue.add(coord);

        while (!queue.isEmpty()) {
            coord = queue.poll();

            if (coord[0] == itemX && coord[1] == itemY) return;

            for (int i=0; i<4; ++i) {
                int newX = coord[0] + dx[i];
                int newY = coord[1] + dy[i];
                int newDX = coord[0] + ddx[i];
                int newDY = coord[1] + ddy[i];

                if (! visited[newX][newY] && map[newDX][newDY] == 1 && depth[newX][newY] == 0) {
                    visited[newX][newY] = true;
                    depth[newX][newY] = depth[coord[0]][coord[1]] + 1;

                    int[] temp = {newX, newY};
                    queue.add(temp);
                }
            }
        }
    }
}
