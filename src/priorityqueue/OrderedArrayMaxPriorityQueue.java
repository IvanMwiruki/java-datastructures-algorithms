package priorityqueue;

import java.util.Random;

/**
 * An eager implementation. Insertion is linear time (because ordering is done),
 * but retrieval is constant time, because the largest item is always at the end.
 */
public class OrderedArrayMaxPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public OrderedArrayMaxPriorityQueue() {
        elements = (E[]) new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void insert(E element) {
        if (size == elements.length) {
            resize(2 * elements.length);
        }

        if (size == 0) {
            elements[size++] = element;
        } else { // TODO: reimplement. doesn't work.
            int indexToPlace = findIndex(element);

            E[] temp = (E[]) new Comparable[++size];
            for (int i = 0; i < size; i++) {
                if (i == indexToPlace) {
                    temp[i] = element;
                } else if (i < indexToPlace) {
                    temp[i] = elements[i];
                } else {
                    temp[i] = elements[i - 1];
                }
            }
            elements = temp;
        }
    }

    private int findIndex(E element) { // linearly find the place to insert the element
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (elements[i].compareTo(element) >= 0) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public E max() {
        return elements[size - 1];
    }

    @Override
    public E deleteMax() {
        E element = elements[--size];
        elements[size] = null;

        if (size > 0 && size <= elements.length / 4) {
            resize(elements.length / 2);
        }
        return element;
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
        MaxPriorityQueue<Integer> ints = new OrderedArrayMaxPriorityQueue<>();

        for (int i = 0; i < 50; i++) {
            ints.insert(new Random().nextInt(100));
        }

        Integer max = ints.max();
    }
}
