package sort;

public class InsertionSort implements Sorter {
    public static int accesses = 0;

    @Override
    public void sort(Comparable[] target) {
        int size = target.length;

        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0 && Sorter.less(target[j], target[j - 1]); j--) {
                Sorter.swap(target, j, j - 1);
                accesses++;
            }
        }
    }

    /**
     * Insertion sort without doing exchanges every time
     */
    public void sortWithLessArrayAccesses(Comparable[] target) {
//        int size = target.length;
//
//        for (int i = 1; i < size; i++) {
//            for (int j = i; j >= 0; j--) {
//                int k = j;
//
//                while (k >= 0 && Sorter.less(target[i], target[k - 1])) {
//                    k--;
//                }
//
//                Sorter.swap(target, i, k);
//                accesses++;
//            }
//        }
        // TODO: reimplement
    }
}
