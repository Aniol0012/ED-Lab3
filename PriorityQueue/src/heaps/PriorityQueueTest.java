package heaps;

import heaps.HeapQueue;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    @Test
    void add_should_increase_size() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        assertEquals(0, queue.size());
        queue.add(1, "One");
        assertEquals(1, queue.size());
    }

    @Test
    void remove_on_non_empty_queue_should_return_correct_element() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(2, "Two");
        queue.add(1, "One");
        assertEquals("Two", queue.remove());
        assertEquals("One", queue.remove());
    }

    @Test
    void remove_on_empty_queue_should_throw_nse_exception() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        assertThrows(NoSuchElementException.class, () -> queue.remove());
    }

    @Test
    void element_on_non_empty_queue_should_return_correct_element() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(2, "Two");
        queue.add(1, "One");
        assertEquals("Two", queue.element());
    }

    @Test
    void element_on_empty_queue_should_throw_nse_exception() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        assertThrows(NoSuchElementException.class, () -> queue.element());
    }

    @Test
    void size_on_empty_queue_should_return_zero() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        assertEquals(0, queue.size());
    }

    @Test
    void size_should_return_correct_size() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(1, "One");
        queue.add(2, "Two");
        queue.add(3, "Three");
        assertEquals(3, queue.size());
    }

    @Test
    void add_should_keep_heap_property() {
        HeapQueue<Integer, Integer> queue = new HeapQueue<>();
        queue.add(10, 60);
        queue.add(3, 150);
        queue.add(25, 30);
        queue.add(23, 17);
        queue.add(29, 15);
        queue.add(30, 10);
        queue.add(7, 9);
        queue.add(16, 3);
        queue.add(10, 3);
        queue.add(7, 3);
        queue.add(3, 8);


        assertEquals(10, queue.remove());

    }


    @Test
    void remove_should_prioritize_lower_timestamp_on_priority_tie() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(1, "One");
        queue.add(1, "Two");

        assertEquals("One", queue.remove());
        assertEquals("Two", queue.remove());
    }

    @Test
    void test1() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(4, "Four");
        queue.add(3, "Three");

        assertEquals("Four", queue.element());
        assertEquals("Three", queue.remove());
    }
}

