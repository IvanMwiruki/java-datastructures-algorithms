package sort;

public interface Sorter {

    /**
     * Sorts the target.
     */
    void sort(Comparable[] target);

    /**
     * Returns true if left is less than right, false otherwise.
     */
    static boolean less(Comparable left, Comparable right) {
        return left.compareTo(right) < 0;
    }

    /**
     * Swap the objects at index i and j.
     */
    static void swap(Comparable[] target, int i, int j) {
        Comparable temp = target[i];
        target[i] = target[j];
        target[j] = temp;
    }

    /**
     * Print out the target.
     */
    static void show(Comparable[] target) {
        for (Comparable object : target) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    /**
     * Returns true if the target is sorted (ascending).
     */
    static boolean isSorted(Comparable[] target) {
        for (int i = 0; i < target.length; i++) {
            if (less(target[i], target[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
