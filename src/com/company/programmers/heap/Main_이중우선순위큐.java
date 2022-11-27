package com.company.programmers.heap;

import java.util.*;

public class Main_이중우선순위큐 {

    public static void main(String[] args) {

        String[] operations = {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"};
        System.out.println(Arrays.toString(solution(operations)));

    }

    public static int[] solution(String[] operations) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 오름차순
        PriorityQueue<Integer> revPq = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순
        int[] answer = new int[2];

        for (String operation: operations) {
            String[] opSpl = operation.split(" ");

            if (opSpl[0].equals("I")) {
               pq.add(Integer.parseInt(opSpl[1]));
               revPq.add(Integer.parseInt(opSpl[1]));
            } else if (opSpl[0].equals("D")) {
                if (pq.isEmpty()) continue;

                if (opSpl[1].equals("1")) { // 최댓값 삭제
                    Integer num = revPq.poll();
                    pq.remove(num);
                } else { // opSpl[1].equals("-1"), 최솟값 삭제
                    Integer num = pq.poll();
                    revPq.remove(num);
                }
            }
        }

        if (pq.isEmpty()) {
            answer[0] = 0; answer[1] = 0;
            return answer;
        } else {
            answer[0] = revPq.peek(); answer[1] = pq.peek();
            return answer;
        }
    }

}
