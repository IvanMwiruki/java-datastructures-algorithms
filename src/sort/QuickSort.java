package sort;

import java.util.Arrays;
import java.util.Collections;

public class QuickSort implements Sorter {

    @Override
    public void sort(Comparable[] target) {
        Collections.shuffle(Arrays.asList(target)); // eliminate dependence on input

        quickSort(target, 0, target.length - 1);
    }

    private void quickSort(Comparable[] target, int low, int high) {
        if (high <= low) { // can be changed to if (hi <= low + 15) { insertionSort(target, low, hi); return; } for optimization
            return;
        }
        int j = partition(target, low, high);
        quickSort(target, low, j - 1);
        quickSort(target, j + 1, high);
    }

    private int partition(Comparable[] target, int low, int high) {
        // partition into a[low ... i - 1], a[i], a[i + 1 ... high]
        int i = low;
        int j = high + 1;
        Comparable v = target[low];

        while (true) {
            // scan right, scan left, check for scan complete, and exchange
            while (Sorter.less(target[++i], v)) {
                if (i == high) {
                    break;
                }
            }
            while (Sorter.less(v, target[--j])) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            Sorter.swap(target, i, j);
        }
        Sorter.swap(target, low, j);
        return j;
    }
}
