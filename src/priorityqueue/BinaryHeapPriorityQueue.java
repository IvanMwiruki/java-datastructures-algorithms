package priorityqueue;

import sort.Sorter;

import java.util.Random;

/**
 * A priority queue maintained in a heap-ordered complete binary tree in an array (aka a binary heap).
 */
public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private E[] priorityQueue;
    private int size;

    public BinaryHeapPriorityQueue() {
        priorityQueue = (E[]) new Comparable[10];
    }

    @Override
    public void insert(E element) {
        if (size + 1 == priorityQueue.length) {
            resize(2 * priorityQueue.length);
        }

        priorityQueue[++size] = element;
        swim(size);
    }

    @Override
    public E max() {
        return priorityQueue[1];
    }

    @Override
    public E deleteMax() {
        E max = priorityQueue[1];
        swap(1, size--);
        priorityQueue[size + 1] = null;
        sink(1);

        if (size > 0 && size <= priorityQueue.length / 4) {
            resize(priorityQueue.length / 2);
        }
        return max;
    }

    private void swim(int index) { // reheapify by moving up
        while (index > 1 && Sorter.less(priorityQueue[index / 2], priorityQueue[index])) {
            swap(index / 2, index);
            index = index / 2;
        }
    }

    private void sink(int index) { // reheapify by moving down
        while (2 * index <= size) {
            int i = 2 * index;
            if (i < size && Sorter.less(priorityQueue[i], priorityQueue[i + 1])) {
                i++;
            }
            if (!Sorter.less(priorityQueue[index], priorityQueue[i])) {
                break;
            }
            swap(index, i);
            index = i;
        }
    }

    private void swap(int i, int j) {
        E temp = priorityQueue[i];
        priorityQueue[i] = priorityQueue[j];
        priorityQueue[j] = temp;
    }

    private void resize(int max) {
        E[] temp = (E[]) new Comparable[max];
        for (int i = 1; i <= size; i++) {
            temp[i] = priorityQueue[i];
        }
        priorityQueue = temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> ints = new BinaryHeapPriorityQueue<>();

        for (int i = 0; i < 1; i++) {
            ints.insert(new Random().nextInt(100));
        }

        Integer max = ints.max();
        System.out.println(max);

        for (int i = 0; i < 1; i++) {
            ints.deleteMax();
        }
        System.out.println(ints);
    }
}
