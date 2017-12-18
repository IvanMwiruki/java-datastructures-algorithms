package steque;

import java.util.Iterator;

public class LinkedSteque<E> implements Steque<E> {

    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int size;

    public LinkedSteque() {}

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
    public void push(E element) { // add item to beginning of list
        Node oldFirst = first;
        first = new Node();
        first.item = element;
        first.next = oldFirst;
        size++;
    }

    @Override
    public E pop() { // remove item from beginning of list
        E element = first.item;
        first = first.next;
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
