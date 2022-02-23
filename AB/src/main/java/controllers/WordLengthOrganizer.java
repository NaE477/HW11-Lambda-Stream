package controllers;

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

    public static void main(String[] args) {
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList("Amir", "Hatef", "Mehran", "Mojtaba", "Mohammad", "Ali",
                "Davood", "Reza", "Mohsen"));

        Map<Integer, List<String>> mappedWithLength =
                nameList
                        .stream()
                        .collect(
                                groupingBy(String::length)
                        );

        mappedWithLength.forEach((length, names) -> System.out.println("length: " + length + " :" + names));
        System.out.println();
        mappedWithLength.forEach((length, names) -> System.out.println("length: " + length + " :" + names + " count: " + names.size()));

    }
}