package priorityqueue;

import java.util.Random;

/**
 * An eager implementation. Insertion is linear time (because ordering is done),
 * but retrieval is constant time, because the largest item is always at the end.
 */
public class OrderedLinkedListMaxPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private Node first;
    private int size;

    public OrderedLinkedListMaxPriorityQueue() {}

    private class Node {
        E item;
        Node next;
    }

    @Override // TODO: reimplement
    public void insert(E element) {
        if (isEmpty() || first.item.compareTo(element) <= 0) {
            Node oldFirst = first;
            first = new Node();
            first.item = element;
            first.next = oldFirst;
            size++;
        } else if (size == 1) {
            first.next = new Node();
            first.next.item = element;
            first.next.next = null;
            size++;
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (x.next != null && x.next.item.compareTo(element) >= 0) {
                    Node oldNext = x.next;
                    x.next = new Node();
                    x.next.item = element;
                    x.next.next = oldNext;
                    size++;
                } else {
                    x.next = new Node();
                    x.next.item = element;
                    x.next.next = null;
                    size++;
                }
            }
        }
    }

    @Override
    public E max() {
        return first.item;
    }

    @Override
    public E deleteMax() {
        E max = first.item;
        first = first.next;
        size--;
        return max;
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
        MaxPriorityQueue<Integer> ints = new OrderedLinkedListMaxPriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            ints.insert(new Random().nextInt(100));
        }

        Integer max = ints.max();
        System.out.println(max);
    }
}
