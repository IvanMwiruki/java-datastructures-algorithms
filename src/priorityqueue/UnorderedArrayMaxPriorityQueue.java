package priorityqueue;

import java.util.Random;

/**
 * A lazy implementation. Insertion is constant time (because no ordering is done),
 * but retrieval is linear time, because it searches through the entire array.
 */
public class UnorderedArrayMaxPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public UnorderedArrayMaxPriorityQueue() {
        elements = (E[]) new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void insert(E element) {
        if (size == elements.length) {
            resize(2 * elements.length);
        }

        elements[size++] = element;
    }

    @Override
    public E max() {
        return elements[indexOfMax()];
    }

    @Override
    public E deleteMax() {
        swap(indexOfMax(), size - 1);
        E element = elements[--size]; // largest item now guaranteed to be at the end
        elements[size] = null;

        if (size > 0 && size <= elements.length / 4) {
            resize(elements.length / 2);
        }
        return element;
    }

    private void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private int indexOfMax() {
        int indexOfMax = 0;
        for (int i = 1; i < size; i++) {
            if (elements[i].compareTo(elements[indexOfMax]) > 0) {
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int max) {
        E[] temp = (E[]) new Comparable[max];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> ints = new UnorderedArrayMaxPriorityQueue<>();

        for (int i = 0; i < 50; i++) {
            ints.insert(new Random().nextInt(100));
        }

        Integer max = ints.max();
        System.out.println(max);
    }
}
