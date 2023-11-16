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
    void add_should_keep_heap_property() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(3, "Three");
        queue.add(1, "One");
        queue.add(4, "Four");
        queue.add(2, "Two");
        assertEquals("Four", queue.remove());
        assertEquals("Three", queue.remove());
        assertEquals("Two", queue.remove());
        assertEquals("One", queue.remove());
    }
}

