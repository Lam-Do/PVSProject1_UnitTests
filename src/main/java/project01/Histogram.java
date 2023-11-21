package project01;

import java.util.Arrays;
import java.util.List;

public class Histogram {
    int[] frequency;
    int min, max;

    public Histogram(List<Integer> data) {
        // initialize attributes
        if (data == null || data.isEmpty()){
            throw new IllegalArgumentException("Input data must not be empty"); // catch empty input
        }
        // Calculate min max value by converting the list into an IntStream then call min() or max() method of Stream API.
        // orElseThrow() catch empty issue of the input list
        this.min = data.stream().mapToInt(Integer::intValue).min().orElseThrow();
        this.max = data.stream().mapToInt(Integer::intValue).max().orElseThrow();
        //min = data.stream().min(Integer::compare).get();
        //max = data.stream().max(Integer::compare).get();

        this.frequency = new int[max - min +1];

        for (int value : data) {
            // update frequencies
            frequency[value - min]++;
        }
    }

    // Note: this constructor is provided as a convenience,
    //       it calls the primary constructor above
    public Histogram(Integer... data) {
        this(Arrays.asList(data));
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int count(int value) {
        int index = value - min;

        if (0 <= index && index < frequency.length)
            return frequency[index];
        else
            return 0;
    }
}
