package sort;

/**
 * Constructs a max binary heap. Then exchanges the first item (the largest key)
 * with the last item, and reheapifies by sinking the new first item down. Doing
 * this while decrementing size produces a sort array.
 */
public class HeapSort implements Sorter {

    @Override
    public void sort(Comparable[] target) {
        int size = target.length - 1;

        for (int i = size / 2; i >= 1; i--) { // i can start halfway through array because leafs dont need to be included
            sink(target, i, size);
        }

        while (size > 1) {
            Sorter.swap(target, 1, size--);
            sink(target, 1, size);
        }
    }

    private void sink(Comparable[] target, int index, int size) {
        while (2 * index <= size) {
            int i = 2 * index;
            if (i < size && Sorter.less(target[i], target[i + 1])) {
                i++;
            }
            if (!Sorter.less(target[index], target[i])) {
                break;
            }
            Sorter.swap(target, index, i);
            index = i;
        }
    }
}
