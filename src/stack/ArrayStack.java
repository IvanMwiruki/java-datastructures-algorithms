package stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    private E[] elements;
    private int size;

    public ArrayStack() {}

    @Override
    public void push(E element) {
        if (size >= elements.length) {
            resize(2 * elements.length);
        }

        elements[size++] = element;
    }

    @Override
    public E pop() {
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
        E[] temp = (E[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public E next() {
            return elements[--size];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
