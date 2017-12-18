package bag;

import java.util.Iterator;

public class LinkedBag<E> implements Bag<E>, Iterable<E> {

    private Node first;
    private int size;

    public LinkedBag() {}

    private class Node {
        E item;
        Node next;
    }

    @Override
    public void add(E element) {
        Node oldFirst = first;
        first = new Node();
        first.item = element;
        first.next = oldFirst;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
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
