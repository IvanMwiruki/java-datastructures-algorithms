package stack;

/**
 * A collection based on the last-in-first-out policy.
 */
public interface Stack<E> extends Iterable<E> {

    /**
     * Add an item.
     */
    void push(E element);

    /**
     * Remove the most recently added (aka newest) item.
     */
    E pop();

    /**
     * Is the stack empty? Returns true if so, false otherwise.
     */
    boolean isEmpty();

    /**
     * Number of elements in the stack.
     */
    int size();
}
