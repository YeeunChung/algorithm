package com.company.programmers.math;

public class Main_마법의엘리베이터 {

    public static void main(String[] args) {

        System.out.println(solution(16)); // 6
        System.out.println(solution(2554)); // 16
        System.out.println(solution(100000000)); // 1
        System.out.println(solution(56)); // 9
        System.out.println(solution(999)); // 2
        System.out.println(solution(156)); // 4 (160) => 8 (200)
        System.out.println(solution(95)); // 반례 찾음

    }

    public static int solution(int storey) {
        /**
         * 현재 층 수에 버튼이 적혀 있는 값을 더한 층으로 이동
         * 0층이 가장 아래층
         * 마법의 돌의 최소 개수 구하기...
         *
         * 5보다 작거나 같으면 버리는 게 이득, 5보다 크면 더하는 게 이득
         * 그런데 반례) 95 => 더하는 게 이득임...!
         * 따라서 1의 자리가 5일 때 10의 자리도 검사해서 5보다 작으면 버리고, 5보다 크면 더하는 게 이득!
         */

        int surplus = storey % 10;
        int value = storey / 10;
        int answer = 0;


        while (! (surplus == 0 && value == 0)) {
            if (surplus < 5 || (surplus == 5 && value % 10 < 5)) {
                answer += surplus;
            }
            else {
                answer += 10 - surplus;
                value ++;
            }
            surplus = value % 10;
            value = value / 10;
        }

        return answer;
    }

}
