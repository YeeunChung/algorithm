package com.company.programmers.dfs;

import java.util.*;

public class Main_퍼즐조각채우기 {

    public static void main(String[] args) {

        int[][] game_board = {{1,1,0,0,1,0}, {0,0,1,0,1,0}, {0,1,1,0,0,1}, {1,1,0,1,1,1}, {1,0,0,0,1,0}, {0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0}, {1,0,1,0,1,0}, {0,1,1,0,1,1}, {0,0,1,0,0,0}, {1,1,0,1,1,0}, {0,1,0,0,0,0}};
//        int[][] game_board = {{0,0,0}, {1,1,0}, {1,1,1}};
//        int[][] table = {{1,1,1}, {1,0,0}, {0,0,0}};

        System.out.println(solution(game_board, table));

    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int solution(int[][] game_board, int[][] table) {

        /**
         *
         * https://school.programmers.co.kr/learn/courses/30/lessons/84021
         *
         * table을 돌리지 말고 game board를 돌려보자!!!
         *
         */

        int count = 0;

        boolean[][] visited = new boolean[table.length][table.length];
        List<List<Coordinate>> puzzleList = new ArrayList<>();

        // 1. 퍼즐 저장
        for (int i=0; i<table.length; ++i) {
            for (int j=0; j<table.length; ++j) {
                if (table[i][j] == 1 && ! visited[i][j]) {
                    visited[i][j] = true;
                    List<Coordinate> puzzle = new ArrayList<>(Arrays.asList(new Coordinate(0, 0)));
                    dfs(i, j, i, j, table, visited, puzzle, 1);
                    puzzleList.add(puzzle);
                }
            }
        }

        System.out.println("초기 상태: ");
        for (int i=0; i<table.length; ++i) {
            System.out.println(Arrays.toString(game_board[i]));
        }
        System.out.println("============================");

        // 2. Game Board 돌리며 비어있는 칸 찾아서 적합한 퍼즐 찾기
        for (int idx = 0; idx < 4; ++idx) {

            // Game Board 돌리기
            if (idx != 0) {
                int[][] rotatedGameBoard = new int[table.length][table.length];

                for (int i=0; i<table.length; ++i) {
                    for (int j=0; j<table.length; ++j) {
                        rotatedGameBoard[i][j] = game_board[table.length - 1 - j][i];
                    }
                }

                game_board = rotatedGameBoard;

                System.out.println("회전 후: ");
                for (int i=0; i<table.length; ++i) {
                    System.out.println(Arrays.toString(game_board[i]));
                }
                System.out.println("============================");
            }

            visited = new boolean[table.length][table.length];

            // 현재 상태에서 들어가는 블록 찾기
            for (int i=0; i< table.length; ++i) {
                for (int j=0; j<table.length; ++j) {
                    if (game_board[i][j] == 0 && ! visited[i][j]) {
                        List<Coordinate> blank = new ArrayList<>(Arrays.asList(new Coordinate(0, 0)));
                        visited[i][j] = true;
                        dfs(i, j, i, j, game_board, visited, blank, 0);

                        for (List<Coordinate> puzzle: puzzleList) {
                            if (isPuzzleMatch(blank, puzzle)) {
                                count += puzzle.size();

                                // puzzle 사용했으므로 제외
                                puzzleList.remove(puzzle);
                                // blank 채워졌으므로 다시 채워넣기
                                for (Coordinate coordinate: blank)
                                    game_board[i + coordinate.x][j + coordinate.y] = 1;

                                break;
                            }
                        }
                    }
                }
            }

            System.out.println("처리 후: ");
            for (int i=0; i<table.length; ++i) {
                System.out.println(Arrays.toString(game_board[i]));
            }
            System.out.println("============================");
        }

        for (List<Coordinate> puzzle: puzzleList)
            System.out.println(puzzle);

        return count;

    }

    static void dfs(int startX, int startY, int x, int y, int[][] table, boolean[][] visited, List<Coordinate> puzzle, int checkNum)  {

        for (int i=0; i<4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX < table.length && newY >= 0 && newY < table.length
                    && table[newX][newY] == checkNum && ! visited[newX][newY]) {
                puzzle.add(new Coordinate(newX-startX, newY-startY));
                visited[newX][newY] = true;
                dfs(startX, startY, newX, newY, table, visited, puzzle, checkNum);
            }
        }

    }

    // 퍼즐 두 조각이 서로 일치하는지 확인
    static boolean isPuzzleMatch(List<Coordinate> puzzle1, List<Coordinate> puzzle2) {
        if (puzzle1.size() != puzzle2.size()) return false;

        Coordinate[] puzzleArr1 = puzzle1.toArray(new Coordinate[puzzle1.size()]);
        Coordinate[] puzzleArr2 = puzzle2.toArray(new Coordinate[puzzle2.size()]);

        for (int i=0; i<puzzleArr1.length; ++i) {
            if (! puzzleArr1[i].equals(puzzleArr2[i])) return false;
        }

        return true;
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            Coordinate coord = (Coordinate) obj;
            if (coord.x == this.x && coord.y == this.y) return true;
            else return false;
        }
    }

}
