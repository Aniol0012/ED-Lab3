package heaps;

import heaps.HeapQueue;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    @Test
    void add_should_increase_size() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();
        assertEquals(0, heapQueue.size());
        heapQueue.add(1, 100);
        assertEquals(1, heapQueue.size());
    }

    @Test
    void remove_on_non_empty_queue_should_return_correct_element() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();
        heapQueue.add(2, 20);
        heapQueue.add(1, 10);

        assertEquals(20, heapQueue.remove());
        assertEquals(10, heapQueue.remove());
    }

    @Test
    void remove_on_empty_queue_should_throw_nse_exception() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();
        assertThrows(NoSuchElementException.class, () -> heapQueue.remove());
    }

    @Test
    void element_on_non_empty_queue_should_return_correct_element() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();
        heapQueue.add(2, 200);
        heapQueue.add(1, 100);
        assertEquals(200, heapQueue.element());
    }

    @Test
    void element_on_empty_queue_should_throw_nse_exception() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();
        assertThrows(NoSuchElementException.class, () -> heapQueue.element());
    }

    @Test
    void size_on_empty_queue_should_return_zero() {
        HeapQueue<Integer, String> heapQueue = new HeapQueue<>();
        assertEquals(0, heapQueue.size());
    }

    @Test
    void size_should_return_correct_size() {
        HeapQueue<Integer, String> heapQueue = new HeapQueue<>();
        heapQueue.add(1, "First");
        heapQueue.add(2, "Second");
        heapQueue.add(3, "Third");
        assertEquals(3, heapQueue.size());
    }

    @Test
    void add_should_keep_heap_property() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();
        heapQueue.add(10, 60);
        heapQueue.add(3, 150);
        heapQueue.add(25, 30);
        heapQueue.add(23, 17);
        heapQueue.add(29, 15);
        heapQueue.add(30, 10);
        heapQueue.add(7, 9);
        heapQueue.add(16, 3);
        heapQueue.add(10, 3);
        heapQueue.add(7, 3);
        heapQueue.add(3, 8);

        assertEquals(10, heapQueue.remove());
        assertEquals(15, heapQueue.element());
    }

    @Test
    void remove_should_prioritize_lower_timestamp_on_priority_tie() {
        HeapQueue<Integer, Boolean> heapQueue = new HeapQueue<>();
        heapQueue.add(1, true);
        heapQueue.add(1, false);

        assertTrue(heapQueue.remove());
        assertFalse(heapQueue.remove());
    }

    @Test
    void remove_should_prioritize_lower_timestamp_on_multiple_priority_tie() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();
        heapQueue.add(20, 100);
        heapQueue.add(10, 200);
        heapQueue.add(10, 300);

        assertEquals(100, heapQueue.remove());
        assertEquals(200, heapQueue.remove());

        heapQueue.add(10, 400);

        assertEquals(300, heapQueue.element());
    }

    @Test
    void add_null_priority_should_throw_ia_exception() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();

        assertThrows(IllegalArgumentException.class, () -> heapQueue.add(null, 100));
    }

    @Test
    void add_null_value_should_throw_ia_exception() {
        HeapQueue<Integer, Integer> heapQueue = new HeapQueue<>();

        assertThrows(IllegalArgumentException.class, () -> heapQueue.add(1, null));
    }
}

