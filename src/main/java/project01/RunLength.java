package project01;

import java.util.ArrayList;
import java.util.List;

public class RunLength {
    public static <T> List<Run<T>> encode(List<T> input) {
        // Note: you may assume without checking that input contains no null elements.
        // Java will complain if you try something like this:
        //     if(input.contains(null))

        List<Run<T>> result = new ArrayList<>();
        T currentElement = input.get(0);
        int count = 1;

        //T currentElement = null;
        //int count = 0

        for (int i = 1; i < input.size(); i++) { // int i = 0
            if (input.get(i).equals(currentElement)) {
                count++;
            } else {
                result.add(new Run<>(count, currentElement));
                currentElement = input.get(i);
                count = 1;
            }
        }
        // if currentElement != null ... add
        result.add(new Run<>(count, currentElement));

        return result;
    }

    public static <T> List<T> decode(List<Run<T>> runs) {
        List<T> result = new ArrayList<>();

        for (Run<T> run : runs) {
            for (int c = 0; c < run.count(); c++) {
                result.add(run.elem());
            }
        }

        return result;
    }

    public static Integer sum(List<Run<Integer>> runs) {
        int sum = 0;
        for (Run<Integer> run : runs) {
            sum = sum + run.count() * run.elem();
        }
        return sum;
        // runs.stream().mapToInt(run -> run.elem() * run.count()).sum();
    }
}
