package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This implementation is most efficient when adding/removing items at the end
 * (ie using pushRight/popRight) and should be used accordingly.
 */
public class ArrayDeque<E> implements Deque<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public ArrayDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void pushLeft(E element) {
       if (size + 1 == elements.length) {
           resize(2 * elements.length);
       }

       for(int i = 0; i < size; i++) {
           elements[i + 1] = elements[i];
       }
       elements[0] = element;
       size++;
    }

    @Override
    public void pushRight(E element) {
        if (size == elements.length) {
            resize(2 * elements.length);
        }

        elements[size++] = element;
    }

    @Override
    public E popLeft() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        E element = elements[0];
        elements[0] = null;

        E[] temp = (E[]) new Object[size];
        for (int i = 1; i < size; i++) {
            temp[i - 1] = elements[i];
        }
        elements = temp;
        size--;

        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        return element;
    }

    @Override
    public E popRight() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        E element = elements[--size];
        elements[size] = null;

        if (size > 0 && size == elements.length / 4) {
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
        E[] temp = (E[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public E next() {
            return elements[--size];
        }
    }
}
