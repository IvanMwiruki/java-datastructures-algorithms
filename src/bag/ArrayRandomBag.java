package bag;

import java.util.Iterator;
import java.util.Random;

/**
 * A bag that provides random iteration.
 */
public class ArrayRandomBag<E> implements Bag<E> {

    private E[] elements;
    private int size;

    public ArrayRandomBag() {}

    @Override
    public void add(E element) {
        if (size == elements.length) {
            resize(2 * elements.length);
        }

        elements[size++] = element;
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
        E[] temp = (E[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public E next() {
            int index = new Random().nextInt(size);
            return elements[index];
        }
    }
}
