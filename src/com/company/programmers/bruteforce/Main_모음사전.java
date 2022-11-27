package com.company.programmers.bruteforce;

import java.util.*;

public class Main_모음사전 {

    public static void main(String[] args) {

        String word = "UUUUU";
        System.out.println(solution(word));

    }

    static Map<String, String> nextAlp = new HashMap<>();

    static public int solution(String word) {

        nextAlp.put("A", "E");
        nextAlp.put("E", "I");
        nextAlp.put("I", "O");
        nextAlp.put("O", "U");
        nextAlp.put("U", "");

        String nowWord = "";
        int cnt = 0;

        while (! nowWord.equals(word)) {
            ++cnt;

            if (nowWord.length() < 5)
                nowWord += "A";
            else {
                String last = nowWord.substring(nowWord.length() - 1);

                if (last.equals("U")) {
                    // 뒤에서 두번째 요소도 바꿔주기
                    int idx;
                    String secondLast = null;
                    for (idx = 4; idx >= 0; --idx)
                        if (nowWord.charAt(idx) != 'U') {
                            secondLast = String.valueOf(nowWord.charAt(idx));
                            break;
                        }

                    String secondNext = nextAlp.get(secondLast);
                    nowWord = nowWord.substring(0, idx) + secondNext;
                } else {
                    String next = nextAlp.get(last);
                    nowWord = nowWord.substring(0, nowWord.length() - 1) + next;
                }
            }
        }

        return cnt;

        /**
         * Axxxx
         * AAxxx
         * AAAxx
         * AAAAx
         * AAAAA
         * AAAAE
         * AAAAI
         * AAAAO
         * AAAAU
         * AAAEx
         * AAAEA
         * AAAEE
         * AAAEI
         * AAAEO
         * AAAEU
         * AAAUA
         * AAAIx
         * AAAIA
         * ...
         * AAAU
         * ...
         * AAAUU
         * AAExx
         * ...
         * E
         * EA
         * EAA
         * EAAA
         * EAAAA
         * EAAAE
         * EAAAI
         * EAAAO
         * EAAAU
         * EAAE
         * EAAI
         * EAAO
         * EAAU
         */
    }

}
