package steque;

import stack.Stack;

/**
 * Stack-ended queue: a collection that supports push (to the beginning), pop (from the beginning), and
 * enqueque (at the end).
 */
public interface Steque<E> extends Stack<E> {

    /**
     * Add an item to the end.
     */
    void enqueue(E element);
}
