package sort;

public class SelectionSort implements Sorter {

    @Override
    public void sort(Comparable[] target) {
        int size = target.length;

        for (int i = 0; i < size; i++) {
            int indexOfMin = i;

            for (int j = i + 1; j < size; j++) {
                if (Sorter.less(target[j], target[indexOfMin])) {
                    indexOfMin = j;
                }
            }
            Sorter.swap(target, i, indexOfMin);
        }
    }
}
