package queue;

import java.util.Iterator;

public class LinkedQueue<E> implements Queue<E>, Iterable<E> {

    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int size;

    public LinkedQueue() {}

    private class Node {
        E item;
        Node next;
    }

    @Override
    public void enqueue(E element) { // add item to end of list
        Node oldLast = last;
        last = new Node();
        last.item = element;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    @Override
    public E dequeue() { // remove item from beginning of list
        E element = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
        }
        size--;
        return element;
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
