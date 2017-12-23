package sort;

public class ShellSort implements Sorter {

    @Override
    public void sort(Comparable[] target) {
        int size = target.length;

        int h = 1;
        while (h < size / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = 1; i < size; i++) {
                for (int j = i; j >= h && Sorter.less(target[j], target[j - h]); j -= h) {
                    Sorter.swap(target, j, j - h);
                }

            }
            h = h / 3;
        }
    }
}
