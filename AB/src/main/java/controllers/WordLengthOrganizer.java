package controllers;

import java.util.*;

import static java.util.stream.Collectors.*;


public class WordLengthOrganizer {
    public static void main(String[] args) {
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList("Amir", "Hatef", "Mehran", "Mojtaba", "Mohammad", "Ali",
                "Davood", "Reza", "Mohsen"));

        /*Map<Integer, List<String>> mappedWithLength = mapByLength(nameList);*/

        Map<Integer, List<String>> mappedWithLength = nameList
                .stream()
                .collect(
                        groupingBy(String::length)
                );

        mappedWithLength.forEach((length, names) -> System.out.println("length: " + length + " :" + names));

        System.out.println();

        mappedWithLength.forEach((length, names) -> System.out.println("length: " + length + " :" + names + " count: " + names.size()));

    }

    /*public static Map<Integer,List<String>> mapByLength(ArrayList<String> list) {
        return list
                .stream()
                .collect(
                        groupingBy(String::length)
                );
    }*/
}