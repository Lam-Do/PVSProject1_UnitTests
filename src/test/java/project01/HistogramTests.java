package project01;

import java.util.List;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Property;

public class HistogramTests {
    // Note: this method can be used to run the unit test without a test framework (e.g. to debug)
    public static void main(String[] args) {
        HistogramTests h = new HistogramTests();
        h.example();
    }

    @Test
    void example() {
        Histogram histogram = new Histogram(2, 3, 0, 5, 5, 6, -2, 9, 0, 3, 5);
        Assertions.assertEquals(-2, histogram.min());
        Assertions.assertEquals(9, histogram.max());


        Assertions.assertEquals(2, histogram.count(0));
        Assertions.assertEquals(0, histogram.count(1));
        Assertions.assertEquals(1, histogram.count(2));

        // some more checks
        int[] expectedArray = {0, 1, 0, 2, 0, 1, 2, 0, 3, 1, 0, 0, 1, 0};
        for (int i = -3; i <= 10; i++) {
            Assertions.assertEquals(expectedArray[i + 3], histogram.count(i));
        }
    }

    int countOccurrences(int value, List<Integer> data) {
        return (int) data.stream().filter(i -> i == value).count();
    }

    @Property // TODO: specify jqwik annotations for the parameters, note we have conveniently marked with ??? where to insert code
    void histogramDoesNotCrash(@ForAll @IntRange(min = -100, max = 0) List<Integer> data) {
        new Histogram(data);
    }

    @Property // TODO: specify jqwik annotations for the parameters
    void histogramCount(@ForAll @IntRange(min = -100, max = 100) List<Integer> data,@ForAll @IntRange(min = -50, max = 50) int value) {
        // TODO: check method count of class Histogram against reference implementation countOccurrences
        Histogram histogram = new Histogram(data);
        Assertions.assertEquals(countOccurrences(value, data), histogram.count(value));
    }

    @Property // TODO: specify jqwik annotations for the parameters
    void histogramRange(@ForAll @IntRange(min = 0, max = 100) List<Integer> data,@ForAll @IntRange(min = 50, max = 100) int value) {
        // TODO: check that if countOccurrences(value) > 0 then
        //       value is between min and max of the corresponding histogram
        Histogram histogram = new Histogram(data);
        Assume.that(countOccurrences(value, data) > 0);
        Assertions.assertTrue(histogram.min() <= value && value <= histogram.max());
    }
}
