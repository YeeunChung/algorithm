package com.company.programmers.stack;

import java.util.*;

public class Main_올바른괄호 {

    public static void main(String[] args) {
        System.out.println(solution("(()("));
    }

    static boolean solution(String s) {

        Stack<Character> bracketStack = new Stack<>();

        for (int i=0; i<s.length(); ++i) {
            if (s.charAt(i) == '(')
                bracketStack.add('(');
            else {
                if (! bracketStack.isEmpty()) bracketStack.pop();
                else return false;
            }
        }

        if (! bracketStack.isEmpty()) return false;
        else return true;

    }
}
