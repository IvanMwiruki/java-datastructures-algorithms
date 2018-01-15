package sort;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        final Integer[] toSort = new Random()
                .ints(1, 100)
                .limit(100)
                .boxed()
                .toArray(Integer[]::new);

//        final Integer[] toSort = new Integer[test.length + 1]; // create a new array where index 0 is null
//        for (int i = 0; i < test.length; i++) {
//            toSort[i + 1] = test[i];
//        }

        Sorter sorter = new QuickSort();
        sorter.sort(toSort);

        Sorter.show(toSort);
    }
}
