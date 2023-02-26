package com.company.programmers.etc;

public class Main_택배배달과수거하기 {

    public static void main(String[] args) {

//        int[] deliveries = {1, 0, 3, 1, 2};
//        int[] pickups = {0, 3, 0, 4, 0};
//        System.out.println(solution(4, 5, deliveries, pickups)); // 16

        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
        int[] pickups = {0, 2, 0, 1, 0, 2, 0};
        System.out.println(solution(2, 7, deliveries, pickups)); // 30

    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        /**
         * 항상 가득 실으면 안 됨... 실어서 가져오는 것도 생각해야 함!!!
         * 맨 뒤부터 배달 / 수거 개수 count하면서 와야 함... 만약에
         * 1/0 0/3 3/0 1/4 2/4 이었으면.. 2개만 실었어야겠고
         * ,,,,,,,,,,,,,,, 2/3 이었으면... 3개 실었어도 됐겠지...?
         * 이걸 어떻게 count하지? 실어갈 수 있는 capa가 cap - 4 가 되는 건가봐...
         *
         * 1/0 0/2 2/0 0/1 1/0 0/2 2/0
         * min (cap - 배달, cap - 수거) =>
         *
         * 아 근데 아래 짠 버전에서,,, 돌아오는 길에 남은 capa만큼 또 실어서 와야 함 <<< 이거 수정할 것!!!!
         */

        int last = n-1; /// 가장 마지막으로 도달해야 하는 index
        long answer = 0;

        while (last > 0) {
            /**  1/0 0/3 3/0 1/4 2/0 */
            int deliveryCnt = 0, pickupCnt = 0;
            int i;
            for (i=last; i>=0; --i) {
                System.out.println(i + " " + deliveryCnt + " " + pickupCnt);
                if (deliveryCnt + deliveries[i] >= cap) {
                    deliveries[i] -= (cap - deliveryCnt);
                    answer += last + 1;

                    if (deliveries[i] == 0) last = i-1;
                    else last = i;

                    System.out.println("here1");
                    break;
                } else {
                    deliveryCnt += deliveries[i];
                }

                if (pickupCnt + pickups[i] >= cap) {
                    pickups[i] -= (cap - pickupCnt);
                    answer += last + 1;

                    if (pickups[i] == 0) last = i-1;
                    else last = i;

                    System.out.println("here2");
                    break;
                } else {
                    pickupCnt += pickups[i];
                }
            }

            System.out.println("answer: " + answer);
        }

        return answer * 2;
    }
}