package hwModels.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class WordLengthOrganizer {
    static BiFunction<List<String>, Integer, List<String>> lengthOrganizer = (inputs, length) -> {
        List<String> outputs = new ArrayList<>();
        inputs
                .stream()
                .filter(s -> s.length() == length)
                .forEach(outputs::add);
        return outputs;
    };

    public static List<String> returnByLength(List<String> input, Integer length, BiFunction<List<String>, Integer, List<String>> function) {
        return function.apply(input,length);
    }

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Nae","Mae","Naeim","Rahim"));
        List<String> organized = returnByLength(names,5,lengthOrganizer);
        for(String s : organized){
            System.out.println(s);
        }
    }
}
