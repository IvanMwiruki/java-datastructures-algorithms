package sort;

public class MergeSort implements Sorter{
    private Comparable[] aux;

    @Override
    public void sort(Comparable[] target) {
        aux = new Comparable[target.length];
//        topDownMergeSort(target, 0, target.length - 1);
        bottomUpMergeSort(target);
    }

    private void bottomUpMergeSort(Comparable[] target) {
        int size = target.length;

        for (int subArraySize = 1; subArraySize < size; subArraySize = subArraySize + subArraySize) {
            for (int low = 0; low < size - subArraySize; low += subArraySize + subArraySize) {
                merge(target, low, low + subArraySize - 1, Math.min(low + subArraySize + subArraySize - 1, size - 1));
            }
        }
    }

    private void topDownMergeSort(Comparable[] target, int low, int high) {
        if (high <= low) {
            return;
        }

        int mid = low + (high - low) / 2;

        topDownMergeSort(target, low, mid); // sort left half

        topDownMergeSort(target, mid + 1, high);// sort right half

        merge(target, low, mid, high); // merge sorted sub arrays
    }

    private void merge(Comparable[] target, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            aux[k] = target[k];
        }

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                target[k] = aux[j++];
            } else if (j > high) {
                target[k] = aux[i++];
            } else if (Sorter.less(aux[j], aux[i])) {
                target[k] = aux[j++];
            } else {
                target[k] = aux[i++];
            }
        }
    }
}
