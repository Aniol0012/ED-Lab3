import heaps.HeapQueue;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    @Test
    public void testAddAndRemove() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(3, "Tres");
        queue.add(1, "Uno");
        queue.add(2, "Dos");

        assertEquals("Uno", queue.remove());
        assertEquals("Dos", queue.remove());
        assertEquals("Tres", queue.remove());
    }

    @Test
    public void testElement() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        queue.add(5, "Cinco");
        assertEquals("Cinco", queue.element());

        queue.add(1, "Uno");
        assertEquals("Uno", queue.element()); // Suponiendo que 1 es mayor prioridad que 5
    }

    @Test
    public void testSize() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();
        assertEquals(0, queue.size());

        queue.add(1, "Uno");
        assertEquals(1, queue.size());

        queue.remove();
        assertEquals(0, queue.size());
    }

    @Test
    public void testRemoveFromEmptyQueue() {
        HeapQueue<Integer, String> queue = new HeapQueue<>();

        assertThrows(NoSuchElementException.class, queue::remove);
    }
}
