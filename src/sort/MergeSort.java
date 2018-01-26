package sort;

public class MergeSort implements Sorter{
    private Comparable[] aux;

    @Override
    public void sort(Comparable[] target) {
        aux = new Comparable[target.length];
        topDownMergeSort(target, 0, target.length - 1);
//        bottomUpMergeSort(target);
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

        int mid = low + ((high - low) / 2);

        topDownMergeSort(target, low, mid); // sort left half

        topDownMergeSort(target, mid + 1, high);// sort right half

        merge(target, low, mid, high); // merge sorted sub arrays
    }

    private void merge(Comparable[] target, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;

        for (int i = low; i <= high; i++) {
            aux[i] = target[i];
        }

        for (int i = low; i <= high; i++) {
            if (left > mid) {
                target[i] = aux[right++];
            } else if (right > high) {
                target[i] = aux[left++];
            } else if (Sorter.less(aux[right], aux[left])) {
                target[i] = aux[right++];
            } else {
                target[i] = aux[left++];
            }
        }
    }
}
