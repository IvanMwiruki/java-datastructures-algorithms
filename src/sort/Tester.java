package sort;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        final Integer[] toSort = new Random()
                .ints(1, 100)
                .limit(100)
                .boxed()
                .toArray(Integer[]::new);

        Sorter sorter = new QuickSort();
        sorter.sort(toSort);

        Sorter.show(toSort);
    }
}
