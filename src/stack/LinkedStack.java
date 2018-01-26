package stack;

import java.util.Iterator;

public class LinkedStack<E> implements Stack<E> {

    private Node first; // top of stack (most recently added Node)
    private int size;

    public LinkedStack() {}

    private class Node {
        E item;
        Node next;
    }

    @Override
    public void push(E element) { // add item to top of stack
        Node oldFirst = first;
        first = new Node();
        first.item = element;
        first.next = oldFirst;
        size++;
    }

    @Override
    public E pop() { // remove item from top of stack
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
            E element = current.item;
            current = current.next;
            return element;
        }
    }
}
