package symboltable;

import queue.LinkedQueue;
import queue.Queue;

public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    private Node root; // root of tree

    public BinarySearchTree() {}

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        private Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    @Override
    public K min() {
        if (isEmpty()) {
            return null;
        }

        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }

        return min(x.left);
    }

    @Override
    public K max() {
        if (isEmpty()) {
            return null;
        }

        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }

        return max(x.right);
    }

    @Override
    public K floor(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Node x = floor(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node floor(Node x, K key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    @Override
    public K ceiling(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node ceiling(Node x, K key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    @Override
    public int rank(K key) {
        return rank(key, root);
    }

    private int rank(K key, Node x) {
        // return number of keys less than x.key in subtree rooted at x
        if (x == null) {
            return 0;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    @Override
    public K select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // return node containing key of rank k
        if (x == null) {
            return null;
        }

        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) {
            return;
        }

        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) {
            return;
        }

        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }

        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public int size(K low, K high) {
        int size = 0;
        Iterable<K> keys = keys(low, high);

        for (K key : keys) {
            size++;
        }

        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (value == null) {
            delete(key);
        }

        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        // change key's value to passed in value if key in subtree rooted at x
        // otherwise, add new node to subtree associating key with value
        if (x == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        x.size = size(x.left) + size(x.right) + 1; // on the way back up. reset sizes.
        return x;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(Node x, K key) {
        // return value associated with key in subtree rooted at x
        // return null if key not present in subtree rooted at x
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    @Override
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.left == null) { // node only has 1 child (on the right)
                return x.right;
            }
            if (x.right == null) { // node only has 1 child (on the left)
                return x.left;
            }
            Node t = x; // node has 2 children. Hibbard deletion using successor.
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<K> keys(K low, K high) {
        Queue<K> queue = new LinkedQueue<>();
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node x, Queue<K> queue, K low, K high) {
        if (x == null) {
            return;
        }

        int cmplow = low.compareTo(x.key);
        int cmphi = high.compareTo(x.key);

        if (cmplow < 0) {
            keys(x.left, queue, low, high);
        }
        if (cmplow <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, low, high);
        }
    }
}
