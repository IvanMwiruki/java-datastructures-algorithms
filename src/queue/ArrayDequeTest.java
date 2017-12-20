package queue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    private ArrayDeque<String> deque;

    @Before
    public void setUp() {
        deque = new ArrayDeque<>();
    }

    @Test
    public void emptyDeque() {
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void pushLeftEmptyDeque() {
        deque.pushLeft("left");

        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());
    }

    @Test
    public void pushRightEmptyDeque() {
        deque.pushRight("right");

        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void popLeftEmptyDeque() {
        deque.popLeft();
    }

    @Test(expected = NoSuchElementException.class)
    public void popRightEmptyDeque() {
        deque.popRight();
    }

    @Test
    public void pushLeftSingleItemDeque() {
        deque.pushLeft("single item");

        deque.pushLeft("left");

        assertEquals(2, deque.size());
    }

    @Test
    public void pushRightSingleItemDeque() {
        deque.pushRight("single item");

        deque.pushRight("right");

        assertEquals(2, deque.size());
    }

    @Test
    public void popLeftSingleItemDeque() {
        deque.pushLeft("single item");

        String result = deque.popLeft();

        assertEquals(0, deque.size());
        assertEquals("single item", result);
    }

    @Test
    public void popRightSingleItemDeque() {
        deque.pushRight("single item");

        String result = deque.popRight();

        assertEquals(0, deque.size());
        assertEquals("single item", result);
    }

    @Test
    public void pushRightPushLeftPopRightPopLeft() {
        deque.pushLeft("left");
        deque.pushRight("right");

        String left = deque.popLeft();
        String right = deque.popRight();

        assertEquals(0, deque.size());
        assertEquals("left", left);
        assertEquals("right", right);
    }
}
