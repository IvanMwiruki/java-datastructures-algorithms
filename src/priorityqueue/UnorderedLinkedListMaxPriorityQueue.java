package priorityqueue;

import java.util.Random;

/**
 * A lazy implementation. Insertion is constant time (because no ordering is done),
 * but retrieval is linear time, because it searches through the entire list.
 */
public class UnorderedLinkedListMaxPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private Node first;
    private int size;

    public UnorderedLinkedListMaxPriorityQueue() {}

    private class Node {
        E item;
        Node next;
    }

    @Override
    public void insert(E element) {
        Node oldFirst = first;
        first = new Node();
        first.item = element;
        first.next = oldFirst;
        size++;
    }

    @Override
    public E max() {
        E max = first.item;
        for (Node x = first.next; x != null; x = x.next) {
            if (x.item.compareTo(max) > 0) {
                max = x.item;
            }
        }
        return max;
    }

    @Override
    public E deleteMax() {
        return null; // TODO: implement
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> ints = new UnorderedLinkedListMaxPriorityQueue<>();

        for (int i = 0; i < 50; i++) {
            ints.insert(new Random().nextInt(100));
        }

        Integer max = ints.max();
        System.out.println(max);
    }
}
