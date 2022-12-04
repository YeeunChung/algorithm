package com.company.programmers.bfs;

import java.util.*;

public class Main_퍼즐조각채우기 {

    public static void main(String[] args) {

        int[][] game_board = {{1,1,0,0,1,0}, {0,0,1,0,1,0}, {0,1,1,0,0,1}, {1,1,0,1,1,1}, {1,0,0,0,1,0}, {0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0}, {1,0,1,0,1,0}, {0,1,1,0,1,1}, {0,0,1,0,0,0}, {1,1,0,1,1,0}, {0,1,0,0,0,0}};

        System.out.println(solution(game_board, table));

    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int solution(int[][] game_board, int[][] table) {

        /**
         * table 돌면서 정보 저장하고 돌려놓기...?
         *
         * 1 0 0 1 1 0
         * 1 0 1 0 1 0
         * 0 1 1 0 1 1
         * 0 0 1 0 0 0
         * 1 1 0 1 1 0
         * 0 1 0 0 0 0
         *
         */

        int[][] checkedTable = new int[table.length][table.length]; // table에 번호 매기기
        Map<Integer, List<List<Coordinate>>> puzzleMap = new HashMap<>(); // 퍼즐 정보 저장


        // 1. 퍼즐 저장
        int num = 0;
        for (int i=0; i<table.length; ++i) {
            for (int j=0; j<table.length; ++j) {
                if (table[i][j] == 1 && checkedTable[i][j] == 0) {
                    ++num;
                    checkedTable[i][j] = num;
                    List<Coordinate> puzzle = new ArrayList<>(Arrays.asList(new Coordinate(0, 0)));
                    dfsForStart(i, j, num, i, j, checkedTable, table, puzzle);
                    puzzleMap.put(num, new ArrayList<>(Arrays.asList(puzzle)));
                }
            }
        }

//        for (int[] c: checkedTable)
//            System.out.println(Arrays.toString(c));
//        System.out.println("==============================");

        // 2. 퍼즐 회전 후 저장
        for (int i=0; i<3; ++i) {
            int[][] tempCheckedTable = new int[table.length][table.length];

            // 시계 방향으로 회전
            for (int j=0; j<table.length; ++j) {
                for (int k=0; k<table.length; ++k) {
                    tempCheckedTable[j][k] = checkedTable[table.length - 1 - k][j];
                }
            }

//            for (int[] c: tempCheckedTable)
//                System.out.println(Arrays.toString(c));
//            System.out.println("==============================");

            boolean[][] visited = new boolean[table.length][table.length];
            for (int j=0; j< table.length; ++j) {
                for (int k=0; k< table.length; ++k) {
                    if (tempCheckedTable[j][k] != 0 && ! visited[j][k]) {
                        List<Coordinate> puzzle = new ArrayList<>(Arrays.asList(new Coordinate(0, 0)));
                        visited[j][k] = true;
                        dfsForRotation(j, k, j, k, tempCheckedTable, visited, puzzle);

                        List<List<Coordinate>> puzzleList = puzzleMap.get(tempCheckedTable[j][k]);
                        puzzleList.add(puzzle);
                        puzzleMap.put(tempCheckedTable[j][k], puzzleList);
                    }
                }
            }

            checkedTable = tempCheckedTable;
        }

//        for (int i=1; i<=5; ++i) {
//            List<List<Coordinate>> puzzleList = puzzleMap.get(i);
//
//            System.out.println(i);
//            for (List<Coordinate> puzzles: puzzleList) {
//                System.out.println(puzzles);
//            }
//        }

        // 3. Game Board 돌며 비어있는 칸 찾아서 적합한 퍼즐
        for (int i=0; i< table.length; ++i) {
            for (int j=0; j<table.length; ++j) {
                
            }
        }

        return 0;

    }

    static void dfsForStart(int startX, int startY, int num, int x, int y, int[][] checkedTable, int[][] table, List<Coordinate> puzzle) {

        for (int i=0; i<4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX < table.length && newY >= 0 && newY < table.length
                && checkedTable[newX][newY] == 0 && table[newX][newY] == 1) {
                puzzle.add(new Coordinate(newX-startX, newY-startY));
                checkedTable[newX][newY] = num;
                dfsForStart(startX, startY, num, newX, newY, checkedTable, table, puzzle);
            }
        }

    }

    static void dfsForRotation(int startX, int startY, int x, int y, int[][] table, boolean[][] visited, List<Coordinate> puzzle) {

        for (int i=0; i<4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX < table.length && newY >= 0 && newY < table.length
                    && table[newX][newY] != 0 && ! visited[newX][newY]) {
                puzzle.add(new Coordinate(newX-startX, newY-startY));
                visited[newX][newY] = true;
                dfsForRotation(startX, startY, newX, newY, table, visited, puzzle);
            }
        }

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
    }

}
