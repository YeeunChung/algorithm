package com.company.programmers.etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_순위검색 {

    public static void main(String[] args) {

    }

    public static int[] solution(String[] info, String[] query) {

        Map<String, List<Integer>> language = new HashMap<>();
        Map<String, List<Integer>> job = new HashMap<>();
        Map<String, List<Integer>> level = new HashMap<>();
        Map<String, List<Integer>> food = new HashMap<>();
        int[] score = new int[info.length];
        int[] answer = new int[query.length];

        for (int i=0; i<info.length; ++i) {
            String[] spec = info[i].split(" ");

            List<Integer> lanList = language.getOrDefault(spec[0], new ArrayList<>());
            lanList.add(i);
            language.put(spec[0], lanList);

            List<Integer> jobList = job.getOrDefault(spec[1], new ArrayList<>());
            jobList.add(i);
            job.put(spec[1], jobList);

            List<Integer> levelList = level.getOrDefault(spec[2], new ArrayList<>());
            levelList.add(i);
            level.put(spec[2], levelList);

            List<Integer> foodList = food.getOrDefault(spec[3], new ArrayList<>());
            foodList.add(i);
            food.put(spec[3],foodList);

            score[i] = Integer.parseInt(spec[4]);
        }

        for (int i=0; i<query.length; ++i) {
            String[] qSpl = query[i].split(" and ");

        }

        return new int[0];

    }

}
