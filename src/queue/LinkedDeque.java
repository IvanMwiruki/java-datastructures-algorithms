package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<E> implements Deque<E> {

    private Node first; // link to left-most node
    private Node last; // link to right-most node
    private int size;

    public LinkedDeque() {}

    private class Node {
        E item;
        Node next;
        Node previous;
    }

    @Override
    public void pushLeft(E element) {
        Node oldFirst = first;
        first = new Node();
        first.item = element;
        first.next = oldFirst;
        first.previous = null;
        size++;

        if (size == 1) {
            last = first;
        } else if (size > 1) {
            first.next.previous = first;
        }
    }

    @Override
    public void pushRight(E element) {
        Node oldLast = last;
        last = new Node();
        last.item = element;
        last.next = null;
        last.previous = oldLast;
        size++;

        if (size == 1) {
            first = last;
        } else if (size > 1) {
            last.previous.next = last;
        }
    }

    @Override
    public E popLeft() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        E element = first.item;
        first = first.next;
        size--;

        if (size > 0) {
            first.previous = null;
        }
        return element;
    }

    @Override
    public E popRight() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        E element = last.item;
        last = last.previous;
        size--;

        if (size > 0) {
            last.next = null;
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

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E element = first.item;
            current = current.next;
            return element;
        }
    }
}
