package models;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class WordLengthOrganizer {
    static BiFunction<List<String>, Integer, List<String>> lengthOrganizer = (inputs, length) -> {
        List<String> outputs = new ArrayList<>();
        inputs
                .stream()
                .filter(s -> s.length() > 3)
                .forEach(outputs::add);
        return outputs;
    };

    /*public static Map<Integer,ArrayList<String>> returnByLength(List<String> input, Integer length, BiFunction<List<String>, Integer, List<String>> function) {
        Map<Integer,ArrayList<String>> output = new HashMap<>();
        output.put(input.stream().)
        return
    }*/

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Amir", "Hatef", "Mehran", "Mojtaba", "Mohammad", "Ali",
                "Davood", "Reza", "Mohsen"));

        Map<Integer, List<String>> mappedWithLength = names
                .stream()
                .collect(
                        groupingBy(String::length)
                );

        mappedWithLength.forEach((length, name) -> System.out.println("length: " + length + " :" + name));

        Map<Long, Map<Integer, List<String>>> mappedWithCount = names
                .stream()
                .collect(
                        Collectors
                                .groupingBy(String::length,
                                groupingBy(String::length,counting())
                        )
                );

        /*employeesList.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDesignation,
                                Collectors.groupingBy(Employee::getGender,
                                        Collectors.counting()
                                )
                        )
                );*/

        mappedWithCount.forEach((count, map) -> {
            System.out.println(count + " " + map);
        });


/*
        mappedWithLength.forEach((length,list) -> mappedWithCount.put(list.size(),list));

        mappedWithCount.forEach((count,list) -> System.out.println(count + " " +  list));*/
    }
}
