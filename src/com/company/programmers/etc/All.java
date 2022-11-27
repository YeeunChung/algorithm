package com.company.programmers.etc;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class All {

    int[] unionArr;

    void all () {
        // 정규표현식
        String str = "something_++";
        str = str.replaceAll("[^._-a-zA-Z0-9]", "");

        // 자릿수 세기
        int num = 111;
        int cntDigit = (int) Math.log(num) + 1;

        // String List -> Array 변환
        List<String> strList = new ArrayList<>();
        String[] strArr = strList.toArray(new String[0]);
        String[] strArr2 = strList.toArray(new String[0]);

        // 특정 값으로 초기화
        Arrays.fill(strArr, "");

        /** TODO: Permutation */

        // 최대공약수, 최소공배수
        BigInteger a1 = BigInteger.valueOf(1);
        BigInteger a2 = BigInteger.valueOf(2);
        int gcd = a1.gcd(a2).intValue();

        BigInteger b1 = BigInteger.valueOf(1);
        BigInteger b2 = BigInteger.valueOf(2);
        int gcd2 = b1.gcd(b2).intValue();

        // 우선순위 힙
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        PriorityQueue<Integer> queue1 = new PriorityQueue<>(Comparator.reverseOrder());

        // List 초기화
        List<Integer> testList = new ArrayList<>(Arrays.asList(1));

        // 배열 sort
        testList.sort(Comparator.comparingInt(o -> o));
        testList.sort(Comparator.reverseOrder());

        // Map Key로 정렬
        Map<Integer, String> map = new HashMap<>();
        Set<Integer> keySet = map.keySet();
        keySet.stream().sorted();

        Object[] keySet2 = map.keySet().toArray(new Integer[0]);
        Arrays.sort(keySet2);

        // Map Value로 정렬
        List<Map.Entry<Integer, String>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        entryList.sort(Map.Entry.comparingByValue());

        List<Map.Entry<Integer, String>> entryList1 = new ArrayList<>(map.entrySet());
        entryList1.sort(Map.Entry.comparingByValue());

        List<Map.Entry<Integer, String>> entryList2 = new ArrayList<>(map.entrySet());
        entryList2.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // array -> list
        String[] array = new String[10];
        List<String> list = new ArrayList<>(Arrays.asList(array));

        // list -> array
        array = list.toArray(new String[0]);

        array = list.toArray(new String[0]);
        list = new ArrayList<>(Arrays.asList(array));

        // union / find
        for (int i=0; i<unionArr.length; ++i) {
            unionArr[i] = i; // 자기 자신을 부모로 등록
        } // 그러고 아래 함수~~

        // Map Value 꺼내기
        Map<String, Integer> map2 = new HashMap<>();
        List<Integer> valueLIst = new ArrayList<>(map2.values());

        // List Sum 구하기
        List<Integer> intList = new ArrayList<>();
        int sum = intList.stream().mapToInt(Integer::intValue)
                .sum();

        // 10진법 n을 k진법으로 변환
        int n = 100; int k = 2;
        String transNum = Integer.toString(n, k);

        String transNum2 = Integer.toString(n, k);

        // Char Array <-> String
        char[] charArr = str.toCharArray();
        str = String.valueOf(charArr);

        str = String.valueOf(charArr);
        str = String.valueOf(charArr);

        // Map Value 정렬
        Map<String, Integer> map1 = new HashMap<>();
        List<Map.Entry<String, Integer>> entryList3 = new ArrayList<>(map1.entrySet());
        entryList3.sort(Map.Entry.comparingByValue());

        // arr to string
    }

    void union(int i, int j) {
        int rootI = findRoot(i);
        int rootJ = findRoot(j);

        if (rootJ == rootI) return;
        unionArr[rootI] = rootJ;
    }

    int findRoot(int i) {
        if (i == unionArr[i]) return i;
        int root = findRoot(i);
        unionArr[i] = root;
        return root;
    }

    /** Permutation, Combination */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        int[] output = new int[2];
        int[] visited = new int[arr.length];
        Integer ansCnt = 0;
//        permutation(arr, 0, 2, output, visited);
//        System.out.println(ansCnt); // 불변객체라 안바뀜 ㅋㅋㅋㅋ
//        for (int i=0; i<ansCnt; ++i)
//            System.out.println(Arrays.toString(answer[i]));

        combination(arr, 0, 3, 2, output, 0, visited);
    }

    static int[][] answer = new int[10000][2];
    static int ansCnt = 0;

    // permutation: 순서 지켜야 함
    static void permutation(int[] arr, int cnt, int r, int[] output, int[] visited) {

        if (cnt == r) {
            answer[ansCnt] = output;
            ansCnt++;
            System.out.println(Arrays.toString(output));
            return;
        }

        for (int i=0; i<arr.length; ++i) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            output[cnt] = arr[i];
            permutation(arr, cnt + 1, r, output, visited);
            visited[i] = 0;
        }
    }

    static void combination(int[] arr, int cnt, int n, int r, int[] output, int index, int[] visited) {
        if (cnt == r) {
            System.out.println(Arrays.toString(output));
            return;
        }

        for (int i=index; i<arr.length; ++i) {
            output[cnt] = arr[i];
            combination(arr, cnt+1, n, r, output, i + 1, visited);
        }
    }

    /**
     * select count (distinct name) as count
     * from ~~
     * where id is not null
     *
     * select *
     * from A
     * inner join B
     * ON A.ID = B.ID
     *
     * select *
     * from A
     * order by name
     * limit 5;
     *
     * select ifnull(name, "null")
     * from Name
     * order by insertId;
     *
     * set @hour = -1;
     * select @hour := @hour + 1 as hour,
     * (select count(*)
     * from animal_outs
     * where hour(datetime) = @hour) as count
     * from animals_out
     * where @hour < 23;
     *
     * set @hour = -1;
     * select @hour := @hour + 1 as hour,
     * (select count(*)
     * from animal_outs
     * where hour(datetime) = @hour) as count
     * from animals_outs;
     *
     * select * from animal
     * where name in ('nane', 'a', 'b');
     *
     * select *
     * from animal
     * where name like '%ne%';
     *
     * select case
     *  when name = 'name' then 'O'
     *  else 'X'
     *  end 'nameOX'
     *  from animal;
     *
     *  select if (name ='name', '0', 'X') AS 'nameOX'
     *  from animal;
     */
}
